package com.example.arystan.DataBook;

import com.example.arystan.Book;
import com.example.arystan.Config;
import com.example.arystan.DataPupil.ConstPupil;
import com.example.arystan.Pupil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.arystan.DataBook.ConstBook.*;

public class DataBook extends Config {

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql:// " + dbHost +":"
                +dbPort+"/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public void addUpBook(Book book) throws RuntimeException {
        String insert  = "INSERT INTO " + USER_TABLE + " (" +
                ConstBook.BOOKS_TITLE + "," + ConstBook.BOOKS_AUTHOR + "," +
                ConstBook.BOOKS_ISBN + "," + ConstBook.BOOKS_WHOM  + "," + ConstBook.BOOKS_QUANTITY + ")" + " VALUES (?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,book.getTitle());
            prSt.setString(2,book.getAuthor());
            prSt.setString(3,book.getIsbn());
            prSt.setString(4,book.getWho());
            prSt.setString(5,book.getQuantity());



            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(Book book ){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + USER_TABLE + " WHERE " +
                ConstBook.BOOKS_ISBN + "=? AND " + ConstBook.BOOKS_TITLE + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,book.getIsbn());
            prSt.setString(2,book.getTitle());


            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
    public List<Book> getAllBook() {
        List<Book> cakes = new ArrayList<>();
        String select = "SELECT * FROM " + USER_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(BOOKS_TITLE);
                String author = resultSet.getString(BOOKS_AUTHOR);
                String isbn = resultSet.getString(BOOKS_ISBN);
                String whom = resultSet.getString(BOOKS_WHOM);
                String quantity = resultSet.getString(BOOKS_QUANTITY);
                Book book = new Book(name, author, isbn, whom,quantity);
                cakes.add(book);
            }

            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return cakes;
    }
    public ResultSet getBook(Book book ){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + USER_TABLE + " WHERE " +
                ConstBook.BOOKS_TITLE + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1,book.getTitle());


            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

}
