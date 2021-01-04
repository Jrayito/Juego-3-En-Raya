package Controlador;

import Modelo.Config;
import Modelo.Juego;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private Label lblTag;
    @FXML
    private Button btnConfiguracion;
    @FXML
    private Button btnLocal;
    @FXML
    private Button btnMaquina;
    @FXML
    private Button btnOnline;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblFicha;

    private static String tipoJuego = "";
    ConfigPropiedades config;
    JuegoController juego;
    String imagenes[] = {"tacha","circulo", "pacman1", "pacman2", "manzana", "pera", "futbol", "americano", 
        "diamante1", "diamante2", "tornillo", "tuerca", "boliche1", "boliche2", "hamburguesa", "papas", "dado1", 
        "dado2", "corazon", "pica"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        config = new ConfigPropiedades();
        juego = new JuegoController();
        
        ponerFicha();
        
        if(config.getUltimoAcceso() == 0){
            InetAddress localHost =  null;
            try {
                localHost = InetAddress.getLocalHost();
            } catch(UnknownHostException ex) {}
            config.setUsername(String.valueOf(localHost.getHostName()));
            config.setUltimoAcceso(1);
        }
        lblTag.setText(config.getUsername());
    }    
    

    @FXML
    private void btnMaquina(ActionEvent event) throws IOException, Exception {
        juego.tipoJuego("maquina");
        try {
            
            new Juego().start(new Stage());
        } catch (Exception ex) {}
        panel.getScene().getWindow().hide();
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        config.GuardarFichero();
        Platform.exit();
    }

    @FXML
    private void btnLocal(ActionEvent event) {
        juego.tipoJuego("local");
        try {
            new Juego().start(new Stage());
        } catch (Exception ex) {}
        panel.getScene().getWindow().hide();
    }

    @FXML
    private void btnOnline(ActionEvent event) throws IOException {
        juego.tipoJuego("online");
        try {
            new Juego().start(new Stage());
        } catch (Exception ex) {}
        panel.getScene().getWindow().hide();
        
    }

    @FXML
    private void btnConfig(ActionEvent event) {
        try{
            new Config().start(new Stage());
        }catch (Exception ex) {}
        panel.getScene().getWindow().hide();
    }

    public String getTipoJuego(){
        return tipoJuego;
    }
    
    private void ponerFicha(){
        URL url = getClass().getResource("/Image/Imagen128/"+imagenes[config.getJugador1()]+".png");
        Image imagen = new Image(url.toString());
        lblFicha.setGraphic(new ImageView(imagen));
    }
}
