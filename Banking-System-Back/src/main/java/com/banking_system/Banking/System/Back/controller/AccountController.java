package com.banking_system.Banking.System.Back.controller;

import com.banking_system.Banking.System.Back.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    public AccountService accountService;

    /* CONSULTA SALDO */
    @RequestMapping("/get_balance")
    public ResponseEntity<Integer> getAccountBalance(@RequestParam int accountNumber) {
        try {
            int account_balance = accountService.getAccountBalance(accountNumber);
            return new ResponseEntity<Integer>(account_balance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
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
            return new ResponseEntity<>("Crédito adicionado com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
