package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.entity.Expenses;
import org.kodluyoruz.mybank.repository.CardRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.CardService;
import org.kodluyoruz.mybank.external.NumberEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CardServiceImp extends NumberEvents implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Card> list(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Override
    public void addCart(int id, CardDto cardDto) {

        Customer customer = customerRepository.getById(id);
        List<Card> cards = customer.getCards();
        Card card = new Card();

        if (setNewCart(cards,card,customer,cardDto)){
            customerRepository.save(customer);
        }
    }

    private boolean setNewCart(List<Card> cards, Card card, Customer customer, CardDto cardDto){
        List<Account> accounts = customer.getAccounts();

        for (int i = 0; i < cards.size(); i++) {
            card.setCardNo(createCardNo());
            card.setCardLimit(cardDto.getCardLimit());
            card.setCcv(ccvNo());
            card.setCardType(cardDto.getCardType());
            card.setCardPassword(cardDto.getCardPassword());
            card.setAccounts(accounts);

            customer.setCards(cards);
        }
        cards.add(card);
        return true;
    }


    @Override
    public HashMap<String,Double> findAllCardDept(int id) {

        Customer customer = customerRepository.getById(id);
        List<Card> cards = customer.getCards();
        HashMap<String, Double> myJson = new HashMap<>();

        for (Card card : cards) {
            if (card.getCardType().equals("CREDIT")){
                double debtAmount = card.getCardDebt();
                myJson.put(card.getCardType()+" CARD DEPT",debtAmount);
            }
        }
        return myJson;
    }

    @Override
    public Card getById(int id) {
        return cardRepository.getById(id);
    }

    @Override
    public Card debtPaymentFromAccount(int id, CardDto cardDto) {

        Card card = cardRepository.getById(id);
        List<Account> accounts = card.getAccounts();

        for (Account account : accounts) {
            if (account.getAccountType().equals("CHECKING")) {
                double amount = cardDto.getAmount();
                card.setCardDebt(card.getCardDebt() - amount);
                card.setCardLimit(card.getCardLimit() + amount);
                account.setBalance(account.getBalance() - amount);
            }
        }
        cardRepository.save(card);
        return card;
    }

    @Override
    public Card debtPaymentFromCashpoint(int id, CardDto cardDto) {

        try {
            Card card = cardRepository.getById(id);

            if (card.getCardPassword().equals(cardDto.getCardPassword())){
                double amount = cardDto.getAmount();
                card.setCardLimit(card.getCardLimit() + amount);
                card.setCardDebt(card.getCardDebt() - amount);
                cardRepository.save(card);
            }
            return card;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Card password is wrong.");
        }
    }

    @Override
    public List<Expenses> listExtre(int id) {
        Card card = cardRepository.getById(id);
        return card.getExpenses();
    }

    @Override
    public Card sendMoneyForShop(int id, CardDto cardDto){

        Customer customer = customerRepository.getById(id);
        List<Card> cards = customer.getCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = customer.getCards().get(i);
            double amount = cardDto.getAmount();
            int ccv = cardDto.getCcv();
            String cardNo = cardDto.getCardNo();
            String productName = cardDto.getExpenses().get(0).getProductName();
            String productType = cardDto.getExpenses().get(0).getProductType();
            toShoppingFromCard(card, amount, cardNo, ccv, productName, productType);
        }
        return cards.get(0);
    }

    private void toShoppingFromCard(Card card, double amount, String cardNo, int ccv, String pName, String pType){

        try {
            List<Expenses> expenses = card.getExpenses();
            if (card.getCardNo().equals(cardNo) & card.getCcv() == ccv){
                card.setAmount(amount);
                Expenses expensesInfo = new Expenses();
                if (card.getCardType().equals("CREDIT")){

                    for (int i = 0; i < expenses.size(); i++) {

                        expensesInfo.setPrice(amount);
                        expensesInfo.setProductName(pName);
                        expensesInfo.setProductType(pType);
                    }

                    card.setCardDebt(card.getCardDebt() + amount);
                    card.setCardLimit(card.getCardLimit() - amount);

                    expenses.add(expensesInfo);
                }
                else if (card.getCardType().equals("PREPAID")){

                    for (int i = 0; i < expenses.size(); i++) {

                        expensesInfo.setPrice(amount);
                        expensesInfo.setProductName(pName);
                        expensesInfo.setProductType(pType);
                    }

                    card.setCardLimit(card.getCardLimit() - amount);
                    expenses.add(expensesInfo);
                }

                card.setExpenses(expenses);

                cardRepository.save(card);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Card no or Ccv are wrong.");
        }
    }
}
