package com.example.sepuede_final;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;

public class registrarController {
    @FXML
    private RadioButton comprador;
    @FXML
    private RadioButton usuario;

    @FXML
    private RadioButton vendedor;
    @FXML
    private Label tipo_rol;

    @FXML
    private PasswordField txtContrasenia;
    @FXML
    private TextField txtUsuario;

    @FXML
    void cancelar () {
        txtUsuario.setText("");
        txtContrasenia.setText("");
    }

    @FXML
    void cerrar (ActionEvent event) {

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
    void iriniciarsesion(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena1 = (Stage) source.getScene().getWindow();
        escena1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("3login.fxml"));
        try {
            Parent root = fxmlLoader.load();
            loginController controller = fxmlLoader.getController();
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
    void registrar(ActionEvent event) {
        if(txtUsuario.getText().equals("") || txtContrasenia.getText().equals("") || tipo_rol.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los campos están vacíos.");
            alert.showAndWait();
        } else {
            DBconexion conexion = new DBconexion();
            Statement st = conexion.crearconexion(false);
            String sql = "SELECT * FROM usuario";
            ResultSet rs = conexion.ejecutarsentencia(sql, st);
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No se ha podido registrar el usuario.");
            Alert correcto = new Alert(Alert.AlertType.INFORMATION);
            correcto.setContentText("El usuario se ha registrado correctamente");
            try {
                rs.moveToInsertRow();
                rs.updateString(2, txtUsuario.getText());
                rs.updateString(3, txtContrasenia.getText());
                String rol = "usuario";
                if (vendedor.isSelected()) {
                    rol = "vendedor";
                } else if (comprador.isSelected()) {
                    rol = "comprador";
                } else if (usuario.isSelected()){
                    rol = "usuario";
                }
                rs.updateString(4, rol);
                rs.insertRow();
                correcto.showAndWait();
                txtUsuario.setText("");
                txtContrasenia.setText("");
                usuario.setSelected(true);
            } catch (SQLException e) {
                System.out.println("Error base de datos");
                error.showAndWait();
                e.printStackTrace();
            } catch (Exception e) {
                error.showAndWait();
                e.printStackTrace();
            }
            conexion.cerrarconexion(st);
        }
    }


    @FXML
    void accedersincuenta(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagenController controller = fxmlLoader.getController();
            controller.setRoluser("usuario");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Vista de Imagenes");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void volver(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("1open.fxml"));
        try {
            Parent root = fxmlLoader.load();
            openController controller = fxmlLoader.getController();
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

    public void initialize() {
        usuario.setSelected(true);
    }

}
