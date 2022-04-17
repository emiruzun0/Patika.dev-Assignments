package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customer);

    Page<Customer> listAll(Pageable pageable);

    Customer getById(int id);

    CustomerDto updateCustomer(CustomerDto customerDto, int customerId);

    void deleteCustomer(int id);
}
