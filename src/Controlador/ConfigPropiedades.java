package Controlador;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigPropiedades {

    private static String username;
    private static int jugador1;
    private static int jugador2;
    private static int ultimoAcceso;
    private static String nivel;
    private Properties propiedades;  
    
    ConfigPropiedades() {}

    public int getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(int ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public void ConfigPropiedades(String username, int jugador1, int jugador2, String nivel, int ultimoAcceso){
        this.username = username;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.nivel = nivel;
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getJugador1() {
        return jugador1;
    }

    public void setJugador1(int jugador1) {
        this.jugador1 = jugador1;
    }

    public int getJugador2() {
        return jugador2;
    }

    public void setJugador2(int jugador2) {
        this.jugador2 = jugador2;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public void AbrirFichero(int bandera){
        propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream("./config.properties"));
            
        } catch (IOException ex) {
            Logger.getLogger(ConfigPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(bandera == 1){
            ConfigPropiedades(
                propiedades.getProperty("username"),
                Integer.parseInt(propiedades.getProperty("jugador1")),
                Integer.parseInt(propiedades.getProperty("jugador2")), 
                propiedades.getProperty("nivel"),
                Integer.parseInt(propiedades.getProperty("acceso")));
        }
        
    }
    
    public void GuardarFichero(){
        this.AbrirFichero(0);
        propiedades.setProperty("username", this.getUsername()); 
        propiedades.setProperty("jugador1", String.valueOf(this.getJugador1()));
        propiedades.setProperty("jugador2", String.valueOf(this.getJugador2()));
        propiedades.setProperty("nivel", this.getNivel());
        propiedades.setProperty("acceso", String.valueOf(this.getUltimoAcceso()));
        
        try {
            propiedades.store(new FileWriter("./config.properties"), "Configuracion del juego");
        } catch (IOException ex) {
            Logger.getLogger(ConfigPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



