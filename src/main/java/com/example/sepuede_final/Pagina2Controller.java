package com.example.sepuede_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Pagina2Controller {
    @FXML
    private TextField txtContraseya;

    @FXML
    private PasswordField txtUsuario;

    @FXML
    void AccionAcceder(ActionEvent event) {
        if (txtUsuario.getText().equals("admin") && txtContraseya.getText().equals("admin")) {
            Node source = (Node) event.getSource();
            Stage escena = (Stage) source.getScene().getWindow();
            escena.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("general.fxml"));
            try {
                Parent root = fxmlLoader.load();
                generalController controller = fxmlLoader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.sizeToScene();
                stage.setTitle("Pantalla general");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("error las contraseña o el nombre es incorrecto");
        alert.showAndWait();
    }

}

    @FXML
    void AccionNocuenta(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("general.fxml"));
        try {
            Parent root = fxmlLoader.load();
            generalController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Pagina2");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AccinoCancelar(ActionEvent event) {
        txtContraseya.setText("");
        txtUsuario.setText("");
    }
    @FXML
    void AccionRegistrarseButton(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena2 = (Stage) source.getScene().getWindow();
        escena2.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registrar.fxml"));
        try {
            Parent root = fxmlLoader.load();
            registrarController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("registrarse");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void AccionCerrar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
    }

    @FXML
    void AccionVolver(ActionEvent event) {

    }

    @FXML
    void AccionLogin(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena1 = (Stage) source.getScene().getWindow();
        escena1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Pagina2.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Pagina2Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("log in");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AccionRegistrarse(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena2 = (Stage) source.getScene().getWindow();
        escena2.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registrar.fxml"));
        try {
            Parent root = fxmlLoader.load();
            registrarController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("registrarse");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
