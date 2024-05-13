package com.banking_system.Banking.System.Back.controller;

import com.banking_system.Banking.System.Back.models.Account;
import com.banking_system.Banking.System.Back.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class AccountController {
    @Autowired
    public AccountService accountService;

    /* CONSULTA CONTA */
    @CrossOrigin(origins = "*")
    @GetMapping("/get_account")
    public ResponseEntity<Account> getAccount(@RequestParam int AccountNumber) {
        try {
            Account account = accountService.getAccount(AccountNumber);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* CONSULTA SALDO */
    @CrossOrigin(origins = "*")
    @GetMapping("/get_balance")
    public ResponseEntity<Float> getAccountBalance(@RequestParam int accountNumber) {
        try {
            float account_balance = accountService.getAccountBalance(accountNumber);
            return new ResponseEntity<Float>(account_balance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    /* CRIA AS CONTAS CORRENTES */
    @PostMapping("/create/current")
    public ResponseEntity<String> createCurrentAccount(@RequestBody Map<String, Integer> accountNumber){
        try {
            accountService.createCurrentAccount(accountNumber.get("AccountNumber"));
            return new ResponseEntity<>("Conta criada com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    /* CRIA CONTAS BÔNUS */
    @PostMapping("/create/bonus_account")
    public ResponseEntity<String> createBonusAccount(@RequestBody Map<String, Integer> accountNumber){
        try {
            accountService.createBonusAccount(accountNumber.get("AccountNumber"));
            return new ResponseEntity<>("Conta criada com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /* CRIA AS CONTAS POUPANÇA*/
    @CrossOrigin(origins="*")
    @PostMapping("/create/savings")
    public ResponseEntity<String> createSavingsAccount(@RequestBody Map<String, Float> accountInfo){
        try {
            accountService.createSavingsAccount(Math.round(accountInfo.get("AccountNumber")), accountInfo.get("AccountBalance"));
            return new ResponseEntity<>("Conta criada com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    /* RENDER JUROS DE TODAS AS CONTAS POUPANÇA*/
    @PostMapping("/yield_interest")
    public ResponseEntity<ArrayList> yieldInterest(@RequestBody Map<String, Float> interest){
        try {
            Float interest_rate = interest.get("InterestRate");
            ArrayList response = accountService.yieldInterest(interest_rate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* DÉBITO */
    @CrossOrigin(origins = "*")
    @PostMapping("/debit")
    public ResponseEntity<String> debitFromAccount(@RequestBody Map<String, Integer> data) {
        try {
            float newBalance = accountService.debitFromAccount(data.get("AccountNumber"), data.get("Value"));
            return new ResponseEntity<>("Saldo: " + newBalance, HttpStatus.OK);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>("Saldo insuficiente! :( Operação abortada!", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Usuário não encontrado! :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* TRANSFERE DE UMA CONTA À OUTRA */
    @CrossOrigin(origins = "*")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Map<String, Integer> data) {
        try {
            if(accountService.transfer(data.get("AccountNumberOrigin"), data.get("AccountNumberDestination"), data.get("Value"))){
                return new ResponseEntity<>("Operação realizada com sucesso!", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Operação falhou! :( ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }  catch (IllegalAccessException e) {
            return new ResponseEntity<>("Saldo insuficiente! :( Operação abortada!", HttpStatus.INTERNAL_SERVER_ERROR);
        }  catch (NoSuchElementException e) {
            return new ResponseEntity<>("Usuário não encontrado! :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ADICIONA CREDITO NA CONTA */
    @CrossOrigin(origins = "*")
    @PostMapping("/credit")
    public ResponseEntity<String> addCredit(@RequestBody Map<String, Integer> options){
        try{

            accountService.addCredit(options.get("AccountNumber"), options.get("Value"));
            return new ResponseEntity<>("Crédito adicionado com sucesso!", HttpStatus.OK);
        }
        catch (IllegalAccessException e){
            return new ResponseEntity<>("Operação falhou! :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ADICIONA CREDITO NA CONTA BÔNUS */
    @CrossOrigin(origins = "*")
    @PostMapping("/credit/bonus_account")
    public ResponseEntity<String> addCreditBonusAccount(@RequestBody Map<String, Integer> options){
        try{

            accountService.addCreditBonusAccount(options.get("AccountNumber"), options.get("Value"));
            return new ResponseEntity<>("Crédito adicionado com sucesso!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
