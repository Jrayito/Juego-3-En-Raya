package Controlador;

import Modelo.Juego;
import Modelo.Principal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoController implements Initializable {

    @FXML
    private Label lblModo;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Rectangle rec1;
    @FXML
    private Rectangle rec2;
    @FXML
    private Rectangle rec3;
    @FXML
    private Rectangle rec4;
    @FXML
    private Label jugador1;
    @FXML
    private Label jugador2;
    @FXML
    private Label marcador2;
    @FXML
    private Label marcador1;
    @FXML
    private Pane panelGanador;
    @FXML
    private Label lblTag;
    @FXML
    private Button btnJugar;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl6;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    @FXML
    private Label lbl9;
    @FXML
    private Label lbl5;
    @FXML
    private AnchorPane panel;
    @FXML
    private Label lblGanador;
    @FXML
    private Label marcadorEmpate;
    @FXML
    private Pane panelOcultar;
    
    private Thread juego;
    private static int turno = 0;
    private int ganador = -1;
    private static String tipoJuego;
    //Llevan el marcador cuando reinician
    private static int contador1 = 0;
    private static int contador2 = 0;
    private static int contador3 = 0;
    private static int reiniciar = 0;
    
    private static int icoJugador1, icoJugador2;
    private ControladorOnline jugador;
    private ConfigPropiedades config;
    private ControladorGeneral cg;
    
    private boolean[] botones = new boolean[9];
    private int[] lineaGanador = new int[2];
    String imagenes[] = {"tacha","circulo", "pacman1", "pacman2", "manzana", "pera", "futbol", "americano", 
        "diamante1", "diamante2", "tornillo", "tuerca", "boliche1", "boliche2", "hamburguesa", "papas", "dado1", 
        "dado2", "corazon", "pica"};    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        config = new ConfigPropiedades();
        cg = new ControladorGeneral();
        
        if(tipoJuego.equals("local") || tipoJuego.equals("maquina")){
            cargarPantalla(false);
            Tablero(false, "");
        }else
            cargarPantalla(true);
        
        
        switch(tipoJuego){
            case "local":
                IniciarPantalla("Local", "Jugador 1", "Jugador 2");
                break;
            case "maquina":
                IniciarPantalla("Maquina - "+config.getNivel(), config.getUsername(), "Maquina");
                break;
            case "online":
                lblModo.setText("Online");
                this.btnJugar.setDisable(true);
                inicarBotones();
                jugador = new ControladorOnline(this);
                juego = new Thread(jugador);
                juego.start();
                break;
        }
        
        icoJugador1 = config.getJugador1();
        icoJugador2 = config.getJugador2();
        
        if(reiniciar == 0){
            contador1 = 0; contador2 = 0; contador3 = 0;}
        
        marcador1.setText(String.valueOf(contador1));
        marcador2.setText(String.valueOf(contador2));
        marcadorEmpate.setText(String.valueOf(contador3));
        
        cg.empezarPartida();
    }
    @FXML
    private void casillaBtn1(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[0] = false;
        
        btn1.setVisible(false);
        SeleccionJuego(lbl1, 0, 0);     
    }
    @FXML
    private void casillaBtn2(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[1] = false;
        
        btn2.setVisible(false);
        SeleccionJuego(lbl2, 0, 1);
    }
    @FXML
    private void casillaBtn3(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[2] = false;
        
        btn3.setVisible(false);
        SeleccionJuego(lbl3, 0, 2);
    }
    @FXML
    private void casillaBtn4(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[3] = false;
        btn4.setVisible(false);
        SeleccionJuego(lbl4, 1, 0);
    }
    @FXML
    private void casillaBtn5(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[4] = false;
        btn5.setVisible(false);
        SeleccionJuego(lbl5, 1, 1);
    }
    @FXML
    private void casillaBtn6(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[5] = false;
        btn6.setVisible(false);
        SeleccionJuego(lbl6, 1, 2);
    }
    @FXML
    private void casillaBtn7(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[6] = false;
        btn7.setVisible(false);
        SeleccionJuego(lbl7, 2, 0);
    }
    @FXML
    private void casillaBtn8(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[7] = false;
        btn8.setVisible(false);
        SeleccionJuego(lbl8, 2, 1);
    }
    @FXML
    private void casillaBtn9(ActionEvent event) {
        if(tipoJuego.equals("online"))
                botones[8] = false;
        btn9.setVisible(false);
        SeleccionJuego(lbl9, 2, 2);
    }
    @FXML
    private void btnReiniciar(ActionEvent event) throws Exception {
        reiniciar = 1;
        panel.getScene().getWindow().hide();
        new Juego().start(new Stage());
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        if(tipoJuego.equals("online")){
            jugador.TermiarConexion();
            juego.stop();
        }
        
        try {
            new Principal().start(new Stage());
        } catch (Exception ex) {}
        reiniciar = 0;
        panel.getScene().getWindow().hide();
    }
    public void tipoJuego(String juego){
        this.tipoJuego = juego;
    }
    
    private void IniciarPantalla(String modo, String j1, String j2){
        lblModo.setText(modo);
        jugador1.setText(j1);
        jugador2.setText(j2);
    }
           
    private void iconosImagenes(String img, Label lblImagen){
        URL url = getClass().getResource("/Image/Imagen128/"+img+".png");
        Image imagen = new Image(url.toString());
        lblImagen.setGraphic(new ImageView(imagen));
        lblImagen.setVisible(true);
    }
    
    private void SeleccionJuego(Label lblImagen, int x, int y){
        if(tipoJuego == "local"){
            if(turno == 0){
                cg.pulsaBoton(x, y, turno);
                iconosImagenes(imagenes[icoJugador1], lblImagen);
                turno = 1;
            }else{
                cg.pulsaBoton(x, y, turno);
                iconosImagenes(imagenes[icoJugador2], lblImagen);
                turno = 0;
            }
        }else if(tipoJuego == "maquina"){
            cg.pulsaBoton(x, y, 0);
            iconosImagenes(imagenes[icoJugador1], lblImagen);
            
            if(config.getNivel().equals("Dificil"))
                cg.ponerFichaOrdenador();   
            else
                cg.NivelFacil();
            
            ponerMaquina();
        }else{            
            if(jugador.getTurno()){
                cg.pulsaBoton(x, y, 0);
                iconosImagenes(imagenes[icoJugador1], lblImagen);
                jugador.Enviar(x, y);
                OcultarBotones();
            }
        }
        ChecarGanador();
    }
    
    public void ChecarGanador(){
        ganador = cg.ganarPartida();
        if(ganador == -1){
            if(cg.tableroCompleto()){
                lblGanador.setText("Empate");
                lblTag.setText(" ");
                contador3++;
                Empate();
            }
        }else if(ganador == 0 || ganador == 1){
            etiquetasFinal();
            MarcarGanador();
            mostrarGanador(ganador);
        }
    }
    private void Empate(){
        int[][] tablero = cg.getTablero();
        
        if(tablero[0][0] != tablero[2][2]){
            Rotate(2500, 0, lbl1);
            Rotate(2000, 500, lbl9);
            Transicion(1500, 2500, 72, 150, lbl1);
            Transicion(1500, 2500, -72, -150, lbl9);
            ocultarLabels(lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8);
        }else if(tablero[0][1] != tablero[2][1]){
            Rotate(2500, 0, lbl2);
            Rotate(2000, 500, lbl8);
            Transicion(1500, 2500, -72, 150, lbl2);
            Transicion(1500, 2500, 72, -150, lbl8);
            ocultarLabels(lbl1, lbl3, lbl4, lbl5, lbl6, lbl7, lbl9);
        }else if(tablero[0][2] != tablero[2][0]){
            Rotate(2500, 0, lbl3);
            Rotate(2000, 500, lbl7);
            Transicion(1500, 2500, -72, 150, lbl3);
            Transicion(1500, 2500, 72, -150, lbl7);
            ocultarLabels(lbl1, lbl2, lbl4, lbl5, lbl6, lbl8, lbl9);
        }else if(tablero[1][0] != tablero[1][2]){
            Rotate(2500, 0, lbl4);
            Rotate(2000, 500, lbl6);
            Transicion(1500, 2500, 72, 0, lbl4);
            Transicion(1500, 2500, -72, 0, lbl6);
            ocultarLabels(lbl1, lbl2, lbl3, lbl5, lbl7, lbl8, lbl9);
        }
            
        panelGanador.setStyle("-fx-background-color: linear-gradient(#4968A6, #DC3C34);");
        Scale(300, 4, "y", 1, panelGanador);
        Scale(300, 4, "x", 1, panelGanador);
        
    }
    
    private void etiquetasFinal(){
        switch(tipoJuego){
            case "local":
                lblGanador.setText("Ganador");
                if(this.ganador == 0){
                    lblTag.setText("Jugador 1");
                    contador1++;
                }else{
                    lblTag.setText("Jugador 2");
                    contador2++;
                }
                break;
            case "maquina":
                lblGanador.setText("Ganador");
                if(this.ganador == 0){
                    lblTag.setText(config.getUsername());
                    contador1++;
                }else{
                    lblTag.setText("Maquina");
                    contador2++;
                }
                break;
            case "online":
                    if(ganador == 0)
                        lblTag.setText(config.getUsername());
                    else
                        lblTag.setText(jugador.getNombreContrincante());
                break;
        }
    }
    
    private void mostrarGanador(int ganador){
            
        OcultarBotones(); 
        
        switch(ganador){
            case 0:
                panelGanador.setStyle("-fx-background-color:#DC3C34;");
                break;
            case 1:
                panelGanador.setStyle("-fx-background-color:#4968A6;");
                break;
            }
        Scale(300, 4, "y", 1, panelGanador);
        Scale(300, 4, "x", 1, panelGanador);
    }
    
    public void ponerMaquina(){
        int[] boton = new int[2];
        boton = cg.getUltimoBoton();
        
        if(boton[0] == 0 && boton[1] == 0){
            btn1.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl1);
        }else if(boton[0] == 0 && boton[1] == 1){
            btn2.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl2);
        }else if(boton[0] == 0 && boton[1] == 2){
            btn3.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl3);
        }else if(boton[0] == 1 && boton[1] == 0){
            btn4.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl4);
        }else if(boton[0] == 1 && boton[1] == 1){
            btn5.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl5);
        }else if(boton[0] == 1 && boton[1] == 2){
            btn6.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl6);
        }else if(boton[0] == 2 && boton[1] == 0){
            btn7.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl7);
        }else if(boton[0] == 2 && boton[1] == 1){
            btn8.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl8);
        }else if(boton[0] == 2 && boton[1] == 2){
            btn9.setVisible(false);
            iconosImagenes(imagenes[icoJugador2], lbl9);
        }
    }
    
    private void MarcarGanador(){
        lineaGanador = cg.getLineaGanar();
        
        //Marcar diagonales
        if(lineaGanador[0] == 0 && lineaGanador[1] == 7){
            MarcarLinea(lbl1, lbl5, lbl9);
            TrasladarLinea(lbl1, lbl5, lbl9, 145, 150, 0, 0, -145, -150);
            ocultarLabels(lbl2, lbl3, lbl4, lbl6, lbl7, lbl8, lbl8);
        }else if(lineaGanador[0] == 0 && lineaGanador[1] == 8){
            MarcarLinea(lbl3, lbl5, lbl7);
            TrasladarLinea(lbl3, lbl5, lbl7,-145, 150, 0, 0, 145, -150);
            ocultarLabels(lbl1, lbl2, lbl4, lbl6, lbl8, lbl9, lbl9);
        
        //Marcar horizontal
        }else if(lineaGanador[0] == 1 && lineaGanador[1] == 0){
            MarcarLinea(lbl1, lbl2, lbl3);
            TrasladarLinea(lbl1, lbl2, lbl3, 145, 150, 0, 150, -145, 150);
            ocultarLabels(lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl9);
            
        }else if(lineaGanador[0] == 1 && lineaGanador[1] == 1){
            MarcarLinea(lbl4, lbl5, lbl6);
            TrasladarLinea(lbl4, lbl5, lbl6, 145, 0, 0, 0, -145, 0);
            ocultarLabels(lbl1, lbl2, lbl3, lbl7, lbl8, lbl9, lbl9);
            
        }else if(lineaGanador[0] == 1 && lineaGanador[1] == 2){
            MarcarLinea(lbl7, lbl8, lbl9);
            TrasladarLinea(lbl7, lbl8, lbl9, 145, -150, 0, -150, -145, -150);
            ocultarLabels(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl6);
            
        
        //Marcar vertical
        }else if(lineaGanador[0] == 2 && lineaGanador[1] == 0){
            MarcarLinea(lbl1, lbl4, lbl7);
            TrasladarLinea(lbl1, lbl4, lbl7, 145, 150, 145, 0, 145, -150);
            ocultarLabels(lbl2, lbl3, lbl5, lbl6, lbl8, lbl9, lbl9);
        }else if(lineaGanador[0] == 2 && lineaGanador[1] == 1){
            MarcarLinea(lbl2, lbl5, lbl8);
            TrasladarLinea(lbl2, lbl5, lbl8, 0, 150, 0, 0, 0, -150);
            ocultarLabels(lbl1, lbl3, lbl4, lbl6, lbl7, lbl9, lbl9);
        }else if(lineaGanador[0] == 2 && lineaGanador[1] == 2){
            MarcarLinea(lbl3, lbl6, lbl9);
            TrasladarLinea(lbl3, lbl6, lbl9, -145, 150, -145, 0, -145, -150);
            ocultarLabels(lbl1, lbl2, lbl4, lbl5, lbl7, lbl8, lbl8);
        }
    }
    
    private void MarcarLinea(Node cas1, Node cas2, Node cas3){
        Rotate(2500, 0, cas1);
        Rotate(2000, 500, cas2);
        Rotate(1500, 1000, cas3);
    }
    
    private void TrasladarLinea(Node cas1, Node cas2, Node cas3, 
            int cor1, int cor2, int cor3, int cor4, int cor5, int cor6){
        
        Transicion(1500, 2500, cor1, cor2, cas1);
        Transicion(1500, 2500, cor3, cor4, cas2);
        Transicion(1500, 2500, cor5, cor6, cas3);
    }
    
    public void cargarPantalla(boolean estado){     
        panelOcultar.setVisible(estado);
    }
    
    public void Tablero(boolean mostrar, String contrincante){
        Scale(2000, 1, "y", 1, rec1);
        Scale(2000, 1, "y", 1, rec2);
        Scale(2000, 1, "x", 1, rec3);
        Scale(2000, 1, "x", 1, rec4);
        
        if(mostrar){
            jugador2.setText(contrincante);
            jugador1.setText(config.getUsername());
        }
    }
    
    private void inicarBotones(){
        for(int x = 0; x <= 8; x++){
            botones[x] = true;
        }
    }
    
    public void OcultarBotones(){
        btn1.setVisible(false); btn2.setVisible(false); btn3.setVisible(false); btn4.setVisible(false);
        btn5.setVisible(false); btn6.setVisible(false); btn7.setVisible(false); btn8.setVisible(false);
        btn9.setVisible(false);
    }
    
    public void cambiarBoton(){
        int[] boton = cg.getUltimoBoton();
        int x = boton[0];
        int y = boton[1];
        
        if(x == 0 && y == 0)
            botones[0] = false;
        else if(x == 0 && y == 1)
            botones[1] = false;
        else if(x == 0 && y == 2)
            botones[2] = false;
        else if(x == 1 && y == 0)
            botones[3] = false;
        else if(x == 1 && y == 1)
            botones[4] = false;
        else if(x == 1 && y == 2)
            botones[5] = false;
        else if(x == 2 && y == 0)
            botones[6] = false;
        else if(x == 2 && y == 1)
            botones[7] = false;
        else 
            botones[8] = false;
                   
    }
    
    public void MostrarBotones(){
        if(ganador == -1){
            for(int x = 0; x <= 8; x++){
                if(botones[x] == true){
                    switch(x){
                        case 0:
                            btn1.setVisible(true);
                            break;
                        case 1:
                            btn2.setVisible(true);
                            break;
                        case 2:
                            btn3.setVisible(true);
                            break;
                        case 3:
                            btn4.setVisible(true);
                            break;
                        case 4:
                            btn5.setVisible(true);
                            break;
                        case 5:
                            btn6.setVisible(true);
                            break;
                        case 6:
                            btn7.setVisible(true);
                            break;
                        case 7:
                            btn8.setVisible(true);
                            break;
                        case 8:
                            btn9.setVisible(true);
                            break;
                    }
                }
            }
        }
    }
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
    private void Transicion(double duracion, double delay, double valorX, double valorY, Node node){
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.millis(duracion));
        tt.setDelay(Duration.millis(delay));
        tt.setToX(valorX);
        tt.setToY(valorY);
        tt.setNode(node);
        tt.play();
    }
    private void Rotate(double duration, double delay, Node node){
        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.millis(duration));
        rt.setDelay(Duration.millis(delay));
        rt.setToAngle(0);
        rt.setFromAngle(360);
        rt.setNode(node);
        rt.play();
    }
    
    private void ocultarLabels(Node lb1, Node lb2, Node lb3, Node lb4, Node lb5, Node lb6, Node lb7){
        Timer timer = new Timer();
        
        TimerTask ocultar = new TimerTask() {
            @Override
            public void run() {
                lb1.setVisible(false); lb2.setVisible(false); lb3.setVisible(false);
                lb4.setVisible(false); lb5.setVisible(false); lb6.setVisible(false);
                lb7.setVisible(false);
            }
        };
        timer.schedule(ocultar, 3100);
        
    }
}
