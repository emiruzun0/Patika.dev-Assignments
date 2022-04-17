package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.external.NumberEvents;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerTransformer extends NumberEvents {

    public Customer customerTransfer(CustomerDto customerDto) {
        List<Card> cards = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        accountDtoCreate(accounts, customerDto);
        Customer customer = new Customer();
        customerCreate(accounts,customerDto,customer, cards);
        cartDtoCreate(cards, customerDto, customer);
        return customer;
    }

    private void customerCreate(List<Account> accounts, CustomerDto customerDto, Customer customer, List<Card> cards){
        customer.setAccounts(accounts);
        customer.setDescription(customerDto.getDescription());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setFullName(customerDto.getFullName());
        customer.setTC(customerDto.getTC());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
        customer.setCreatedDate(LocalDate.now());
        customer.setCards(cards);
    }

    private void accountDtoCreate(List<Account> accounts, CustomerDto customerDto) {

        for (int i = 0; i < customerDto.getAccounts().size(); i++) {

            AccountDto accountDto = customerDto.getAccounts().get(i);

            Account account = new Account();
            account.setAccountType(accountDto.getAccountType());
            account.setCurrency(accountDto.getCurrency());
            account.setBalance(accountDto.getBalance());
            account.setIban(setAccountIban(account));
            accounts.add(account);
        }
    }

    private void cartDtoCreate(List<Card> cards, CustomerDto customerDto, Customer customer){
        for (int i = 0; i < customerDto.getCards().size(); i++) {

            CardDto cardDto = customerDto.getCards().get(i);

            Card card = new Card();
            card.setCardNo(createCardNo());
            card.setCardLimit(cardDto.getCardLimit());
            card.setCcv(ccvNo());
            card.setCardType(cardDto.getCardType());
            card.setCardDebt(cardDto.getCardDebt());
            card.setExpenses(cardDto.getExpenses());
            card.setAmount(cardDto.getAmount());
            card.setCreatedDate(LocalDate.now());
            card.setCardPassword(cardDto.getCardPassword());
            card.setAccounts(customer.getAccounts());
            cards.add(card);
        }
    }

    public CustomerDto toCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .TC(customer.getTC())
                .password(customer.getPassword())
                .description(customer.getDescription())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .cards(toCardDtoList(customer.getCards()))
                .accounts(toAccountDtoList(customer.getAccounts()))
                .createdDate(LocalDate.now())
                .build();
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

    public List<AccountDto> toAccountDtoList(List<Account> accounts){
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            accountDtos.add(toAccountDto(account));
        }
        return accountDtos;
    }


    private CardDto toCardDto(Card card){
        return CardDto.builder()
                .id(card.getId())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .cardLimit(card.getCardLimit())
                .expiredDate(card.getExpiredDate())
                .ccv(card.getCcv())
                .createdDate(LocalDate.now())
                .cardDebt(card.getCardDebt())
                .amount(card.getAmount())
                .expenses(card.getExpenses())
                .cardPassword(card.getCardPassword())
                .accounts(toAccountDtoList(card.getAccounts()))
                .build();
    }

    public List<CardDto> toCardDtoList(List<Card> cards){
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            cardDtos.add(toCardDto(card));
        }
        return cardDtos;    
    }

}
