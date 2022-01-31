package es.albertomarquez.proyectojuegojava;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {  
/*----- VARIABLES A UTLIZAR -----*/
    double posXpersonaje = 100;
    double posYpersonaje = 364;
    double posXvayas = 969;
    double posYvayas = 364;
    Group groupPersonaje;
    Group groupVayas;
    int imagenFondo1X = 0;
    int imagenFondo2X = 969;
    ImageView imgfondo1;
    ImageView imgfondo2;
    ImageView imgPajaro1;
    int imgPajaro1X = 969;
    int velPersonaje = -4;
    Text textScore;
    final int TEXT_SIZE = 24;
    int score;
    boolean finPartida = false;
    double velVallas = 4;
    
    
/*---------------------------------------------------------------------------------*/      
    @Override
    public void start(Stage stage) {         
/*------ CREACION DE IMAGEVIEW() ------*/        
        imgfondo1 = new ImageView();
        imgfondo2 = new ImageView();
        imgPajaro1 = new ImageView();
        
/*---------------------------------------------------------------------------------*/                      
/*------ PANEL PRINCIPAL QUE CONTENDRA LOS ELEMENTOS DE LA PANTALLA ------*/  
        Pane paneRoot = new Pane();
        var scene = new Scene(paneRoot, 969, 480);        
        stage.setScene(scene);
        stage.show();
        paneRoot.getChildren().add(imgfondo1);
        paneRoot.getChildren().add(imgfondo2);
        paneRoot.getChildren().add(imgPajaro1);
        imgfondo1.setLayoutX(imagenFondo1X);
        imgfondo2.setLayoutX(imagenFondo2X);
        imgPajaro1.setLayoutX(imgPajaro1X);
        imgPajaro1.setLayoutY(250);
        
/*---------------------------------------------------------------------------------*/      
/*------ CREAR OBJETO ImageView ------*/
        Image img = new Image(getClass().getResourceAsStream("/images/mapa_def.jpg"));
        imgfondo1 = new ImageView(img);
        paneRoot.getChildren().add(imgfondo1);
        imgfondo2 = new ImageView(img);
        paneRoot.getChildren().add(imgfondo2);
        Image img2 = new Image(getClass().getResourceAsStream("/images/pajaro1.gif"));
        imgPajaro1 = new ImageView(img2);
        paneRoot.getChildren().add(imgPajaro1);
/*---------------------------------------------------------------------------------*/         
/*------ FONDO SCROLL INFINITO ------*/
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
/*---------------------------------------------------------------------------------*/         
/*------ PERSONAJES SECUNDARIOS SCROLL INFINITO ------*/ 
        Timeline PAJARO1 = new Timeline(                
                new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {                      
                    imgPajaro1X = imgPajaro1X - 3;
                    imgPajaro1.setLayoutX(imgPajaro1X);
                    if (imgPajaro1X <= -60) {  
                        imgPajaro1X = 969;
                      }
        
        })
        );
        PAJARO1.setCycleCount(Timeline.INDEFINITE);
        PAJARO1.play();
/*---------------------------------------------------------------------------------*/                       
/*------ LAYOUT PRINCIPAL ------*/
        HBox paneScores = new HBox();
        paneScores. setTranslateY(20) ;
        paneScores. setAlignment (Pos.CENTER) ;
        paneScores. setSpacing(100) ;
        paneRoot.getChildren().add(paneScores) ;
/*------ LAYOUT PUNTUACION ACTUAL ------*/
        HBox paneCurrentScore = new HBox();
        paneCurrentScore. setSpacing(10) ;
        paneScores.getChildren().add(paneCurrentScore) ;
/*------ TEXTO PARA CUANDO EL PERSONAJE SALTE LAS VALLAS ------*/
        Text textTitleScore = new Text("Vallas saltadas:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.BLACK);
/*------ TEXTO PARA EL NUMERO DE PUNTOS CONSEGUIDOS ------*/
        Text textScore = new Text("0");
        textScore. setFont (Font. font (TEXT_SIZE));
        textScore. setFill(Color.BLACK) ;
        paneCurrentScore.getChildren().add(textTitleScore) ;
        paneCurrentScore.getChildren().add(textScore) ;
/*---------------------------------------------------------------------------------*/                              
/*----- GRUPO PERSONAJE ----- */
        groupPersonaje = new Group();
        paneRoot.getChildren().add(groupPersonaje);       
/*----- GRUPO VAYAS -----*/
        groupVayas = new Group();
        paneRoot.getChildren().add(groupVayas);
/*---------------------------------------------------------------------------------*/                       
/*----- PARTES DEL CUERPO DEL PERSONAJE PRINCIPAL ------*/                       
    /*-- CUERPO --*/
        Rectangle cuerpo = new Rectangle(48, 60, Color.ORANGE);
        cuerpo.setX(20);
        cuerpo.setY(0);
        cuerpo.setArcWidth(30);
        cuerpo.setArcHeight(30);
        groupPersonaje.getChildren().add(cuerpo);
    /*-- PIERNAS --*/
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
    /*-- BRAZOS --*/
        Rectangle brazoDerecha = new Rectangle(10, 30, Color.ORANGE);
        brazoDerecha.setX(15);
        brazoDerecha.setY(25);
        brazoDerecha.setRotate(40);
        brazoDerecha.setArcWidth(8);
        brazoDerecha.setArcHeight(8);
        groupPersonaje.getChildren().add(brazoDerecha);
       
        Rectangle brazoIzquierda = new Rectangle(10, 30, Color.ORANGE);
        brazoIzquierda.setX(62);
        brazoIzquierda.setY(25);
        brazoIzquierda.setArcWidth(8);
        brazoIzquierda.setArcHeight(8);
        brazoIzquierda.setRotate(-40);
        groupPersonaje.getChildren().add(brazoIzquierda);       
    /*-- OJOS --*/
        Circle ojoD = new Circle(5, Color.BLACK);
        ojoD.setCenterX(57);
        ojoD.setCenterY(22);
        groupPersonaje.getChildren().add(ojoD);
        
        Circle ojoI = new Circle(5, Color.BLACK);
        ojoI.setCenterX(29);
        ojoI.setCenterY(22);
        groupPersonaje.getChildren().add(ojoI);
    /*-- BOCA --*/
        Rectangle boca = new Rectangle(32,4, Color.BLACK);
        boca.setX(27);
        boca.setY(30);
        groupPersonaje.getChildren().add(boca);        
    /*-- POSICION DEL PERSONAJE COMPLETO --*/
        groupPersonaje.setLayoutX(posXpersonaje);
        groupPersonaje.setLayoutY(posYpersonaje);
/*---------------------------------------------------------------------------------*/   
/*------ PARTES DE LA VALLA ------*/    
        Rectangle vaya1 = new Rectangle(60, 10, Color.BLACK);
        vaya1.setX(0);
        vaya1.setY(25);
        groupVayas.getChildren().add(vaya1);
       
        Rectangle paloIzquierdo = new Rectangle(10, 45, Color.BLACK);
        paloIzquierdo.setX(0);
        paloIzquierdo.setY(25);
        groupVayas.getChildren().add(paloIzquierdo);
       
        Rectangle paloDerecho = new Rectangle(10, 45, Color.BLACK);
        paloDerecho.setX(50);
        paloDerecho.setY(25);
        groupVayas.getChildren().add(paloDerecho);
/*------ POSICION DE LA VALLA ------*/     
        groupVayas.setLayoutX(posXvayas);
        groupVayas.setLayoutY(posYvayas);
/*---------------------------------------------------------------------------------*/           
/*------ MOVIMIENTO INFINITO DE LA VALLA ------*/        
        Timeline vayas = new Timeline(                                      
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {                    
                    posXvayas = posXvayas - velVallas;
                    groupVayas.setLayoutX(posXvayas);
                    //System.out.println(posXvayas);
                        if(posXvayas <= -63) {                            
                            posXvayas = 969;                         
                        }                                            
                })            
        );            
        vayas.setCycleCount(Timeline.INDEFINITE);
        vayas.play();
/*---------------------------------------------------------------------------------*/           
/*------ MOVIMIENTO DE SALTO DEL PERSONAJE ------*/        
        scene.setOnKeyPressed((KeyEvent event) -> {                
            if(event.getCode() == KeyCode.UP && posYpersonaje == 364) {
                velVallas = velVallas + 0.5;
                velPersonaje = -4;
                score++;
                textScore.setText(String.valueOf(score)); 
                System.out.println("PULSAS ARRIBA");              
            }                
        });
       
        Timeline movSalto = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) ->{
                posYpersonaje += velPersonaje;
                groupPersonaje.setLayoutY(posYpersonaje);                
                if (posYpersonaje <= 270){                    
                    velPersonaje = 4;                
                }                
                if (posYpersonaje == 364){
                velPersonaje = 0;                
                }                
            })
        ); 
        movSalto.setCycleCount(Timeline.INDEFINITE);
        movSalto.play();
