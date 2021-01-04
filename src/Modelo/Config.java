package Modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author acuar
 */
public class Config extends Application {

    //private Scene scene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Config.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Vista/main.css");
        primaryStage.setScene(scene);
        //Quitamos la decoració de la ventana
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //Mostramos la escena
        primaryStage.show();
    }
    
}
