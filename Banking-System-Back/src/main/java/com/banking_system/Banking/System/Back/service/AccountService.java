package com.banking_system.Banking.System.Back.service;

import com.banking_system.Banking.System.Back.models.Account;
import com.banking_system.Banking.System.Back.models.BonusAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<Account>();

    public int getAccountBalance(int accountNumber) {
        Boolean found = false;
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                found = true;
                return account.getBalance();
            }
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        return -1;
    }

    public void createAccount(int accountNumber){
        Account newAccount = new Account();
        newAccount.setNumber(accountNumber);
        newAccount.setBalance(0);
        accounts.add(newAccount);
    }

    public void createBonusAccount(int accountNumber){
        Account newAccount = new BonusAccount(accountNumber);
        newAccount.setNumber(accountNumber);
        newAccount.setBalance(0);
        accounts.add(newAccount);
    }

    public Account getAccount(int accountNumber){
        Boolean found  = false;
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                found = true;
                return account;
            }
        }

        return null;
    }
    public int debitFromAccount(int accountNumber, int value) throws IllegalAccessException {
        Account account = getAccount(accountNumber);

        if (account == null) {
            throw new NoSuchElementException();
        }

        int newBalance = account.getBalance() - value;

        if (newBalance < 0) {
            throw new IllegalAccessException();
        } else {
            account.setBalance(newBalance);
            return newBalance;
        }
    }

    public Boolean transfer(int accountNumberOrigin, int accountNumberDestination, int value) throws NoSuchElementException, IllegalAccessException {
        if(accountNumberOrigin == accountNumberDestination){
            return false;
        }

        Account origin = getAccount(accountNumberOrigin);
        Account destination = getAccount(accountNumberDestination);

        if (origin == null || destination == null) {
            throw new NoSuchElementException();
        }

        int newBalanceOrigin = origin.getBalance() - value;
        int newBalanceDestination = destination.getBalance() + value;

        if(destination.getType().equals("bonus_account")){
            BonusAccount bonusAccount = (BonusAccount) destination;
            bonusAccount.setBonus(bonusAccount.getBonus() + Math.floorDiv(value, 200));
        }

        if (newBalanceOrigin < 0) {
            throw new IllegalAccessException();
        } else {
            origin.setBalance(newBalanceOrigin);
            destination.setBalance(newBalanceDestination);
        }

        return true;

    }
    public void addCredit(int accountNumber, int creditValue){
        Account account = accounts.stream().filter(acc -> acc.getNumber() == accountNumber).toList().get(0);
        account.setBalance(account.getBalance() + creditValue);
    }

    public void addCreditBonusAccount(int accountNumber, int creditValue){
        BonusAccount account = (BonusAccount) accounts.stream().filter(acc -> acc.getNumber() == accountNumber).toList().get(0);
        account.setBalance(account.getBalance() + creditValue);
        account.setBonus(account.getBonus() + Math.floorDiv(creditValue, 100) );
    }
}