/*---------------------------------------------------------------------------------*/           
/*------ DETECTAR COLISION PEROSNAJE/VAYA  ------*/          
        Timeline colision = new Timeline (           
                new KeyFrame(Duration.seconds(0.017),(ActionEvent ae) -> {            
                    Shape shapeColision = Shape.intersect(brazoDerecha,paloIzquierdo);        
                    boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();        
                    if(colisionVacia == false){            
                        vayas.stop();
                        fondoScroll.stop();
                        posXvayas = 969;
                        groupVayas.setLayoutX(posXvayas);
                        System.out.println(posXvayas);
                        finPartida = true;
                    } else if (finPartida == true){
                        System.out.println(finPartida);
                        textTitleScore.setFill(Color.TRANSPARENT);
                        textScore. setFill(Color.TRANSPARENT) ;
                        Label labelfinal = new Label("Has saltado un total de "+score+" vallas");
                        Font font = Font.font("Bahnschrift", FontWeight.BLACK, FontPosture.REGULAR, 25);
                        labelfinal.setFont(font);
                        labelfinal.setTextFill(Color.BLACK);
                        labelfinal.setTranslateX(300);
                        labelfinal.setTranslateY(200);
                        paneRoot.getChildren().add(labelfinal);
                    }               
                })        
        );        
        colision.setCycleCount(Timeline.INDEFINITE);
        colision.play();           
    }
   

    public static void main(String[] args) {
        launch();
    }

}