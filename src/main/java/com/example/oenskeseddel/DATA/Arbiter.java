package com.example.oenskeseddel.DATA;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Arbiter {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ønskeliste", "root", "123");
            System.out.println("we linked");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement statement;
    Statement statementResultSet;
    Statement statement3;

    {
        try {
            statement = connection.createStatement();
            statementResultSet = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement3 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createUser(String email, String Username, String Password, String PasswordRE) throws SQLException {
                Pattern pat = Pattern.compile("^(.+)@(.+)$");
        if(Objects.equals(Password, PasswordRE) || pat.matcher(email).matches()){
            statement.execute("INSERT INTO brugerliste (email,usernavn,password) VALUES ('"+email+"', '"+Username+"', '"+Password+"');");
        return true;
        }
        return false;
    }


    public int confirmLogIn(String Username,String Password) throws SQLException {
        int UserID = 1;
        ResultSet resultSetUserPassword = statementResultSet.executeQuery("SELECT id,usernavn,password FROM brugerliste");
        while (resultSetUserPassword.next()) {
            if (Password.equals(resultSetUserPassword.getString("password")) || Username.equals(resultSetUserPassword.getString("usernavn"))) {
                UserID = resultSetUserPassword.getInt("id");
            }
        }
        return UserID;
    }


        public void addWishToWishlistFromView(String wish, int UserID) throws SQLException {
        statement.execute("INSERT INTO `ønskeliste`.`wishlists` (`id`, `wish`) VALUES ('"+UserID+"', '"+wish+"');");
    }


    public ArrayList postWishListToView(int UserID) throws SQLException {
        ArrayList<String> arrayList = new ArrayList();
        ResultSet resultSet = statementResultSet.executeQuery("SELECT wish,id FROM wishlists");
        while(resultSet.next()){
            if(resultSet.getInt("id") == UserID) {
                arrayList.add(resultSet.getString("wish"));
            }
        }

        return arrayList;
    }




}