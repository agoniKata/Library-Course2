package com.example.arystan.DataStudent;

import com.example.arystan.Config;
import com.example.arystan.Student;

import java.sql.*;

public class DataStudent extends Config {
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql:// " + dbHost +":"
                +dbPort+"/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public void signUpUser(Student user) throws RuntimeException {
        String insert  = "INSERT INTO " + ConstUser.USER_TABLE + " (" +
                ConstUser.USERS_NAME + "," + ConstUser.USERS_SURNAME + "," +
                ConstUser.USER_USERNAME + "," + ConstUser.USERS_PASSWORD + "," +
                 ConstUser.USERS_WHO + ")" + " VALUES (?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getName());
            prSt.setString(2,user.getSurname());
            prSt.setString(3,user.getUsername());
            prSt.setString(4,user.getPassword());
            prSt.setString(5,user.getWho());


            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(Student user ){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstUser.USER_TABLE + " WHERE " +
                ConstUser.USER_USERNAME + "=? AND " + ConstUser.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());


            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
}
