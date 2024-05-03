package com.banking_system.Banking.System.Back.service;

import com.banking_system.Banking.System.Back.models.Account;
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
}
