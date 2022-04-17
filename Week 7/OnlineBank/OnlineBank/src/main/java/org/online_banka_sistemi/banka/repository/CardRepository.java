package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Expenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    Page<Card> findAll(Pageable page);

    Card getById(Integer id);

}
