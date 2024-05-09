package com.banking_system.Banking.System.Back.models;

public class Account {
    private int number;

    private float balance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected String type;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
