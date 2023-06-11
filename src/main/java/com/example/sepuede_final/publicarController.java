package com.example.sepuede_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class publicarController {
    @FXML
    private TextArea descripcion;
    @FXML
    private Pagination on;
    @FXML
    private Pane ancho;
    @FXML
    private Button bottonImaen;

    private int currentPageIndex;

    private static final int IMAGES_PER_PAGE = 5;



    File filesJpg[];

    @FXML
    void AccionSeleccionar1(ActionEvent event) {
        Stage stage = (Stage) ancho.getScene().getWindow();
        openDirectoryChooser(stage); // Llamamos al método para abrir la carpeta

        on.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                currentPageIndex = pageIndex; // Actualizamos el índice de la página actual
                return createPage(pageIndex);
            }
        });

        // Mostrar la primera página después de cargar las imágenes
        on.setCurrentPageIndex(0);
    }

    private void openDirectoryChooser(Stage parent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(parent);
        if (selectedDirectory != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpeg");
                }
            };
            filesJpg = selectedDirectory.listFiles(filterJpg);
            System.out.println("Número total de imágenes cargadas: " + filesJpg.length);
        }
    }

    public VBox createPage(int index) {
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(300); // Ajusta el ancho deseado
        imageView.setFitHeight(230); // Ajusta el alto deseado

        int imageIndex = index * 5 + currentPageIndex; // Calcula el índice de la imagen actual

        if (imageIndex < filesJpg.length) {
            File file = filesJpg[imageIndex];
            try {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            // No hay más imágenes para mostrar en esta página
            imageView.setImage(null);
        }

        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        return pageBox;
    }

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
