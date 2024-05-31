package com.example.arystan;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.arystan.DataBook.DataBook;
import com.example.arystan.DataStudent.Shake.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterBook {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField authorField;

    @FXML
    private ImageView bookImage;

    @FXML
    private TextField isbnField;

    @FXML
    private ImageView login;

    @FXML
    private TextField quantityField;
    @FXML
    private ImageView register;

    @FXML
    private ImageView searchBook;

    @FXML
    private TextField titleFiled;
    private String forWhom;
    @FXML
    private ChoiceBox<String> who;
    private String[] w = {"All", "Pupil"};
    @FXML
    void initialize() {
        Settings set =  new Settings();
        set.image(login,"home.fxml");
        set.image(register,"regisetr.fxml");

       who.getItems().addAll(w);
        who.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("All")) {
                forWhom = "All";
            } else {
                forWhom = "Pupil";
            }
        });
        addButton.setOnAction(event -> {

            Book book = new Book(titleFiled.getText(),authorField.getText(),isbnField.getText(),
                    forWhom,quantityField.getText());
            try {
                writeDataBook(book);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
        public void writeDataBook(Book book) throws SQLException {
            DataBook dataBook = new DataBook();

            book.setTitle(titleFiled.getText());
            book.setIsbn(isbnField.getText());
            ResultSet resultSet = dataBook.getUser(book);

            if(!resultSet.next()){
                dataBook.addUpBook(book);
            }
            else{
                Shake t = new Shake(titleFiled);
                Shake i = new Shake(isbnField);
                Shake l = new Shake(authorField);
                Shake  e= new Shake(who);
                Shake  q= new Shake(quantityField);
                t.playAnim();
                i.playAnim();
                l.playAnim();
                e.playAnim();
                q.playAnim();
            }
        }


}
