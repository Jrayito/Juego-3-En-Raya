package Controlador;

import Modelo.Principal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfigController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private TextField txtTag;
    @FXML
    private ComboBox<String> boxNiveles;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblImagen;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnSelect;
    
    ConfigPropiedades config;
    private static int contador = 0;
    String imagenes[] = {"tacha","circulo", "pacman1", "pacman2", "manzana", "pera", "futbol", "americano", 
        "diamante1", "diamante2", "tornillo", "tuerca", "boliche1", "boliche2", "hamburguesa", "papas", "dado1", 
        "dado2", "corazon", "pica"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        config = new ConfigPropiedades();
        
        //Creamos la lista a mostrar
        ObservableList<String> nivel = FXCollections.observableArrayList();
        nivel.addAll("Facil", "Dificil");
        //Asignamos los valores a el ComboBox
        boxNiveles.setItems(nivel);
        
        boxNiveles.getSelectionModel().select(config.getNivel());

        txtTag.setText(config.getUsername());
        
        contador = config.getJugador1();
        if(contador == 0)
            btnAtras.setDisable(true);
        
        lblImagen.setScaleX(1);
        
        URL rutaImagen = getClass().getResource("/Image/Imagen128/"+imagenes[contador]+".png");
        lblImagen.setGraphic(new ImageView(new Image(rutaImagen.toString())));
    }
    
    @FXML
    private void btnSiguiente(ActionEvent event) {
        contador++;
        iconosImagenes(imagenes[contador]);
        if(contador == 1)
            btnAtras.setDisable(false);
        if(contador == 19)
            btnSiguiente.setDisable(true);
    }
    
    @FXML
    private void btnAtras(ActionEvent event) {
        contador--;
        iconosImagenes(imagenes[contador]);
        if(contador == 0)
            btnAtras.setDisable(true);
        if(contador == 18)
            btnSiguiente.setDisable(false);
    }

    @FXML
    private void Guardar(ActionEvent event) {
        String tag = txtTag.getText();
        String nivel = boxNiveles.getSelectionModel().getSelectedItem();
        
        if(tag != ""){
            config.setUsername(tag);   
            config.setNivel(nivel);
            CerrarVentana();
        }   
    }

    @FXML
    private void personajeSeleccion(ActionEvent event) {
        if(contador%2 == 0){
            config.setJugador1(contador);
            config.setJugador2(contador+1);
        }else{
            config.setJugador1(contador);
            config.setJugador2(contador-1);
        }
        CerrarVentana();
    }
     
    private void iconosImagenes(String img){
        URL url = getClass().getResource("/Image/Imagen128/"+img+".png");
        Image imagen = new Image(url.toString());
        lblImagen.setGraphic(new ImageView(imagen));
    }
    
    private void CerrarVentana(){
        try {
            new Principal().start(new Stage());
        } catch (Exception ex) {}
        panel.getScene().getWindow().hide();
    }
}
