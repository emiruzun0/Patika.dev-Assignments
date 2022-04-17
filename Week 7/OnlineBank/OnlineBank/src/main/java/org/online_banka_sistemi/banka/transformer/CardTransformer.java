package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.external.NumberEvents;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardTransformer extends NumberEvents {

    public Card cardTransfer(CardDto cardDto){
        Card card = new Card();
        toCreateCard(card,cardDto);
        return card;
    }


    private void toCreateCard(Card card, CardDto cardDto){
        card.setCardNo(createCardNo());
        card.setCcv(ccvNo());
        card.setCardType(cardDto.getCardType());
        card.setCardDebt(cardDto.getCardDebt());
        card.setCardLimit(cardDto.getCardLimit());
        card.setExpenses(cardDto.getExpenses());
        card.setAmount(cardDto.getAmount());
        card.setCreatedDate(LocalDate.now());
        card.setCardPassword(cardDto.getCardPassword());
        card.setAccounts(toAccountList(cardDto.getAccounts()));
    }

    public List<Account> toAccountList(List<AccountDto> accountDtos){
        ;List<Account> accounts = new ArrayList<>();
        for (AccountDto accountDto:accountDtos) {
            accounts.add(toAccount(accountDto));
        }
        return accounts;
    }
    private Account toAccount(AccountDto accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .balance(accountDto.getBalance())
                .currency(accountDto.getCurrency())
                .accountType(accountDto.getAccountType())
                .iban(accountDto.getIban())
                .createdDate(accountDto.getCreatedDate())
                .build();
    }

    public List<AccountDto> toAccountDtoList(List<Account> accounts){
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            accountDtos.add(toAccountDto(account));
        }
        return accountDtos;
    }

    private AccountDto toAccountDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .accountType(account.getAccountType())
                .iban(account.getIban())
                .createdDate(account.getCreatedDate())
                .build();
    }

    public CardDto toCardDto(Card card){
        return CardDto.builder()
                .id(card.getId())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .cardLimit(card.getCardLimit())
                .expiredDate(card.getExpiredDate())
                .ccv(card.getCcv())
                .createdDate(card.getCreatedDate())
                .cardDebt(card.getCardDebt())
                .amount(card.getAmount())
                .expenses(card.getExpenses())
                .cardPassword(card.getCardPassword())
                .accounts(toAccountDtoList(card.getAccounts()))
                .build();
    }
}
