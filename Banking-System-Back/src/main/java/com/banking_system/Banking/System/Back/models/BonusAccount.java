package com.banking_system.Banking.System.Back.models;

public class BonusAccount extends Account {
    private int bonus;
    public BonusAccount(int number){
        setNumber(number);
        setType("bonus_account");
        this.bonus = 10;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
