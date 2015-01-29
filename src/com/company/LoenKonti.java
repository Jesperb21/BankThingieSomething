package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.DoubleSummaryStatistics;

/**
 * Created by user on 27-01-2015.
 */
public class LoenKonti extends Account{
    public LoenKonti(Double Balance, Double InterestRate, int CustomerID, int AccountID){
        balance = Balance;
        interestRate = InterestRate;
        customerID = CustomerID;
        accountID = AccountID;
    }

    @Override
    public double GetBalance() {
        return balance;
    }

    @Override
    public String GetAccountName() {
        throw new NotImplementedException();
    }

    @Override
    public double GetInterestRate() {
        return interestRate;
    }

    @Override
    public int GetCustomerID() {
        return customerID;
    }

    @Override
    public boolean Withdraw(double cash) {
        if(balance >= cash){//check again on the "server" to make sure the account can do this
            balance-= cash;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean Deposit(double cash) {
        //check if the account supports more cash maybe?
        balance += cash;
        return true;
    }
    /*
        +withdraw(double):bool
        +deposit(double):bool
     */
}
