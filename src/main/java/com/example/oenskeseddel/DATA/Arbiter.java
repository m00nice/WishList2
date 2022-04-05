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
    Statement statement2;
    Statement statement3;

    {
        try {
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String email, String Username, String Password, String PasswordRE) throws SQLException {
                Pattern pat = Pattern.compile("^(.+)@(.+)$");
        if(Objects.equals(Password, PasswordRE) || pat.matcher(email).matches()){
            statement.execute("INSERT INTO brugerliste (email,usernavn,password) VALUES ('"+email+"', '"+Username+"', '"+Password+"');");

            ResultSet resultSetUser = statement.executeQuery("SELECT usernavn FROM brugerliste");
            ResultSet resultSetPassword = statement2.executeQuery("SELECT password FROM brugerliste");
            ResultSet resultSetID = statement3.executeQuery("SELECT id FROM brugerliste");
            resultSetID.beforeFirst();
            resultSetPassword.beforeFirst();
            resultSetUser.beforeFirst();
            while(resultSetPassword.next() || resultSetUser.next() || resultSetID.next()) {
                if (resultSetPassword.getString("password").equals(Password) || resultSetUser.getString("usernavn").equals(Username)) {
                    int UserID = resultSetID.getInt("id");
                    statement.execute("CREATE TABLE 'ØNSKELISTE'.'wishlist"+UserID+"'('wish'VARCHAR(64)NOT NULL);");
                }

            }




        }
    }

    public boolean confirmLogIn(String Username,String Password) throws SQLException {
        boolean confirmation = false;
        ResultSet resultSetUser = statement.executeQuery("SELECT usernavn FROM brugerliste");
        ResultSet resultSetPassword = statement.executeQuery("SELECT password FROM brugerliste");
        while (resultSetPassword.next()||resultSetUser.next()) {
            if (Password.equals(resultSetPassword.getString("password")) || Username.equals(resultSetUser.getString("usernavn"))) {
                confirmation = true;
            }
        }
        return confirmation;
    }


    public void createWishlistToModel(int UserID) throws SQLException {
        statement.execute("CREATE TABLE 'ØNSKELISTE'.'wishlist"+UserID+"'('wish'VARCHAR(64)NOT NULL);");
    }

    public void addWishToWishlistFromView(String wish, int UserID) throws SQLException {
        statement.execute("INSERT INTO  wishlist"+UserID+"(wish) VALUES ("+wish+")");
    }


    public ArrayList postWishListToView(int UserID) throws SQLException {
        ArrayList<String> arrayList = new ArrayList();
        ResultSet resultSet = statement.executeQuery("SELECT wish FROM wishlist"+UserID);
        while(resultSet.next()){
            arrayList.add(resultSet.getString("wish"));
        }

        return arrayList;
    }




}