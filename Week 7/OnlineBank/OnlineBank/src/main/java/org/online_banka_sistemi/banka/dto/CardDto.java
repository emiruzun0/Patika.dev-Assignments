package org.kodluyoruz.mybank.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Expenses;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private int id;

    private String cardNo;

    private String cardType;

    private double amount;

    private String cardPassword;

    private double cardLimit;

    private List<Expenses> expenses;

    private double cardDebt;

    private LocalDate expiredDate;

    private int ccv;

    @CreationTimestamp
    private LocalDate createdDate;

    private List<AccountDto> accounts;

}
