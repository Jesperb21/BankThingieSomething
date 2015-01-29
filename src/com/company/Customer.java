package com.company;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

/**
 * Created by user on 27-01-2015.
 */
public class Customer {
    public Customer(String Name, String Address, int CustomerId){
        name = Name;
        address = Address;
        customerID = CustomerId;
    }
    public String address;
    public String name;
    public int customerID;
}
