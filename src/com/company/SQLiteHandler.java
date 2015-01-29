package com.company;

import java.sql.*;

public final class SQLiteHandler {

    public static Bank fetchBank() {
        Bank bank;
        String name = "testbank";
        String address = "test address";
        ResultSet r = fetch("banks", 1);
        try {
            name = r.getString("name");
            address = r.getString("address");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bank = new Bank(name, address);
        return bank;
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
