package com.company;

import java.util.List;

//view & controller
public class managementSystem {
    private Bank bank;
    //region setup and load settings
    public boolean setupBank(){
        //load bank fra sql
        bank = SQLiteHandler.fetchBank();


        if(bank != null) { //hvis banken blev oprettet
            System.out.println(String.format("welcome to bank: %s, with the id: %d", bank.name, bank.id));
            return true;
        }else { //hvis banken ikke blev oprettet
            System.out.println(String.format("welcome to bank: %s, with the id: %d", bank.name, bank.id));
            return false;
        }
    }
    //endregion

    public boolean createNewCustomer(String name, String address){
        return bank.createCustomer(name,address);
    }

    //region create account
    public boolean createNewAccount(double balance, double interestRate, int customerID){
        return bank.createLoenAccount(interestRate, balance, customerID);
    }
    public boolean createNewAccount(double balance, double interestRate, int customerID, boolean locked){
        return bank.createSavingsAccount(interestRate, balance, customerID, locked);
    }
    public boolean createNewAccount(double balance, double interestRate, int customerID, boolean locked, int until){
        return bank.createSavingsAccount(interestRate, balance, customerID, locked, until);
    }
    //endregion

    public boolean transferCash(int acc1ID, int acc2ID, double amount){
        return bank.transferCash(bank.getAccByID(acc1ID), bank.getAccByID(acc2ID), amount);
    }

    public List<Customer> listCustomers(){
        return bank.customers;
    }
    public List<Account> listAccount(){
        return bank.accounts;
    }

}
