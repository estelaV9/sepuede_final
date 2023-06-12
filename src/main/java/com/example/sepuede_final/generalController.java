package com.example.sepuede_final;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class generalController {

    @FXML
    private Button img1;
    @FXML
    private ListView<ImageView> imagenes;

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
    void AccionImg1(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagenController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void AccionImg2(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen2.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen2Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen2");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AccionImg3(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen3.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen3Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen3");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AccionImg4(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen4.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagen4Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Imagen4");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AccionVolver(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("3login.fxml"));
        try {
            Parent root = fxmlLoader.load();
            loginController controller = fxmlLoader.getController();
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

    public void initialize() {
        DBconexion conexion = new DBconexion();
        Statement st = conexion.crearconexion(false);
        String sql = "SELECT * FROM imagenes";
        ObservableList<ImageView> listaimagenes = FXCollections.observableArrayList();
        try {
            ResultSet rs = conexion.ejecutarsentencia(sql, st);
            while(rs.next()) {
                Blob url = rs.getBlob("url");
                // Leer los bytes del Blob
                byte[] bytes = url.getBytes(1, (int) url.length());
                // Crear un objeto Image desde los bytes
                InputStream inputStream = new ByteArrayInputStream(bytes);
                Image image = new Image(inputStream);
                // Mostrar la imagen en un ImageView
                ImageView imagen = new ImageView(image);
                listaimagenes.add(imagen);
            }
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imagenes.setItems(listaimagenes);
    }
}
