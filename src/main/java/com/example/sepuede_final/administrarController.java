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
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class administrarController {

    @FXML
    private TreeTableView<Usuario> usuarios;
    @FXML
    private TreeTableColumn<Usuario, Integer> id;
    @FXML
    private TreeTableColumn<Usuario, String> nombre;
    @FXML
    private TreeTableColumn<Usuario, String> contraseña;
    @FXML
    private TreeTableColumn<Usuario, String> rol;
    @FXML
    private TreeTableColumn<Usuario, String> fecha_registro;
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
    void AccionVolver2(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage escena = (Stage) source.getScene().getWindow();
        escena.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imagen.fxml"));
        try {
            Parent root = fxmlLoader.load();
            imagenController controller = fxmlLoader.getController();
            controller.setRoluser("admin");
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

    public void initialize() {
        ObservableList<Usuario> listausuarios = FXCollections.observableArrayList();
        DBconexion conexion = new DBconexion();
        Statement st = conexion.crearconexion(false);
        String sql = "SELECT * FROM usuario";
        try {
            ResultSet rs = conexion.ejecutarsentencia(sql, st);
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nombre_usuario"), rs.getString("contraseña"), rs.getString("rol"), rs.getDate("fecha_registro"));
                System.out.println(usuario.toString());
                listausuarios.add(usuario);
            }
            conexion.cerrarconexion(st);
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TreeItem<Usuario> rootItem = new TreeItem<>();
        for (Usuario usuario : listausuarios) {
            TreeItem<Usuario> item = new TreeItem<>(usuario);
            rootItem.getChildren().add(item);
        }

        id.setCellValueFactory(new TreeItemPropertyValueFactory<>("idUsuario"));
        nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nombreUsuario"));
        contraseña.setCellValueFactory(new TreeItemPropertyValueFactory<>("contraseña"));
        rol.setCellValueFactory(new TreeItemPropertyValueFactory<>("rol"));
        fecha_registro.setCellValueFactory(new TreeItemPropertyValueFactory<>("fechaRegistro"));

        usuarios.setRoot(rootItem);
        usuarios.setShowRoot(false);
    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        TreeItem<Usuario> selectedItem = usuarios.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Usuario usuario = selectedItem.getValue();

            // Eliminar el usuario de la base de datos
            DBconexion conexion = new DBconexion();
            Statement st = conexion.crearconexion(false);
            String sql = "DELETE FROM usuario WHERE id_usuario = " + usuario.getIdUsuario();
            try {
                conexion.ejecutarsentencia(sql, st);
                conexion.cerrarconexion(st);
            } catch (Exception e) {
                System.out.println("Error al eliminar usuario");
                e.printStackTrace();
                // Mostrar mensaje de error si es necesario
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al eliminar usuario");
                alert.setContentText("Ocurrió un error al eliminar el usuario de la base de datos");
                alert.showAndWait();
                return;
            }

            // Eliminar el elemento seleccionado de la vista
            selectedItem.getParent().getChildren().remove(selectedItem);

            // Actualizar la vista
            usuarios.refresh();
        }
    }
}
