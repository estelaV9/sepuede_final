module com.example.sepuede_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

    opens com.example.sepuede_final to javafx.fxml;
    exports com.example.sepuede_final;
}