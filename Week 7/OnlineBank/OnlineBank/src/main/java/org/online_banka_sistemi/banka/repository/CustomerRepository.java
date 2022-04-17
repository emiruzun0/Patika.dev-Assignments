package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Page<Customer> findAll(Pageable page);

    Customer getById(int id);

}
