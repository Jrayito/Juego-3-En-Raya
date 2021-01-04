package Controlador;

import Modelo.Principal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author acuar
 */
public class InicioController implements Initializable {
    //Variables XML
    @FXML
    Rectangle rectRojo;
    @FXML
    Rectangle rectAmarillo;
    @FXML
    Rectangle rectVerde;
    @FXML
    Rectangle rectAzul;
    @FXML
    Rectangle tacha1;
    @FXML
    Rectangle tacha2;
    @FXML
    Circle circulo;
    @FXML
    Label lblCargando;
    @FXML
    private AnchorPane panel;

    ConfigPropiedades config;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        config = new ConfigPropiedades();
        
        new Preloader().start();
        
        config.AbrirFichero(1);
        //Trasladamos a nuevas coordenadas para ocultar
        rectRojo.setTranslateY(-572);
        rectAmarillo.setTranslateY(572);
        rectVerde.setTranslateX(-522);
        rectAzul.setTranslateX(522);

        //Animaciones
        Transicion(3000,3,"y",0,rectRojo);
        Transicion(2000,5,"y",0,rectAmarillo);
        Transicion(2000,7,"x",0,rectVerde);
        Transicion(2000,9,"x",0,rectAzul);
     
        Scale(500,11,"y",1,tacha1);
        Scale(500,11,"y",1,tacha2);
        Scale(500,12,"y",1,circulo);
        
        Scale(500,2,"x",1,lblCargando);
        FadeTransition(3,2,1,0,lblCargando);
    }       
    private void Transicion(double duracion, double delay, String eje, double valor, Node node){
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.millis(duracion));
        tt.setDelay(Duration.seconds(delay));
        if(eje == "x")
            tt.setToX(valor);
        else
            tt.setToY(valor);
        tt.setNode(node);
        tt.play();
    }
    // 1 =  ejeX
    // 0 =  ejeY
    private void Scale(double duracion, double delay, String eje, double valor, Node node){
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.millis(duracion));
        scale.setDelay(Duration.seconds(delay));
        if(eje == "x")
            scale.setToX(valor);
        else
            scale.setToY(valor);
        scale.setNode(node);
        scale.play();
    }
    private void FadeTransition(double duracion, double delay, double from, double to, Node node){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(duracion));
        fade.setDelay(Duration.seconds(delay));
        fade.setFromValue(from);
        fade.setToValue(to);
        fade.setNode(node);
        fade.setCycleCount(Timeline.INDEFINITE);
        fade.play();
    }
    
    class Preloader extends Thread{
        @Override
        public void run(){
            try{
                Thread.sleep(14000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run(){
                        try{
                            new Principal().start(new Stage());
                        }catch(Exception ex){};
                    panel.getScene().getWindow().hide();
                    }
                });
            }catch(Exception ex){}
        }
    }
}
