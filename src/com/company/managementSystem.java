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
            return true;
        }else { //hvis banken ikke blev oprettet
            return false;
        }
    }
    public boolean loadSettings(){
        //load settings, lige nu sætter den kun nogle statiske test variabler ind, senere kunne den importere fra en sql database e.l.
        bank.createCustomer("gertrud", "viborgvej 1");
        bank.createCustomer("gert", "viborgvej 2");
        bank.createCustomer("viktor", "viborgvej 3");
        bank.createCustomer("vicky", "viborgvej 4");

        bank.createLoenAccount(1000.0, 0.3,1);
        bank.createLoenAccount(10500.0, 0.1,2);
        bank.createSavingsAccount(1500.0, 0.7,3, false);
        bank.createSavingsAccount(523200.0, 0.5,4, false);

        if(bank.accounts.isEmpty() || bank.customers.isEmpty()){
            //hvis bankens accounts og customers lister er tomme gik der et eller andet galt
            return false;
        }else {
            return true;
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
