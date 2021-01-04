package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class ControladorOnline  implements Runnable{
    int puerto = 5000;
    private static Socket socket;
    private DataInputStream dataIs;
    private DataOutputStream dataOs;
    private static boolean turno = true;
    private static int con;
    private static String nombreContrincante;
    private JuegoController juego;
    private ControladorGeneral cg;
    private ConfigPropiedades cp;
    
    public ControladorOnline(JuegoController juego){
        cg = new ControladorGeneral();
        cp = new ConfigPropiedades();
        
        try {
            //Establecemos la conexión
            socket = new Socket("192.168.1.71", puerto);
            dataIs = new DataInputStream(socket.getInputStream());
            dataOs = new DataOutputStream(socket.getOutputStream());
            this.juego = juego;
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Enviar mensaje en formado <mensaje / usuario>
    public void Enviar(int fila, int colum){
        try {
            dataOs.writeUTF(fila+""+colum+"/jugador "+con);
        } catch (IOException ex) {
            Logger.getLogger(ControladorOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTurno(!this.turno);
    }
    
    public void TermiarConexion(){
        try {
            dataOs.writeUTF("*");
        } catch (IOException ex) {
            Logger.getLogger(ControladorOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getTurno(){
        return turno;
    }
    public void setTurno(boolean turno){
        this.turno = turno;
    }
    
    public String getNombreContrincante(){
        return nombreContrincante;
    }
    
    @Override
    public void run() {
        try {
            String entrada = dataIs.readUTF();
            con = Integer.parseInt(String.valueOf(entrada.charAt(0)));
            
            if(con%2 == 0){
                turno = true; 
            }else{ 
                turno = false;
                Platform.runLater(()-> juego.OcultarBotones());
            }
            //Hacemos el envio de nuestro nombre
            dataOs.writeUTF(cp.getUsername()+"/jugador "+con);
            
            while(true){
                int x = 0, y = 0;
                String coor = dataIs.readUTF();          
                
                if(coor.length() > 2){
                    if(turno)
                        dataOs.writeUTF(cp.getUsername()+"/jugador "+con);
                        
                    nombreContrincante = coor;
                    Platform.runLater(()-> juego.cargarPantalla(false));
                    Platform.runLater(()-> juego.Tablero(true, this.getNombreContrincante()));
                }else{
                    x = Integer.parseInt(String.valueOf(coor.charAt(0)));
                    y = Integer.parseInt(String.valueOf(coor.charAt(1)));
                    cg.pulsaBoton(x, y, 1);
                    cg.setUltimoBoton(x, y);
                    Platform.runLater(()-> juego.ponerMaquina());
                    Platform.runLater(()-> juego.ChecarGanador());
                    Platform.runLater(()-> juego.cambiarBoton());
                    Platform.runLater(()-> juego.MostrarBotones());
                    setTurno(!this.turno);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
