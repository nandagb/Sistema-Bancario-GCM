package com.banking_system.Banking.System.Back.service;

import com.banking_system.Banking.System.Back.models.Account;
import com.banking_system.Banking.System.Back.models.SavingsAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<Account>();

    public float getAccountBalance(int accountNumber) {
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

    public void createCurrentAccount(int accountNumber){
        Account newAccount = new Account();
        newAccount.setNumber(accountNumber);
        newAccount.setBalance(0);
        newAccount.setType("current");
        accounts.add(newAccount);
    }

    public void createSavingsAccount(int accountNumber){
        SavingsAccount newAccount = new SavingsAccount();
        newAccount.setNumber(accountNumber);
        newAccount.setBalance(0);
        newAccount.setType("savings");
        accounts.add(newAccount);
    }

    public ArrayList<Account> yieldInterest(float interest_rate_percentage){
        Float rate = interest_rate_percentage / 100;
        ArrayList<Account> updated_accounts = new ArrayList<>();
        for(Account account : this.accounts){
            if(account.getType().equals("savings")){
                Float new_balance = account.getBalance() * (rate+1);
                account.setBalance(new_balance);
                updated_accounts.add(account);
            }
        }
        return updated_accounts;
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
    public float debitFromAccount(int accountNumber, int value) throws IllegalAccessException {
        Account account = getAccount(accountNumber);

        if (account == null) {
            throw new NoSuchElementException();
        }

        float newBalance = account.getBalance() - value;

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

        float newBalanceOrigin = origin.getBalance() - value;
        float newBalanceDestination = destination.getBalance() + value;

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
}
