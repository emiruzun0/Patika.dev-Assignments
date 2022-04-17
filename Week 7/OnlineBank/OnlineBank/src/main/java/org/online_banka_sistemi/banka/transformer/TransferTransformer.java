package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.TransferDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Transfer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransferTransformer {

    public Transfer accountTransfer(TransferDto transferDto){

        List<Account> accounts = new ArrayList<>();
        accountDtoCreate(accounts,transferDto);
        Transfer transfer = new Transfer();
        transferCreate(accounts,transferDto,transfer);
        return transfer;
    }


    private void transferCreate(List<Account> accounts, TransferDto transferDto, Transfer transfer ){

        transfer.setAccountType(transferDto.getAccountType());
        transfer.setCurrency(transferDto.getCurrency());
        transfer.setAccounts(accounts);
        transfer.setAmount(transferDto.getAmount());
        transfer.setDate(LocalDate.now());
        transfer.setSenderIban(transferDto.getSenderIban());
        transfer.setReceiverIban(transferDto.getReceiverIban());
    }


    private void accountDtoCreate(List<Account> accounts, TransferDto transferDto){
        for (int i = 0; i < transferDto.getAccounts().size(); i++) {
            Account accountDto = transferDto.getAccounts().get(i);

            Account account = new Account();
            account.setId(accountDto.getId());
            account.setIban(accountDto.getIban());
            account.setBalance(accountDto.getBalance());
            account.setCurrency(accountDto.getCurrency());
            account.setAccountType(accountDto.getAccountType());
            account.setCreatedDate(accountDto.getCreatedDate());
            accounts.add(account);
        }
    }

}
