package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Expenses;
import org.kodluyoruz.mybank.service.CardService;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.transformer.CardTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Controller
@RestController
@RequestMapping("/v1/api/card")
public class CardController {

    private final CardService cardService;

    private final CardTransformer cardTransformer;

    public CardController(CardService cardService, CardTransformer cardTransformer) {
        this.cardService = cardService;
        this.cardTransformer = cardTransformer;
    }

    @PutMapping(value = "/custno/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody CardDto cardDto, @PathVariable int id){
        try {
            cardService.addCart(id, cardDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card is not created for id"+id);
        }
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(value = HttpStatus.OK)
    public List<CardDto> listCard(@RequestParam("page") int page, @RequestParam("size") int size){
        try {
            return cardService.list(PageRequest.of(page,size)).stream()
                    .map(cardTransformer::toCardDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no card in database");
        }

    }

    @GetMapping(value = "/debtamount/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public HashMap<String,Double> cardDebt(@PathVariable int id){
        try {
            return cardService.findAllCardDept(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Debt is not found with id"+id);
        }
    }

    @PutMapping(value = "/shop/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Card shoppingWithCard(@RequestBody CardDto cardDto, @PathVariable int id){
        try {
            return cardService.sendMoneyForShop(id, cardDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is not created new shopping for id"+id);
        }
    }

    @PutMapping(value = "/carddebtpay/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Card payCardDebt(@RequestBody CardDto cardDto, @PathVariable int id){
        try {
            return cardService.debtPaymentFromAccount(id, cardDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "card dept pay Process is not completed for id"+id);
        }

    }

    @PutMapping(value = "/cashomat/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Card PayCardDebtFromCashpoint(@RequestBody CardDto cardDto, @PathVariable int id){
        try {
            return cardService.debtPaymentFromCashpoint(id, cardDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "card dept pay from cashpoint Process is not completed for id"+id);
        }

    }

    @GetMapping(value = "/ekstre/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Expenses> cardExtre(@PathVariable int id){
        try {
            return cardService.listExtre(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no card Extre info with id"+id);
        }

    }

}
