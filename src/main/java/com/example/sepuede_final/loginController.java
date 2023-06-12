package com.example.sepuede_final;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class loginController {
    @FXML
    private TextField txtUsuarioLogin;

    @FXML
    private PasswordField txtContraseniaLogin;

    @FXML
    void acceder(ActionEvent event) {
        if (txtUsuarioLogin.getText().equals("")) {
            Alert errorlogin = new Alert(Alert.AlertType.WARNING);
            errorlogin.setContentText("Los campos están vacíos.");
            errorlogin.showAndWait();
        } else {
            DBconexion conexion = new DBconexion();
            Statement st = conexion.crearconexion(false);
            String sql = "SELECT * FROM usuario";
            ResultSet rs = conexion.ejecutarsentencia(sql, st);
            boolean usuarioencontrado = false;
            Alert econexionlogin = new Alert(Alert.AlertType.ERROR);
            econexionlogin.setContentText("Conexión con la base de datos fallida.");
            try {
                String usuariologin = null;
                int registrousuario = 0;
                Alert errorlogin = new Alert(Alert.AlertType.WARNING);
                while (rs.next() && !usuarioencontrado) {
                    usuariologin = rs.getString("nombre_usuario");
                    if (usuariologin.equals(txtUsuarioLogin.getText())) {
                        usuarioencontrado = true;
                        registrousuario = rs.getRow();
                    }
                }
                if (!usuarioencontrado) {
                    errorlogin.setContentText("El usuario no existe.");
                    errorlogin.showAndWait();
                } else {
                    rs.absolute(registrousuario);
                    if (rs.getString("contraseña").equals(txtContraseniaLogin.getText())) {
                        String rol = rs.getString("rol");
                        if (rol.equals("comprador")) {
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
                        } else if (rol.equals("vendedor")) {
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
                        } else if (rol.equals("admin")) {
                            Node source = (Node) event.getSource();
                            Stage escena = (Stage) source.getScene().getWindow();
                            escena.close();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("4.1adminsitrador.fxml"));
                            try {
                                Parent root = fxmlLoader.load();
                                general2Controller controller = fxmlLoader.getController();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setResizable(false);
                                stage.sizeToScene();
                                stage.setTitle("Pantalla Administrador");
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (rol.equals("usuario")) {
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
                    } else {
                        errorlogin.setContentText("La contraseña para " + usuariologin + " es incorrecta.");
                        errorlogin.showAndWait();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error base de datos");
                econexionlogin.showAndWait();
                e.printStackTrace();
            } catch (Exception e) {
                econexionlogin.showAndWait();
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
    void cancelar(ActionEvent event) {
        txtContraseniaLogin.setText("");
        txtUsuarioLogin.setText("");
    }


    @FXML
    void cerrar(ActionEvent event) {
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
    void irregistro(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena2 = (Stage) source.getScene().getWindow();
        escena2.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("2registro.fxml"));
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
