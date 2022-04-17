package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.TransferDto;
import org.kodluyoruz.mybank.external.ExchangeRates;
import org.kodluyoruz.mybank.entity.Transfer;
import org.kodluyoruz.mybank.service.TransferService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/transfer")
public class TransferController {


    private final TransferService transferService;

    @GetMapping(value = "/exchangerates", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeRates getExchageList(){
        return transferService.exchangeRate();
    }


    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Transfer> getTransferDetails(){
        try{
            return transferService.findAll();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no transaction");
        }
    }

    @PostMapping(value = "/iban")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transfer sendMoney(@RequestBody TransferDto transferDto, @Param(value = "sender") String iban1, @Param(value = "receiver") String iban2){
            return transferService.sendMoney(transferDto, iban1, iban2);
    }
}
