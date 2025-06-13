package com.bank.user.controller;

import com.bank.user.entity.AccountEntity;
import com.bank.user.repository.AccountRepository;
import com.bank.user.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping("/register-account")
    public String registerAccount(Model model){
        model.addAttribute("account", new AccountEntity());
        return "create-account";
    }

    @PostMapping("/create-account")
    public String createAccount(AccountEntity accountEntity,  Model model){
        System.out.println(accountEntity);
        AccountEntity accountSave = accountService.addAccount(accountEntity);

        model.addAttribute("token", accountSave.getToken());
        model.addAttribute("account", new AccountEntity()); //

        return "create-account";
    }

    @GetMapping("/view-account")
    public String viewAccount(Model model){
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("totalAccounts", accountRepository.count());
        return "view-account";
    }

}
