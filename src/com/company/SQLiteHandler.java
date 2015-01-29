package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SQLiteHandler {

    public static Bank fetchBank() {
        Bank bank;
        String name = "testbank";
        String address = "test address";
        Integer id = 1;
        ResultSet r = fetch("banks", 1);
        try {
            name = r.getString("name");
            address = r.getString("address");
            id = r.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bank = new Bank(name, address, id);
        return bank;
    }

    public static List<Customer> fetchCustomers(int bankid){
        List<Customer> customers = new ArrayList<Customer>();
        try {
            String query = "SELECT * FROM customers WHERE bankid=?";

            PreparedStatement statement = connection().prepareStatement(query);

            statement.setInt(1, bankid);

            statement.setQueryTimeout(30);
            ResultSet r = statement.executeQuery();

            while (r.next()) {
                customers.add(new Customer(r.getString("name"), r.getString("address"), r.getInt("id)")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public static List<Account> fetchAccounts(int bankid){
        List<Account> accounts = new ArrayList<Account>();
        try {
            String query = "SELECT * FROM accounts WHERE bankid=?";

            PreparedStatement statement = connection().prepareStatement(query);

            statement.setInt(1, bankid);

            statement.setQueryTimeout(30);
            ResultSet r = statement.executeQuery();

            while (r.next()) {
                if(r.getInt("type") == 1){
                    accounts.add(new LoenKonti(r.getDouble("balance"), r.getDouble("iterestrate"), r.getInt("id"), r.getInt("ownerid")));
                }else if(r.getInt("type") == 2) {
                    if(!r.getBoolean("locked")) {
                        accounts.add(new OpsparingsKonti(r.getDouble("balance"), r.getDouble("iterestrate"), r.getInt("id"), r.getInt("ownerid"), r.getBoolean("locked")));
                    }else {
                        accounts.add(new OpsparingsKonti(r.getDouble("balance"), r.getDouble("iterestrate"), r.getInt("id"), r.getInt("ownerid"), r.getBoolean("locked"), r.getInt("lockedUntil")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


    public static ResultSet fetch(String tablename, int row){
        try {
            String query = "SELECT * FROM tablename WHERE id=?";
            query = query.replace("tablename", tablename);

            PreparedStatement statement = connection().prepareStatement(query);

            statement.setInt(1, row);

            statement.setQueryTimeout(30);
            ResultSet r = statement.executeQuery();

            while (r.next()) {
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/com/company/bankdb.db");
/*
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet r = statement.executeQuery("SELECT * FROM banks");

            while (r.next()) {
                System.out.println(r.getString("name"));
            }
*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
