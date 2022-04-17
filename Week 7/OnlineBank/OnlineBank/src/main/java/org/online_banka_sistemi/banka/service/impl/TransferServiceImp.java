package org.kodluyoruz.mybank.service.impl;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.TransferDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.exception.accountException.MoneyCanNotTransferred;
import org.kodluyoruz.mybank.external.ExchangeRates;
import org.kodluyoruz.mybank.entity.Transfer;
import org.kodluyoruz.mybank.external.Messages;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.TransferRepository;
import org.kodluyoruz.mybank.service.TransferService;
import org.kodluyoruz.mybank.transformer.TransferTransformer;
import org.kodluyoruz.mybank.external.NumberEvents;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImp extends NumberEvents implements TransferService {


    private final RestTemplate restTemplate;


    private final TransferTransformer transferTransformer;


    private final TransferRepository transferRepository;


    private final AccountRepository accountRepository;



    @Override
    public ExchangeRates exchangeRate() {
        return restTemplate.getForObject("https://api.exchangeratesapi.io/latest?base=TRY", ExchangeRates.class);
    }

    @Override
    public double usdRate() {
        return exchangeRate().getRates().get("TRY") / exchangeRate().getRates().get("USD");
    }

    @Override
    public double eurRate() {
        return exchangeRate().getRates().get("TRY") / exchangeRate().getRates().get("EUR");
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }


    @Override
    @Transactional(rollbackFor = ResponseStatusException.class)
    public Transfer sendMoney(TransferDto transferDto, String senderIban, String receiverIban) {

            Transfer transfer = transferTransformer.accountTransfer(transferDto);
            double amount = transfer.getAmount();
            String currency = transfer.getCurrency();
            String accountType = transfer.getAccountType();
            senderIban = transfer.getSenderIban();
            receiverIban = transfer.getReceiverIban();
            toAccountsTransfer(transfer, amount, senderIban, receiverIban);
            Transfer transfer1 = new Transfer();
            saveTransfer(transfer1, amount, currency, accountType,senderIban,receiverIban);
            return transfer;
    }

    private void saveTransfer(Transfer transfer, double amount, String currency, String accountType, String senderIban, String receiverIban){
        transfer.setAmount(amount);
        transfer.setCurrency(currency);
        transfer.setAccountType(accountType);
        transfer.setSenderIban(senderIban);
        transfer.setReceiverIban(receiverIban);
        transferRepository.save(transfer);
    }

    private void toAccountsTransfer(Transfer transfer, double amount, String senderIban, String receiverIban){

        String euro = "EUR";
        String tl = "TRY";
        String usd = "USD";

        Account accountSend = transfer.getAccounts().get(0);
        Account accountTake = transfer.getAccounts().get(1);

        double forEurToTl = (double) Math.round((amount * eurRate()) * 100) / 100;
        double forTlToEuro = (double) Math.round((amount / eurRate()) * 100) / 100;

        double forUsdToTl = (double) Math.round((amount * usdRate()) * 100) / 100;
        double forTlToUsd = (double) Math.round((amount / usdRate()) * 100) / 100;

        if (senderIban.equals(accountSend.getIban()) & receiverIban.equals(accountTake.getIban()) & accountSend.getAccountType().equals("CHECKING")){

                if (accountSend.getAccountType().equals(accountTake.getAccountType()) && accountSend.getCurrency().equals(accountTake.getCurrency())) {
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + amount);
                }
                if (accountSend.getCurrency().equals(euro) & accountTake.getCurrency().equals(tl)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + forEurToTl);
                }
                else if (accountSend.getCurrency().equals(tl) & accountTake.getCurrency().equals(euro)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + forTlToEuro);
                }else if (accountSend.getCurrency().equals(usd) & accountTake.getCurrency().equals(tl)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + forUsdToTl);
                }else if (accountSend.getCurrency().equals(tl) & accountTake.getCurrency().equals(usd)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + forTlToUsd);
                }else if (accountSend.getCurrency().equals(euro) & accountTake.getCurrency().equals(usd)){
                    double euroAmount = amount * (usdRate() / eurRate());
                    double usdAmount = amount * (eurRate() / usdRate());
                    accountSend.setBalance(accountSend.getBalance() - euroAmount);
                    accountTake.setBalance(accountTake.getBalance() + usdAmount);
                }else if (accountSend.getCurrency().equals(usd) & accountTake.getCurrency().equals(euro)){
                    double euroAmount = amount * (usdRate() / eurRate());
                    double usdAmount = amount * (eurRate() / usdRate());
                    accountSend.setBalance(accountSend.getBalance() - usdAmount);
                    accountTake.setBalance(accountTake.getBalance() + euroAmount);
                }
        }else if (senderIban.equals(accountSend.getIban()) & receiverIban.equals(accountTake.getIban()) &
                accountSend.getAccountType().equals("SAVING")){
            throw new MoneyCanNotTransferred(Messages.Error.NOT_ENOUGH_MONEY_IN_YOUR_ACCOUNT.message);
        }
        accountRepository.save(accountSend);
        accountRepository.save(accountTake);
    }

}