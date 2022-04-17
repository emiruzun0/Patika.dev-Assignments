package org.kodluyoruz.mybank.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Address;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;

    private String fullName;

    private String TC;

    private String password;

    private String description;

    private String email;

    private String phone;

    @CreationTimestamp
    private LocalDate createdDate;

    private Address address;

    private List<AccountDto> accounts;

    private List<CardDto> cards;
}
