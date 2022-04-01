package com.example.oenskeseddel.DATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class UserDatahandler {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "m00nice", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSyntax(String syntax) throws SQLException {
        statement.execute(syntax);
    }

    public void OpretBruger(String email, String username, String password) throws SQLException {
        statement.execute("USE ØNSKELISTE");
        statement.execute("INSERT INTO BRUGERLISTE (email, username , password) VALUES ("+email+", "+username+", "+password+")");

    }

    public void LogInd() throws SQLException {
        statement.execute("USE ØNSKELISTE");
        statement.execute("");
    }


    public boolean isEmailValid(String parameter) {
        Pattern pat = Pattern.compile("^(.+)@(.+)$");
        return pat.matcher(parameter).matches();
    }
}
