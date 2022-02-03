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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {  
/*----- VARIABLES A UTLIZAR -----*/
    Group groupPersonaje;
    Group groupVayas;
    double posXpersonaje = 100;
    double posYpersonaje = 364;
    double posXvayas = 969;
    double posYvayas = 364;
    double velVallas = 4;
    int score;
    int imagenFondo1X = 0;
    int imagenFondo2X = 969;
    int imgPajaro1X = 969;
    int imgPajaro2X = - 160;
    int gusanoX = 969;
    int velPersonaje = -4;
    final int TEXT_SIZE = 24;
    boolean reinicioPartida = false;
    boolean finPartida = false;
    ImageView imgfondo1;
    ImageView imgfondo2;
    ImageView imgPajaro1;
    ImageView imgPajaro2;
    ImageView imgGusano;
    Timeline vayas;
    Timeline fondoScroll;
    Timeline movSalto;
    Timeline colision;
    Timeline gusano;
    Timeline Pajaro1;
    Timeline Pajaro2;
    Label labelfinal;
    Pane paneRoot = new Pane();
    HBox paneScores;
    HBox paneCurrentScore;
    Text textTitleScore;
    Text textScore;
    Text textFinal;
    
/*---------------------------------------------------------------------------------*/      
    @Override
    public void start(Stage stage) {         
/*------ CREACION DE IMAGEVIEW() ------*/        
        imageViews();
/*---------------------------------------------------------------------------------*/                      
/*------ PANEL PRINCIPAL QUE CONTENDRA LOS ELEMENTOS DE LA PANTALLA ------*/     
        var scene = new Scene(paneRoot, 969, 480);        
        stage.setScene(scene);
        stage.show(); 
        panelPrincipal();
/*---------------------------------------------------------------------------------*/      
/*------ CREAR OBJETO ImageView ------*/
        imagenes();
/*---------------------------------------------------------------------------------*/         
/*------ FONDO SCROLL INFINITO ------*/
        scrollImagen();
/*---------------------------------------------------------------------------------*/         
/*------ PERSONAJES SECUNDARIOS SCROLL INFINITO ------*/ 
        persSecundarioScroll();
/*---------------------------------------------------------------------------------*/                       
/*------ TITULOS HBOX ------*/         
        elementosHBox();
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
        posicionValla();
/*---------------------------------------------------------------------------------*/           
/*------ MOVIMIENTO INFINITO DE LA VALLA ------*/        
         movVallas();
/*---------------------------------------------------------------------------------*/           
/*------ MOVIMIENTO DE SALTO DEL PERSONAJE ------*/           
        saltoPersonaje(scene);      
/*---------------------------------------------------------------------------------*/
/*------ DETECTAR COLISION PEROSNAJE/VAYA  ------*/          
        colision = new Timeline (           
                new KeyFrame(Duration.seconds(0.017),(ActionEvent ae) -> {            
                    Shape shapeColision = Shape.intersect(brazoDerecha,paloIzquierdo);        
                    boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();        
                    if(colisionVacia == false){            
                        finPartida1();               
                    }else if (finPartida == true){
                        finPartida2();                        
                    }               
                })        
        );        
        colision.setCycleCount(Timeline.INDEFINITE);
        colision.play();                      
    }   
    public static void main(String[] args) {
        launch();
    }
    public void scrollImagen(){
 
        fondoScroll = new Timeline(                             
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
    }
    public void imagenes(){
    Image img = new Image(getClass().getResourceAsStream("/images/mapa_def.jpg"));
        imgfondo1 = new ImageView(img);
        paneRoot.getChildren().add(imgfondo1);
        imgfondo2 = new ImageView(img);
        paneRoot.getChildren().add(imgfondo2);
        Image img2 = new Image(getClass().getResourceAsStream("/images/pajaro1.gif"));
        imgPajaro1 = new ImageView(img2);
        paneRoot.getChildren().add(imgPajaro1);
        imgPajaro1.setLayoutX(imgPajaro1X);
        imgPajaro1.setLayoutY(130);
        Image img3 = new Image(getClass().getResourceAsStream("/images/pp.gif"));
        imgPajaro2 = new ImageView(img3);
        paneRoot.getChildren().add(imgPajaro2);
        imgPajaro2.setLayoutX(imgPajaro2X);
        imgPajaro2.setLayoutY(40);
        Image img4 = new Image(getClass().getResourceAsStream("/images/gusano.gif"));
        imgGusano = new ImageView(img4);
        paneRoot.getChildren().add(imgGusano);
        imgGusano.setLayoutX(gusanoX);
        imgGusano.setLayoutY(425);}
    public void persSecundarioScroll(){
    Pajaro1 = new Timeline(                
                new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {                      
                    imgPajaro1X = imgPajaro1X - 3;
                    imgPajaro1.setLayoutX(imgPajaro1X);
                    if (imgPajaro1X <= -160) {  
                        imgPajaro1X = 969;
                      }
        
        })
        );
        Pajaro1.setCycleCount(Timeline.INDEFINITE);
        Pajaro1.play();
        
        Pajaro2 = new Timeline(                
                new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {                      
                    imgPajaro2X = imgPajaro2X + 2;
                    imgPajaro2.setLayoutX(imgPajaro2X);
                    if (imgPajaro2X >= 1000) {  
                        imgPajaro2X = -160;
                      }
        
        })
        );
        Pajaro2.setCycleCount(Timeline.INDEFINITE);
        Pajaro2.play(); 
        
        gusano = new Timeline(                
                new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {                      
                    gusanoX = gusanoX - 1;
                    imgGusano.setLayoutX(gusanoX);
                    if (gusanoX <= -160) {  
                        gusanoX = 969;
                      }  
        })
        );
        gusano.setCycleCount(Timeline.INDEFINITE);
        gusano.play();
    }
    public void imageViews(){
     imgfondo1 = new ImageView();
        imgfondo2 = new ImageView();
        imgPajaro1 = new ImageView();
        imgPajaro2 = new ImageView();
        imgGusano = new ImageView();}
    public void movVallas(){
    vayas = new Timeline(                                      
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {                    
                    posXvayas = posXvayas - velVallas;
                    groupVayas.setLayoutX(posXvayas);
                    //System.out.println(posXvayas);
                        if(posXvayas <= -63) {                            
                            posXvayas = 969;
                            score++;
                            textScore.setText(String.valueOf(score)); 
                           // System.out.println("PULSAS ARRIBA"); 
                        }                                         
                })            
        );            
        vayas.setCycleCount(Timeline.INDEFINITE);
        vayas.play();}
    public void panelPrincipal(){
        paneRoot.getChildren().add(imgfondo1);
        paneRoot.getChildren().add(imgfondo2);
        paneRoot.getChildren().add(imgPajaro1);
        paneRoot.getChildren().add(imgPajaro2);
        paneRoot.getChildren().add(imgGusano);
        imgfondo1.setLayoutX(imagenFondo1X);
        imgfondo2.setLayoutX(imagenFondo2X);     
    }
    public void saltoPersonaje(Scene scene){
        scene.setOnKeyPressed((KeyEvent event) -> {                
            if(event.getCode() == KeyCode.UP && posYpersonaje == 364) {
                velVallas = velVallas + 0.5;
                velPersonaje = -4;                 
            }
            if (reinicioPartida == true){
                if(event.getCode() == KeyCode.DOWN) {
                textFinal.setFill(Color.TRANSPARENT);
                velVallas = velVallas + 0.5;
                velPersonaje = -4;
                System.out.println("pUSLA LEFT");            
                vayas.play();
                fondoScroll.play();
                gusano.play();
                Pajaro1.play();
                Pajaro2.play();                                 
                posXpersonaje = 100;
                posYpersonaje = 364;
                posXvayas = 969;
                posYvayas = 364;
                velVallas = 4;
                imagenFondo1X = 0;
                imagenFondo2X = 969;
                imgPajaro1X = 969;
                imgPajaro2X = - 160;
                gusanoX = 969;
                reinicioPartida = false;
                finPartida = false;
            }
            }
        });
        
         movSalto = new Timeline(
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
        
    }
    public void posicionValla(){
        groupVayas.setLayoutX(posXvayas);
        groupVayas.setLayoutY(posYvayas);
    }
    public void elementosHBox(){
        /*------ LAYOUT PRINCIPAL ------*/
        paneScores = new HBox();
        paneScores. setTranslateY(20) ;
        paneScores. setAlignment (Pos.CENTER) ;
        paneScores. setSpacing(100) ;
        paneRoot.getChildren().add(paneScores) ;
/*------ LAYOUT PUNTUACION ACTUAL ------*/
        paneCurrentScore = new HBox();
        paneCurrentScore. setSpacing(10) ;
        paneScores.getChildren().add(paneCurrentScore) ;
/*------ TEXTO PARA CUANDO EL PERSONAJE SALTE LAS VALLAS ------*/
        textTitleScore = new Text("Vallas saltadas:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.BLACK);
/*------ TEXTO PARA EL NUMERO DE PUNTOS CONSEGUIDOS ------*/
        textScore = new Text("0");
        textScore. setFont (Font. font (TEXT_SIZE));
        textScore. setFill(Color.BLACK) ;
        paneCurrentScore.getChildren().add(textTitleScore) ;
        paneCurrentScore.getChildren().add(textScore) ;
/*---------------------------------------------------------------------------------*/ 
    }
    public void finPartida1(){
                        vayas.stop();
                        fondoScroll.stop();
                        gusano.stop();
                        Pajaro1.stop();
                        Pajaro2.stop();
                        posXvayas = 969;
                        gusanoX = 969;
                        imgPajaro1X = 969;
                        imgPajaro2X = -160;
                        groupVayas.setLayoutX(posXvayas);
                        imgGusano.setLayoutX(gusanoX);
                        imgPajaro1.setLayoutX(imgPajaro1X);
                        imgPajaro2.setLayoutX(imgPajaro2X);
                        System.out.println(posXvayas);
                        
                        finPartida = true;     
    }
    public void finPartida2(){
        // System.out.println(finPartida);
        textTitleScore.setFill(Color.TRANSPARENT);
        textScore. setFill(Color.TRANSPARENT) ;
        HBox panefinal = new HBox();
        panefinal.setTranslateY(200) ;
        panefinal.setTranslateX(300) ;
        panefinal.setAlignment (Pos.CENTER) ;
        panefinal.setSpacing(100) ;
        textFinal = new Text("Has saltado un total de: "+score+" vallas");
        textFinal. setFont (Font. font (TEXT_SIZE));
        textFinal. setFill(Color.BLACK) ;
        paneRoot.getChildren().add(panefinal) ;
        panefinal.getChildren().add(textFinal) ;   
        reinicioPartida = true;
    }
}