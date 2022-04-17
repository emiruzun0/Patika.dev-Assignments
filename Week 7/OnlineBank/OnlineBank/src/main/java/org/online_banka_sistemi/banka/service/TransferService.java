package org.kodluyoruz.mybank.service;


import org.kodluyoruz.mybank.dto.TransferDto;
import org.kodluyoruz.mybank.external.ExchangeRates;
import org.kodluyoruz.mybank.entity.Transfer;

import java.util.List;

public interface TransferService {

    ExchangeRates exchangeRate();

    double usdRate();

    double eurRate();

    List<Transfer> findAll();

    Transfer sendMoney(TransferDto transferDto, String iban1, String iban2);

}
