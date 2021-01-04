package Modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Principal.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Vista/main.css");
        primaryStage.setScene(scene);
        //Quitamos la decoración de la ventana
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //Mostramos la escena
        primaryStage.show();
    }
    
}
