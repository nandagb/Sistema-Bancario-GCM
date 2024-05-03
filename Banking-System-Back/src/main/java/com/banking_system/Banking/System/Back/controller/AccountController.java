package com.banking_system.Banking.System.Back.controller;

import com.banking_system.Banking.System.Back.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    public AccountService accountService;

    /* CRIA AS CONTAS */
    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, Integer> accountNumber){
        try {
            accountService.createAccount(accountNumber.get("AccountNumber"));
            return new ResponseEntity<>("Conta criada com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ADICIONA CREDITO NA CONTA */
    @PostMapping("/credit")
    public ResponseEntity<String> addCredit(@RequestBody Map<String, Integer> options){
        try{

            accountService.addCredit(options.get("AccountNumber"), options.get("Value"));
            return new ResponseEntity<>("Conta criada com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
