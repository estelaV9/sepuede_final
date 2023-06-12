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
    void AccionLogin2(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena1 = (Stage) source.getScene().getWindow();
        escena1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("3Pagina2.fxml"));
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
                rs.updateString(2, txtUusario.getText());
                rs.updateString(3, txtContrasenya.getText());
                String rol;
                if (vendedor.isSelected()) {
                    rol = "vendedor";
                } else if (comprador.isSelected()) {
                    rol = "comprador";
                } else {
                    rol = "usuario";
                }
                rs.updateString(4, rol);
                rs.insertRow();
                correcto.showAndWait();
            } catch (SQLException e) {
                System.out.println("Error base de datos");
                error.showAndWait();
                e.printStackTrace();
            } catch (Exception e) {
                error.showAndWait();
                e.printStackTrace();
            }
            conexion.cerrarconexion(st);

            if (vendedor.isSelected()) {
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("5vistacomprador.fxml"));
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("4general.fxml"));
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
    }


    @FXML
    void AccionSincuenta2(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("4general.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("1open.fxml"));
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
