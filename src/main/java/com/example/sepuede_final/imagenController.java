package com.example.sepuede_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class imagenController {

    @FXML
    private ImageView imagen;
    @FXML
    private Label totalImagenes;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField nimagen;
    @FXML
    private Button anterior;
    @FXML
    private Button siguiente;
    @FXML
    private Button ir;

    private int obraactual;
    private int nobras;

    @FXML
    void AccionVolver(ActionEvent event) {
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
            stage.setTitle("General");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        cambiarimagen(1);
        DBconexion conexion = new DBconexion();
        Statement st = conexion.crearconexion(false);
        String sql = "SELECT COUNT(*) FROM imagenes;";
        try {
            ResultSet rs = conexion.ejecutarsentencia(sql, st);
            while(rs.next()) {
                int obras = rs.getInt(1);
                this.nobras = obras;
                this.nimagen.setText(obras + "");
                conexion.cerrarconexion(st);
            }
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void anterior(ActionEvent event) {
        if (this.obraactual > 1) {
            this.obraactual--;
            cambiarimagen(this.obraactual);
        }
    }

    public void siguiente(ActionEvent event) {
        if (this.obraactual < this.nobras) {
            this.obraactual++;
            cambiarimagen(this.obraactual);
        }
    }

    public void irobra(ActionEvent event) {
        int nobra = Integer.parseInt(nimagen.getText());
        if (nobra < this.nobras) {
            this.obraactual++;
            cambiarimagen(this.obraactual);
        }
    }



    public void cambiarimagen(int obraactual) {
        nimagen.setText(obraactual + "");
        this.obraactual = obraactual;
        DBconexion conexion = new DBconexion();
        Statement st = conexion.crearconexion(false);
        String sql = "SELECT * FROM imagenes WHERE id_imagen = '" + obraactual + "'";
        try {
            ResultSet rs = conexion.ejecutarsentencia(sql, st);
            while(rs.next()) {
                Blob url = rs.getBlob("url");
                // Leer los bytes del Blob
                byte[] bytes = url.getBytes(1, (int) url.length());
                // Crear un objeto Image desde los bytes
                InputStream inputStream = new ByteArrayInputStream(bytes);
                Image image = new Image(inputStream);
                this.imagen.setImage(image);
                String descripcion = rs.getString("descripcion");
                this.txtDescripcion.setText(descripcion);
                conexion.cerrarconexion(st);
            }
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
