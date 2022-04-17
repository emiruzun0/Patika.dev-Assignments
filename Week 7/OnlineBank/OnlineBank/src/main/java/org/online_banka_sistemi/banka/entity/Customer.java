package org.kodluyoruz.mybank.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String TC;

    private String password;

    private String description;

    private String email;

    private String phone;

    @CreationTimestamp
    private LocalDate createdDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Card> cards;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Account> accounts;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Override
    public String toString() {
        return "";
    }
}
