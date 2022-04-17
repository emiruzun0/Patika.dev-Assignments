package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;

import org.kodluyoruz.mybank.service.CustomerService;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.transformer.CustomerTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/customer")
public class CustomerController {


    private final CustomerTransformer customerTransformer;

    private final CustomerService customerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto customerDto){
        try {
            return customerService.createCustomer(customerDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer is not created");
        }

    }

    @GetMapping(params = {"page","size"})
    public List<CustomerDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        try {
            return customerService.listAll(PageRequest.of(page, size)).stream()
                    .map(customerTransformer::toCustomerDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no customer in database");
        }
    }

    @GetMapping(value = "/{id}")
    public Customer customerById(@PathVariable int id){
        try {
            return customerService.getById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id"+id);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable int id, @RequestBody CustomerDto customerDto){
        try {
            return customerService.updateCustomer(customerDto, id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer is not updated with id "+id);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
            customerService.deleteCustomer(id);
    }

}
