module com.example.arystan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.arystan to javafx.fxml;
    exports com.example.arystan;
}