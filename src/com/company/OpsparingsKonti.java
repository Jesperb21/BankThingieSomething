package com.company;

import java.util.concurrent.locks.Lock;

/**
 * Created by user on 27-01-2015.
 */
public class OpsparingsKonti extends Account{
    public OpsparingsKonti(Double Balance, double InterestRate, int CustomerID, int AccountID, boolean Locked, int LockedUntil){
        lockedUntil = LockedUntil;
        balance = Balance;
        interestRate = InterestRate;
        customerID = CustomerID;
        accountID = AccountID;
        locked = Locked;
    }
    public OpsparingsKonti(double Balance, double InterestRate, int CustomerID, int AccountID, boolean Locked){
        balance = Balance;
        interestRate = InterestRate;
        customerID = CustomerID;
        accountID = AccountID;
        locked = Locked;
        lockedUntil = 0;
    }
    boolean locked;
    int lockedUntil;

    @Override
    public double GetBalance() {
        return 0;
    }

    @Override
    public String GetAccountName() {
        return null;
    }

    @Override
    public double GetInterestRate() {
        return 0;
    }

    @Override
    public int GetCustomerID() {
        return 0;
    }

    @Override
    public boolean Withdraw(double cash) {
        if(balance >= cash){//check again on the "server" to make sure the account can do this
            if(!locked) {//only withdraw cash if the account isn't locked
                balance -= cash;
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean Deposit(double cash) {
        //check if the account supports more cash maybe?
        if(!locked) {//only deposit cash if the account isn't locked
            balance += cash;
            return true;
        }else {
            return false;
        }
    }

    /*
        +locked:bool
        +lockedUntil:int
     */
}
