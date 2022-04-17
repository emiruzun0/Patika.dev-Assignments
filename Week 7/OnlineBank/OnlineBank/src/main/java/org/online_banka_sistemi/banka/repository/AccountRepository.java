package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Integer> {


    Page<Account> findAll(Pageable pageable);

    Account getById(int id);

}
