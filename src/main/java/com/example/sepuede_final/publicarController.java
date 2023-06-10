package com.example.sepuede_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class publicarController {
    @FXML
    private TextArea descripcion;

    @FXML
    void AccionCancelar(ActionEvent event) {
        descripcion.setText("");
    }

    @FXML
    void AccionPublicar(ActionEvent event) {
        if (descripcion.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los campos estan vacios");
            alert.showAndWait();

        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¡La publicación se ha ejecutado correctamente!");
            alert.showAndWait();
        }
    }

    @FXML
    void AccionVolver(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("6vistavendedor.fxml"));
        try {
            Parent root = fxmlLoader.load();
            vendedorController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Vista vendedor");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
