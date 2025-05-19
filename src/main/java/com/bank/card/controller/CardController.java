package com.bank.card.controller;

import com.bank.card.dto.CardDto;
import com.bank.card.enums.TypeCard;
import com.bank.card.service.interfaces.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("card", new CardDto());
        model.addAttribute("typeCards", TypeCard.values());
        return "card";
    }

    @GetMapping("/list")
    public String listCard(Model model) {
        List<CardDto> card = cardService.getAllCards();
        model.addAttribute("cards", card);
        return "list-card";
    }

    @PostMapping("/save-card")
    public String saveCard(CardDto card) {
        cardService.createCard(card);
        return "redirect:/card/list";
    }

}
