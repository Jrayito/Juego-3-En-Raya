package Modelo;

import java.awt.Rectangle;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Inicio extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creamos la ruta la la vista pricipal
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Inicio.fxml"));
        //Creamos la escena a mostrar pasando la vista
        Scene scene = new Scene(root);
        //Agregamos la escena al stage
        primaryStage.setScene(scene);
        //Quitamos la decoració de la ventana
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //Mostramos la escena
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
