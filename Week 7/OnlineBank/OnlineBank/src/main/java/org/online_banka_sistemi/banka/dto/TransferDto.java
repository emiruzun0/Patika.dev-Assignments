package org.kodluyoruz.mybank.dto;

import lombok.*;
import org.kodluyoruz.mybank.entity.Account;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    private int id;

    private double amount;

    private String senderIban;

    private String receiverIban;

    private String currency;

    private String accountType;

    private LocalDate date;

    private List<Account> accounts;

}
