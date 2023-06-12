package com.example.sepuede_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class formulariocController {

    @FXML
    private DatePicker calendario;

    @FXML
    private TextField mail;

    @FXML
    private TextField tarjeta;

    @FXML
    void AccionCancelar(ActionEvent event) {
        mail.setText("");
        tarjeta.setText("");
        calendario.setValue(null);
    }


    @FXML

    void AccionComprar(ActionEvent event) {
        LocalDate holi = calendario.getValue();
        if (mail.getText().equals("") || tarjeta.getText().equals("") || holi == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los campos estan vacios");
            alert.showAndWait();

        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¡La compra se ha ejecutado correctamente!");
            alert.showAndWait();
    }}

    @FXML
    void AccionVolver(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagenController controller = fxmlLoader.getController();
            controller.setRoluser("comprador");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Ver obras");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
