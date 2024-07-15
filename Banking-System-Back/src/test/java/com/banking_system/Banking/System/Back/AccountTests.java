package com.banking_system.Banking.System.Back;

import com.banking_system.Banking.System.Back.models.Account;
import com.banking_system.Banking.System.Back.models.BonusAccount;
import com.banking_system.Banking.System.Back.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountTests {
    @Autowired
    AccountService accountService;
    @Test
    void createCurrentAccountTest(){
        accountService.createCurrentAccount(1);
        Account account = accountService.getAccount(1);
        assertThat(account).isNotNull();
    }
    @Test
    void createBonusAccountTest(){
        accountService.createBonusAccount(2);
        Account account = accountService.getAccount(2);
        assertThat(account).isNotNull();
    }

    @Test
    void createSavingsAccountTest(){
        accountService.createSavingsAccount(3, 0);
        Account account = accountService.getAccount(3);
        assertThat(account).isNotNull();
    }

    @Test
    void getCurrentAccountTest(){
        Account account = accountService.getAccount(1);
        assertThat(account).isNotNull();
    }

    @Test
    void getBonusAccountTest(){
        Account account = accountService.getAccount(2);
        assertThat(account).isNotNull();
    }

    @Test
    void getSavingsAccountTest(){
        Account account = accountService.getAccount(3);
        assertThat(account).isNotNull();
    }

    @Test
    void getBalanceCurrentAccountTest() throws IllegalAccessException {
        accountService.createCurrentAccount(4);
        accountService.addCredit(4, 100);
        float balance = accountService.getAccountBalance(4);
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void getBalanceBonusAccountTest() throws IllegalAccessException {
        accountService.createBonusAccount(5);
        accountService.addCredit(5, 100);
        float balance = accountService.getAccountBalance(5);
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void getBalanceSavingsAccountTest() throws IllegalAccessException {
        accountService.createSavingsAccount(6, 0);
        accountService.addCredit(6, 100);
        float balance = accountService.getAccountBalance(6);
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void creditTest() throws IllegalAccessException {
        accountService.createCurrentAccount(7);
        Account account = accountService.getAccount(7);
        accountService.addCredit(7, 100);
        assertThat(account.getBalance()).isEqualTo(100);
        accountService.addCredit(7, 100);
        assertThat(account.getBalance()).isEqualTo(200);
    }
    @Test
    void negativeCreditTest() throws IllegalAccessException {
        accountService.createCurrentAccount(8);
        assertThrows(IllegalAccessException.class, () ->{
            accountService.addCredit(8, -100);
        });
    }

    @Test
    void bonusAfterCreditTest() throws IllegalAccessException {
        accountService.createBonusAccount(9);
        Account account = accountService.getAccount(9);
        assertThat(((BonusAccount) account).getBonus()).isEqualTo(10);
        accountService.addCreditBonusAccount(9, 540);
        assertThat(((BonusAccount) account).getBonus()).isEqualTo(15);
    }

    @Test
    void debitTest() throws IllegalAccessException {
        accountService.createBonusAccount(10);
        accountService.addCredit(10, 100);
        accountService.debitFromAccount(10, 24);
        Account account = accountService.getAccount(10);
        assertThat(account.getBalance()).isEqualTo(76);
    }

    @Test
    void negativeDebitTest() throws IllegalAccessException {
        accountService.createBonusAccount(11);
        accountService.addCredit(11, 100);
        assertThat(accountService.debitFromAccount(11, -24)).isEqualTo(-1);
    }

    @Test
    void negativeBalanceDebitTest() throws IllegalAccessException {
        accountService.createBonusAccount(12);
        accountService.addCredit(12, 100);
        assertThrows(IllegalAccessException.class, () -> {
            accountService.debitFromAccount(12, 1124);
        });
    }

    void createSavingsAccounts(){
        accountService.createSavingsAccount(13, 0);
        accountService.createSavingsAccount(14, 100);
        accountService.createSavingsAccount(15, 150);
    }


    @Test
    void yieldInterestTest() throws IllegalAccessException {
        createSavingsAccounts();
        Account account1 = accountService.getAccount(13);
        Account account2 = accountService.getAccount(14);
        Account account3 = accountService.getAccount(15);

        accountService.yieldInterest(12.13f);
        assertThat(account1.getBalance()).isEqualTo(0);
        assertThat(account2.getBalance()).isCloseTo(112.13f, within(0.001f));
        assertThat(account3.getBalance()).isCloseTo(168.195f, within(0.001f));
    }
}
