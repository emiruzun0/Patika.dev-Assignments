package org.kodluyoruz.mybank;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CardRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    private final String url = "/v1/api/card";

    @Test
    public void testListCard() throws Exception{
        mockMvc.perform(get(url+"?page=0&size=20").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddCard() throws Exception{

        List<Account> accounts = new ArrayList<>();

        List<Card> cards = new ArrayList<>();

        Card card = new Card();
        card.setId(2);
        card.setCardNo("1233 1234 1234 1234");
        card.setCcv(123);
        card.setCardLimit(2000);
        card.setCardType("CREDIT");
        card.setCardPassword("1234");
        card.setCardDebt(20);
        card.setAccounts(accounts);
        card.setCreatedDate(LocalDate.now());

        cards.add(card);

        Customer customer = new Customer();

        customer.setId(1);
        customer.setPassword("123");
        customer.setDescription("description");
        customer.setPhone("1234567");
        customer.setEmail("customer@mail.com");
        customer.setCards(cards);

        customerRepository.save(customer);

        mockMvc.perform(put(url+"/custno/{id}",1).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCardDebt() throws Exception {
        mockMvc.perform(get(url+"/debtamount/{id}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCardEkstre() throws Exception{
        mockMvc.perform(get(url+"/ekstre/{id}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
