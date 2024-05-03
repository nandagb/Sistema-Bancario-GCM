package com.banking_system.Banking.System.Back.service;

import com.banking_system.Banking.System.Back.models.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<Account>();

    public void createAccount(int accountNumber){
        Account newAccount = new Account();
        newAccount.setNumber(accountNumber);
        newAccount.setBalance(0);
        accounts.add(newAccount);
    }
}
