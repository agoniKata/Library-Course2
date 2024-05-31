package com.example.arystan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Home  {

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
    private ImageView registerImage;

    @FXML
    private ImageView searchBook;

    @FXML
    void initialize() {
  Settings settings = new Settings();
  settings.image(login,"login.fxml");
  settings.image(bookToUser,"registerBook.fxml");
        registerImage.setOnMouseClicked(actionEvent ->{
            registerImage.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("regisetr.fxml"));
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
        });
    }


}
