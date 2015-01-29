package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHandler {

    public SQLiteHandler(){
        Class.forName("org.sqlite.JDBC");
    }

}
