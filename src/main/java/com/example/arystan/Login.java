package com.example.arystan;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.arystan.DataPupil.DataPupil;
import com.example.arystan.DataStudent.DataStudent;
import com.example.arystan.DataStudent.Shake.Shake;
import com.example.arystan.Global.Global;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bookImage;

    @FXML
    private ImageView bookToUser;

    @FXML
    private ImageView register;



    @FXML
    private Button signupButton;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;
    private String name;
    @FXML
    void initialize() throws SQLException {
        Settings settings = new Settings();
        settings.image(register,"regisetr.fxml");

        settings.image(bookToUser,"registerBook.fxml");

        signupButton.setOnAction(event -> {

            try {
                if(CheckUser(userName.getText(),userPassword.getText())==1){
                    signupButton.getScene().getWindow().hide();
                    Global global = new Global(userName.getText(),userPassword.getText(),name);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("library.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                }
                else {
                    try {
                        if(CheckUser(userName.getText(),userPassword.getText())==2){
                            Global global = new Global(userName.getText(),userPassword.getText(),name);
                            signupButton.getScene().getWindow().hide();

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("library.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.show();
                        }
                        else{
                            Shake user = new Shake(userName);
                            Shake password = new Shake(userPassword);
                            user.playAnim();
                            password.playAnim();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
    public int CheckUser(String as,String sa) throws SQLException {

        DataStudent dataStudent = new DataStudent();
        Student student = new Student();
        student.setUsername(as);
        student.setPassword(sa);
        ResultSet resultStudent = dataStudent.getUser(student);

        DataPupil dataPupil = new DataPupil();
        Pupil pupil = new Pupil();
        pupil.setUsername(as);
        pupil.setPassword(sa);
        ResultSet resultPupil = dataPupil.getUser(pupil);

        if(resultStudent.next()){
            String a = resultStudent.getString("name");
          name = a;
           return  1 ;

        }
        else if(resultPupil.next()){
            String b = resultPupil.getString("name");
            name = b;
            return 2;

        }

        return 0;
    }

}
