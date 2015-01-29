package com.company;

/**
 * Created by user on 27-01-2015.
 */
public interface IAccount {
    double GetBalance();
    String GetAccountName();
    double GetInterestRate();
    int GetCustomerID();
    boolean Withdraw(double cash);
    boolean Deposit(double cash);
}
