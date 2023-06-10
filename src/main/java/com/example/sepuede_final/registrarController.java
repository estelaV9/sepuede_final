package com.example.sepuede_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class registrarController {
    @FXML
    private RadioButton comprador;
    @FXML
    private RadioButton usuarios;

    @FXML
    private RadioButton vendedor;
    @FXML
    private Label tipo_rol;

    @FXML
    private PasswordField txtContrasenya;
    @FXML
    private TextField txtUusario;

    @FXML
    void AccionCancelar2(ActionEvent event) {
        txtContrasenya.setText("");
        txtUusario.setText("");
    }

    @FXML
    void AccionCerrar2 (ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
    }

    @FXML
    void AccionLogin2(ActionEvent event) {
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
    void AccionSign(ActionEvent event) {
        if(txtUusario.getText().equals("") || txtContrasenya.getText().equals("") || tipo_rol.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los campos estan vacios");
            alert.showAndWait();
        }else {
            if(comprador.isSelected() && vendedor.isSelected()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("no puedes elegir dos opciones");
                alert.showAndWait();

            }else {
                if(comprador.isSelected() && usuarios.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("no puedes elegir dos opciones");
                    alert.showAndWait();
                }else {
                    if(comprador.isSelected() && vendedor.isSelected() && usuarios.isSelected()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("no puedes elegir dos o mas de dos opciones");
                        alert.showAndWait();
                    }}}}

            if (vendedor.isSelected()) {
                Node source = (Node) event.getSource();
                Stage escena = (Stage) source.getScene().getWindow();
                escena.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistavendedor.fxml"));
                try {
                    Parent root = fxmlLoader.load();
                    vendedorController controller = fxmlLoader.getController();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.sizeToScene();
                    stage.setTitle("Pantalla vendedor");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (comprador.isSelected()) {
                    Node source = (Node) event.getSource();
                    Stage escena = (Stage) source.getScene().getWindow();
                    escena.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistacomprador.fxml"));
                    try {
                        Parent root = fxmlLoader.load();
                        compradorController controller = fxmlLoader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setResizable(false);
                        stage.sizeToScene();
                        stage.setTitle("Pantalla comprador");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
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

                }
            }
        }


    @FXML
    void AccionSincuenta2(ActionEvent event) {
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
    void AccionVolver2(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("open.fxml"));
        try {
            Parent root = fxmlLoader.load();
            OpenController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Inicio");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
