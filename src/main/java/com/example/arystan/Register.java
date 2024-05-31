package com.example.arystan;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.arystan.DataPupil.DataPupil;
import com.example.arystan.DataStudent.DataStudent;
import com.example.arystan.DataStudent.Shake.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bookImage;

    @FXML
    private ImageView bookToUser;

    @FXML
    private ImageView login;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;



    @FXML
    private Button signupButton;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField userNameField;
    private String myDelivery;
    @FXML
    private ChoiceBox<String> who;
    private String[] w = {"Student","Pupil"};
    @FXML
    void initialize() {
        Settings set = new Settings();

        set.image(bookToUser,"registerBook.fxml");
        set.image(login,"login.fxml");


        who.getItems().addAll(w);
        who.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Student")) {
                myDelivery = "Student";
            } else {
                myDelivery = "Pupil";
            }
        });
        signupButton.setOnAction(event -> {

            writeData(userNameField.getText(),passwordField.getText(),myDelivery);
        });

    }
    private void writeData(String loginText, String loginPassword,String as) {



            if (as.equals("Student")) {
                DataStudent dbHandler = new DataStudent();

                Student student = new Student();
                student.setUsername(loginText);
                student.setPassword(loginPassword);

                ResultSet result = dbHandler.getUser(student);

                int c = 0;

                while (true){
                    try {
                        if (!result.next())

                            break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    c++;
                }
                if(c >=1){
                    Shake loginH = new Shake(userNameField);
                    Shake pas = new Shake(passwordField);
                    Shake name1 = new Shake(nameField);
                    Shake  surnam = new Shake(surnameField);
                    Shake balanc = new Shake(who);


                    loginH.playAnim();
                    name1.playAnim();
                    surnam.playAnim();
                    balanc.playAnim();
                    pas.playAnim();


                }


                else{
                    DataStudent databaseHandler = new DataStudent();

                    String name = nameField.getText();
                    String surname = this.surnameField.getText();
                    String username = this.userNameField.getText();
                    String password = passwordField.getText();
                    String who = "Student";

                    Student student1 = new Student(name,surname,who,username,password);
                    databaseHandler.signUpUser(student1);


                }

            }
            else{
                DataPupil dbHandler = new DataPupil();

                Pupil pupil = new Pupil();
                pupil.setUsername(loginText);
                pupil.setPassword(loginPassword);

                ResultSet result = dbHandler.getUser(pupil);

                int c = 0;

                while (true){
                    try {
                        if (!result.next())

                            break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    c++;
                }
                if(c >=1){
                    Shake loginH = new Shake(userNameField);
                    Shake pas = new Shake(passwordField);
                    Shake name1 = new Shake(nameField);
                    Shake  surnam = new Shake(surnameField);
                    Shake balanc = new Shake(who);


                    loginH.playAnim();
                    name1.playAnim();
                    surnam.playAnim();
                    balanc.playAnim();
                    pas.playAnim();


                }


                else{
                    DataPupil databaseHandler = new DataPupil();

                    String name = nameField.getText();
                    String surname = this.surnameField.getText();
                    String username = this.userNameField.getText();
                    String password = passwordField.getText();
                    String who = "Pupil";

                    Pupil pupil1 = new Pupil(name,surname,who,username,password);
                    databaseHandler.signUpUser(pupil1);


                }
            }

        }

    }


