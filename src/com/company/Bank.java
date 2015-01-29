package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Bank {
    public Bank(String Name, String Address, Integer Id){
        name = Name;
        address = Address;
        id = Id;
        customers = SQLiteHandler.fetchCustomers(id);
        accounts = SQLiteHandler.fetchAccounts(id);
    }
    String name;
    String address;
    Integer id;
    public List<Customer> customers = new ArrayList<Customer>();
    public List<Account> accounts = new ArrayList<Account>();

    public Account getAccByID(int id){
        for (Account a : accounts){
            if(a.accountID == id){
                return a;
            }
        }
        return null;
    }

    //region sorts
    private void sortAccountList(){//sorts the customer list depending on the id
        Collections.sort(accounts, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Account account1 = (Account) obj1;
                Account account2 = (Account) obj2;
                return account1.customerID-account2.customerID;
            }
        });
    }
    private void sortCustomerList(){//sorts the customer list depending on the id
        Collections.sort(customers, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Customer customer1 = (Customer) obj1;
                Customer customer2 = (Customer) obj2;
                return customer1.customerID-customer2.customerID;
            }
        });
    }
    //endregion

    //region account
    public boolean createLoenAccount(double balance, double interestRate, int customerID){
        int id;
        if(accounts.size() != 0) {
            id = (accounts.get(accounts.size()-1).accountID + 1);
        }else {
            id = 0;
        }
        LoenKonti account = new LoenKonti(balance, interestRate, customerID, id);
        return createAccount(account);
    }
    public boolean createSavingsAccount(double balance, double interestRate, int customerID, boolean locked){
        int id;
        if(accounts.size() != 0) {
            id = (accounts.get(accounts.size()-1).accountID + 1);
        }else {
            id = 0;
        }
        OpsparingsKonti account = new OpsparingsKonti(balance, interestRate, customerID,id, locked);
        return createAccount(account);
    }
    public boolean createSavingsAccount(double balance, double interestRate, int customerID, boolean locked, int until){
        int id;
        sortAccountList();
        if(accounts.size() != 0) {
            id = (accounts.get(accounts.size()-1).accountID + 1);
        }else {
            id = 0;
        }
        OpsparingsKonti account = new OpsparingsKonti(balance, interestRate, customerID,id, locked, until);
        return createAccount(account);
    }
    public boolean createAccount(Account account){
        if(account != null) {
            accounts.add(account);
            return true;
        }else {
            return false;
        }
    }

    //endregion
    //region customer
    public boolean createCustomer(String name, String address){
        sortCustomerList();
        int id;
        if(customers.size() != 0) {
            id = (customers.get(customers.size()-1).customerID + 1);
        }else {
            id = 0;
        }
        Customer customer = new Customer(name, address, id);
        return createCustomer(customer);
    }
    public boolean createCustomer(Customer customer){
        if(customer != null) {
            customers.add(customer);
            return true;
        }else {
            return false;
        }
    }
    //endregion

    //region transfer cash
    public boolean transferCash(Account sender, Account receiver, double amount){
        if (sender.balance >= amount){
            //sender has enough cash to do this
            if(sender.Withdraw(amount)){
                if(receiver.Deposit(amount)){
                    return true;
                }else {
                    //receiver couldn't receive the cash
                    sender.Deposit(amount);
                    return false;
                }
            }else {
                //sender couldn't withdraw enough cash to complete the transfer
                return false;
            }
        }else {
            //sender doesn't have enough cash to do this
            return false;
        }
    }

    //endregion
}
