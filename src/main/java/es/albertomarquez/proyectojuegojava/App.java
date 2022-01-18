package es.albertomarquez.proyectojuegojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Panel principal que contendrá los elementos de la pantalla
        Pane paneRoot = new Pane();
        var scene = new Scene(paneRoot, 970, 480);        
        stage.setScene(scene);
        stage.show();
        // Cargar la imagen crear objeto ImageView
        Image img = new Image(getClass().getResourceAsStream("/images/mapa_def.jpg"));
        ImageView imgView = new ImageView(img);
        // Añadir el ImageView al panel principal de la pantalla
        paneRoot.getChildren().add(imgView);
    }

    public static void main(String[] args) {
        launch();
    }

}