package com.banking_system.Banking.System.Back.service;

import com.banking_system.Banking.System.Back.models.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<Account>();

    public void createAccount(int accountNumber){
        Account newAccount = new Account();
        newAccount.setNumber(accountNumber);
        newAccount.setBalance(0);
        accounts.add(newAccount);
    }

    public void addCredit(int accountNumber, int creditValue){
        Account account = accounts.stream().filter(acc -> acc.getNumber() == accountNumber).toList().get(0);
        account.setBalance(account.getBalance() + creditValue);
    }
}
