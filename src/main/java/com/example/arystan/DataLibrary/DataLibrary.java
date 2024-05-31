package com.example.arystan.DataLibrary;

import com.example.arystan.Book;
import com.example.arystan.Config;
import com.example.arystan.DataPupil.ConstPupil;
import com.example.arystan.LibraryHistory;
import com.example.arystan.Pupil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.arystan.DataLibrary.ConstLibrary.*;


public class DataLibrary extends  Config{

        public Connection getDbConnection() throws ClassNotFoundException, SQLException {
            String connectionString = "jdbc:mysql:// " + dbHost +":"
                    +dbPort+"/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionString, dbUser, dbPass);
        }

        public void signUpUser(String name, String bookTitle, String quantity) throws RuntimeException {
            String insert  = "INSERT INTO " + ConstLibrary.USER_TABLE + " (" +
                    ConstLibrary.Library_name + "," + ConstLibrary.Library_book + "," + ConstLibrary.Library_quantity +
                    ")" + " VALUES (?,?,?)";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1,name);
                prSt.setString(2,bookTitle);
                prSt.setString(3,quantity);



                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public ResultSet getUser(String name){
            ResultSet resSet = null;

            String select = "SELECT * FROM" + ConstLibrary.USER_TABLE + " WHERE " +
                    ConstLibrary.Library_name + "=?";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(select);
                prSt.setString(1,name);



                resSet = prSt.executeQuery();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return resSet;
        }
    public List<LibraryHistory> getAllHistory(String nameUser) {
        List<LibraryHistory> history = new ArrayList<>();
        String select = "SELECT * FROM " + USER_TABLE + " WHERE name = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, nameUser);
            ResultSet resultSet = prSt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(Library_name);
                String title = resultSet.getString(Library_book);
                String quantity = resultSet.getString(Library_quantity);
                LibraryHistory libraryHistory = new LibraryHistory(name, title, quantity);
                history.add(libraryHistory);
            }

            resultSet.close();
            prSt.close();
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
        }

        return history;
    }

}

