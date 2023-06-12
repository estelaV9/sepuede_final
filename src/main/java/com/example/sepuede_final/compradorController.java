package com.example.sepuede_final;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class compradorController {

    @FXML
    private Button img9;

    @FXML
    void AccionImg9(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen9.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen9Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen9");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AccionImg10(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen10.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen10Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen10");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AccionImg11(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen11.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen11Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen11");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AccionImg12(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen12.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen12Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen12");
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cerrar.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Cerrar");
            stage.setScene(new Scene(root));
            stage.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(3)); // 3 segundos de pausa
            delay.setOnFinished(e -> stage.close()); // Cierra la ventana después de 3 segundos
            delay.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void AccionVolver(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("2registro.fxml"));
        try {
            Parent root = fxmlLoader.load();
            registrarController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Registrar");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
