package es.albertomarquez.proyectojuegojava;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    
    double posX = 100;
    double posY = 364;
    Group groupPersonaje;
    int imagenFondo1X = 0;
    int imagenFondo2X = 969;
    ImageView imgfondo1;
    ImageView imgfondo2;
    
    
    @Override
    public void start(Stage stage) {
        imgfondo1 = new ImageView();
        imgfondo2 = new ImageView();
       
        // Panel principal que contendrá los elementos de la pantalla
        Pane paneRoot = new Pane();
        var scene = new Scene(paneRoot, 969, 480);        
        stage.setScene(scene);
        stage.show();
        paneRoot.getChildren().add(imgfondo1); 
        paneRoot.getChildren().add(imgfondo2);
        imgfondo1.setLayoutX(imagenFondo1X);
        imgfondo2.setLayoutX(imagenFondo2X);
        
        // Cargar la imagen crear objeto ImageView
        Image img = new Image(getClass().getResourceAsStream("/images/mapa_def.jpg"));
        imgfondo1 = new ImageView(img);
        paneRoot.getChildren().add(imgfondo1);
        imgfondo2 = new ImageView(img);
        paneRoot.getChildren().add(imgfondo2);
        
        //Desplazar la pantala ala izquierda
        Timeline fondoScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {
                      imagenFondo1X = imagenFondo1X -1;
                      imgfondo1.setLayoutX(imagenFondo1X);
                      imagenFondo2X = imagenFondo2X -1;
                      imgfondo2.setLayoutX(imagenFondo2X);
                      if (imagenFondo1X == -969) {
                          imagenFondo1X = 969;
                      } else if (imagenFondo2X == -969) {
                          imagenFondo2X = 969;
                      }
                  })
          );
          fondoScroll.setCycleCount(Timeline.INDEFINITE);
          fondoScroll.play(); // EJECUTAR EL TIMELINE
        
       
        
      /* --- DIBUJO DEL PERSONAJE --- */
        groupPersonaje = new Group();
        paneRoot.getChildren().add(groupPersonaje);
        
        // Cuerpo
        Rectangle cuerpo = new Rectangle(48, 60, Color.ORANGE);
        cuerpo.setX(20);
        cuerpo.setY(0);
        cuerpo.setArcWidth(30);
        cuerpo.setArcHeight(30);
        groupPersonaje.getChildren().add(cuerpo);
        // Piernas
        Rectangle piernaIzquierda = new Rectangle(10, 20, Color.ORANGE);
        piernaIzquierda.setX(21);
        piernaIzquierda.setY(50);
        piernaIzquierda.setArcWidth(10);
        piernaIzquierda.setArcHeight(10);
        groupPersonaje.getChildren().add(piernaIzquierda);
        
        Rectangle piernaDerecha = new Rectangle(10, 20, Color.ORANGE);
        piernaDerecha.setX(57);
        piernaDerecha.setY(50);
        piernaDerecha.setArcWidth(10);
        piernaDerecha.setArcHeight(10);
        groupPersonaje.getChildren().add(piernaDerecha);
        
        //BrazosI
        Rectangle brazoDerecha = new Rectangle(10, 30, Color.ORANGE);
        brazoDerecha.setX(15);
        brazoDerecha.setY(25);
        brazoDerecha.setRotate(40);
        brazoDerecha.setArcWidth(8);
        brazoDerecha.setArcHeight(8);
        groupPersonaje.getChildren().add(brazoDerecha);
        
        //BrazosD
        Rectangle brazoIzquierda = new Rectangle(10, 30, Color.ORANGE);
        brazoIzquierda.setX(62);
        brazoIzquierda.setY(25);
        brazoIzquierda.setArcWidth(8);
        brazoIzquierda.setArcHeight(8);
        brazoIzquierda.setRotate(-40);
        groupPersonaje.getChildren().add(brazoIzquierda);
        
        // OjoD
        Circle ojoD = new Circle(5, Color.BLACK);
        ojoD.setCenterX(57);
        ojoD.setCenterY(22);
        groupPersonaje.getChildren().add(ojoD);
        // OjoI
        Circle ojoI = new Circle(5, Color.BLACK);
        ojoI.setCenterX(29);
        ojoI.setCenterY(22);
        groupPersonaje.getChildren().add(ojoI);
        //BOCA
        Rectangle boca = new Rectangle(32,4, Color.BLACK);
        boca.setX(27);
        boca.setY(30);
        groupPersonaje.getChildren().add(boca);
         //Movemos el personaje hacia la direccion que queramos
        groupPersonaje.setLayoutX(posX);
        groupPersonaje.setLayoutY(posY);
        
    
        
    }
   

    public static void main(String[] args) {
        launch();
    }

}