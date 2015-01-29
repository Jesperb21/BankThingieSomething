package com.company;

import java.util.Scanner;

public class Main {
    private static void println(){    //i got tired of writing System.out.println all the time =')
        println("");
    }
    private static void println(String msg){    //i got tired of writing System.out.println all the time =')
        System.out.println(msg);
    }
    private static void print(String msg){    //i got tired of writing System.out.println all the time =')
        System.out.print(msg);
    }
    public static void main(String[] args) {
        // write your code here
        managementSystem m = new managementSystem();
        if(m.setupBank()) {
            println("banken er oprettet og settings er importeret succesfylt");
            println();
            println("-----------------------------------------------------------------------------------");
            println();

            println("okay, lets get serius");
            println();
            boolean repeatMenu = true;
            while (repeatMenu) {
                println("0: exit");
                println("1: opret customer");
                println("2: opret accounts");
                println("3: list customers");
                println("4: list accounts");
                println("5: transfer cash");
                println();
                println("select an option: ");


                Scanner scanner = new Scanner(System.in);

                switch (scanner.nextInt()) {
                    case 0://exit
                        repeatMenu = false;
                        break;
                    case 1://create customer
                        println("----------new customer----------");
                        println();
                        print("customer name:");
                        String name = scanner.next();
                        print("customer address:");
                        String address = scanner.next();
                        println("creating account...");
                        println();
                        if(m.createNewCustomer(name, address)){
                            println("account created");
                        }else {
                            println("something went wrong, try again later");
                        }
                        println();
                        println("--------------------------------");
                        break;
                    case 2://create account
                        println("----------new account----------");
                        println();
                        print("account type(1: loenkonti, 2:opsparingskonti)?");
                        int customerId;
                        double balance, interestRate;
                        switch (scanner.nextInt()){
                            case 1:
                                println("-----loenkonti-----");
                                println("customer id?");
                                customerId=scanner.nextInt();
                                println("balance?");
                                balance=scanner.nextDouble();
                                println("interest rate?");
                                interestRate=scanner.nextDouble();
                                println();
                                println("creating account...");
                                if(m.createNewAccount(balance, interestRate, customerId)){
                                    println("account created!");
                                }else {
                                    println("something went wrong, try again later");
                                }
                                println("-------------------");
                                break;
                            case 2:
                                println("----opsparingskonti-----");
                                println("customer id?");
                                customerId=scanner.nextInt();
                                println("balance?");
                                balance=scanner.nextDouble();
                                println("interest rate?");
                                interestRate=scanner.nextDouble();
                                println("locked?(TRUE/FALSE)");
                                boolean locked = scanner.nextBoolean();
                                if(locked){
                                    println("locked until?");
                                    int until = scanner.nextInt();

                                    println();
                                    println("creating account...");
                                    if (m.createNewAccount(balance, interestRate, customerId, locked, until)) {
                                        println("account created!");
                                    } else {
                                        println("something went wrong, try again later");
                                    }
                                }else {
                                    println();
                                    println("creating account...");
                                    if (m.createNewAccount(balance, interestRate, customerId, locked)) {
                                        println("account created!");
                                    } else {
                                        println("something went wrong, try again later");
                                    }
                                }


                                println("------------------------");
                                break;
                            default:
                                println("uuuuh... didnt get that, try again");
                                break;
                        }

                        println("-------------------------------");
                        break;
                    case 3://list customers
                        println();
                        println("---------Customers--------");
                        println();
                        System.out.printf("%-12s%-12s%s\n","id","name","address");
                        for (Customer customer : m.listCustomers()) {
                            System.out.printf("%-12s%-12s%s\n", customer.customerID, customer.name, customer.address);
                        }
                        println("--------------------------");
                        println();
                        break;
                    case 4://list accounts
                        println();
                        println("---------Accounts--------");
                        System.out.printf("%-12s%-12s%s\n", "owner id", "balance", "interestRate");
                        println();
                        for (Account account : m.listAccount()) {
                            System.out.printf("%-12s%-12s%s\n", account.customerID, account.balance,account.interestRate);
                        }
                        println("--------------------------");
                        println();
                        break;
                    case 5://transfer cash
                        break;
                }
            }

            //todo - opret account          done
            //todo - list bruger            done
            //todo - list accounts          done
            //todo - deposit & withdraw     done-ish
            //todo - transfer               done
            //todo - SQL                    done-ish

            /*
        String BankName;
        String BankAddress;
        Scanner user_input = new Scanner(System.in); //linjen ind i programmet fra consollen

        System.out.print("insert name for bank:");
        BankName = user_input.next(); //det næste der bliver skrevet, vent indtil enter bliver trykket på
        System.out.println("name: "+BankName);
        System.out.print("bank address:");
        BankAddress = user_input.next(); //det næste der bliver skrevet, vent indtil enter bliver trykket på (igen)
        System.out.println("address: "+BankAddress);

        bank = new Bank(BankName, BankAddress); //opret en ny bank med data inputtet fra useren ovenfor
        */
        }
    }

}
