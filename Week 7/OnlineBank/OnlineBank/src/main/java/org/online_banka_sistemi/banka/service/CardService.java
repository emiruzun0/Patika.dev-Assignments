package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Expenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface CardService {

    Page<Card> list(Pageable pageable);

    void addCart(int id, CardDto cardDto);

    HashMap<String,Double> findAllCardDept(int id);

    Card sendMoneyForShop(int id, CardDto cardDto);

    Card getById(int id);

    Card debtPaymentFromAccount(int id, CardDto cardDto);

    Card debtPaymentFromCashpoint(int id, CardDto cardDto);

    List<Expenses> listExtre(int id);

}
