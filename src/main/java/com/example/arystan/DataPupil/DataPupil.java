package com.example.arystan.DataPupil;

import com.example.arystan.Config;
import com.example.arystan.DataStudent.ConstUser;
import com.example.arystan.Pupil;
import com.example.arystan.Student;

import java.sql.*;
    public class DataPupil extends Config {
        public Connection getDbConnection() throws ClassNotFoundException, SQLException {
            String connectionString = "jdbc:mysql:// " + dbHost +":"
                    +dbPort+"/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionString, dbUser, dbPass);
        }

        public void signUpUser(Pupil user) throws RuntimeException {
            String insert  = "INSERT INTO " + ConstPupil.USER_TABLE + " (" +
                    ConstPupil.USERS_NAME + "," + ConstPupil.USERS_SURNAME + "," +
                    ConstPupil.USER_USERNAME + "," + ConstPupil.USERS_PASSWORD + "," +
                    ConstPupil.USERS_WHO + ")" + " VALUES (?,?,?,?,?)";

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

        public ResultSet getUser(Pupil user ){
            ResultSet resSet = null;

            String select = "SELECT * FROM " + ConstPupil.USER_TABLE + " WHERE " +
                    ConstPupil.USER_USERNAME + "=? AND " + ConstPupil.USERS_PASSWORD + "=?";
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

