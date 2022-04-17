package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.service.AccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/account")
public class AccountController {


    private final AccountService accountService;


    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        try {
            return accountService.listPageAccount(PageRequest.of(page, size)).stream()
                    .map(Account::toAccountDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no Account in database");
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        try {
            accountService.deleteAccount(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not fount with id:"+id);
        }

    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findByid(@PathVariable int id){
        try {
            return accountService.getById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not fount with id:"+id);
        }

    }

    @PutMapping(value = "/custno/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@PathVariable int id, @RequestBody AccountDto accountDto){
        try {
            accountService.addAccount(id, accountDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account is not created with id"+id);
        }

    }

}
