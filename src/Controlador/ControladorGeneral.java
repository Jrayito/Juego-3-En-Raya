
package Controlador;

public class ControladorGeneral {

    private static int[][] Tablero = new int[3][3];
    private static int[] lineaGanar = new int[2];
    private static int Ganador = -1;
    private static int[] ub = new int[2];
    
    public void empezarPartida(){
        for(int x = 0; x <= 2; x++){
            for(int i = 0; i <= 2; i++){
                Tablero[x][i] = -1;
            }
        }
        Ganador = -1;
    }
    
    public int[][] getTablero(){
        return Tablero;
    }        
    
    public void pulsaBoton(int x, int i, int jugador){
        if( Tablero[x][i] == -1){
            if(Ganador == -1){
                Tablero[x][i] = jugador;
                //Ganador = ganarPartida();
            }
        }
    }
    
    public int ganarPartida(){
        // Verficar Diagonales
        if(Tablero[0][0] != -1 && Tablero[0][0] == Tablero[1][1] && Tablero[0][0] == Tablero[2][2]){
            lineaGanar[0] = 0; lineaGanar[1] = 7;
            return Tablero[0][0];
        }
        
        if(Tablero[0][2] != -1 && Tablero[0][2] == Tablero[1][1] && Tablero[0][2] == Tablero[2][0]){
            lineaGanar[0] = 0; lineaGanar[1] = 8;
            return Tablero[0][2];
        }
        
        
        for(int x = 0; x <= 2; x++){
            //vertical
            if(Tablero[x][0] != -1 && Tablero[x][0] == Tablero[x][1] && Tablero[x][0] == Tablero[x][2]){
                lineaGanar[0] = 1; lineaGanar[1] = x;
                return Tablero[x][0];
            }
            //horizontal
            if(Tablero[0][x] != -1 && Tablero[0][x] == Tablero[1][x] && Tablero[0][x] == Tablero[2][x]){
                lineaGanar[0] = 2; lineaGanar[1] = x;
                return Tablero[0][x];
            }
        }
        return -1;
    }
    
    public int[] getLineaGanar(){
        return lineaGanar;
    }
    
    public int getGanador(){
        return Ganador;
    }
    
    public int[] getUltimoBoton(){
        return ub;
    }
    
    public void setUltimoBoton(int x, int y){
        this.ub[0] = x;
        this.ub[1] = y;
    }
    //Algoritmo minimax
    public boolean tableroCompleto(){
        for(int x = 0; x <= 2; x++){
            for(int i = 0; i <= 2; i++){
                if(Tablero[x][i] == -1)
                    return false;
            }
        }
        return true;
    }
    
    public boolean finPartida(){
        return tableroCompleto() | ganarPartida() != -1;
    }
    
    //Nivel dificil
    public void ponerFichaOrdenador(){
        if(!finPartida()){
            int fila = 0;
            int columna = 0;
            int valorMin = Integer.MIN_VALUE;
            int aux;
            for(int x = 0; x <= 2; x++){
                for(int i = 0; i <= 2; i++){
                    if( Tablero[x][i] == -1){
                        Tablero[x][i] = 1;
                        aux = Min();
                        if(aux > valorMin){
                            valorMin = aux;
                            fila = x;
                            columna = i;
                        }
                        Tablero[x][i] = -1;
                    }
                }
            }
            Tablero[fila][columna] = 1;
            ub[0] = fila;
            ub[1] = columna;
        }  
    }
    
    private int Max(){
        if(finPartida()){
            if(ganarPartida() != -1)
                return -1;
            else
                return 0;
        }
        int valorMin = Integer.MIN_VALUE;
        int aux;
        for(int x = 0; x <= 2; x++){
            for(int i = 0; i <= 2; i++){
                if(Tablero[x][i] == -1){
                    Tablero[x][i] = 1;
                    aux =  Min();
                    if(aux > valorMin)
                        valorMin = aux;
                    Tablero[x][i] = -1;
                }
            }
        }
        return valorMin;
    }
    
    private int Min(){
        if(finPartida()){
            if(ganarPartida() != -1)
                return 1;
            else
                return 0;
        }
        int valorMax = Integer.MAX_VALUE;
        int aux;
        for(int x = 0; x <= 2; x++){
            for(int i = 0; i <= 2; i++){
                if(Tablero[x][i] == -1){
                    Tablero[x][i] = 0;
                    aux =  Max();
                    if(aux < valorMax)
                        valorMax = aux;
                    Tablero[x][i] = -1;
                }
            }
        }
        return valorMax;
    }
    
    //Nivel facil
    public void NivelFacil(){
        boolean bandera = true;
        
        if(!this.finPartida()){
            while(bandera){
                int random = (int) (Math.random()*9);
                switch(random){
                    case 0:
                        if(Tablero[0][0] == -1){
                            Tablero[0][0] = 1;
                            ub[0] = 0; ub[1] = 0;
                            bandera = false;
                        }
                        break;
                    case 1:
                        if(Tablero[0][1] == -1){
                            Tablero[0][1] = 1;
                            ub[0] = 0; ub[1] = 1;
                            bandera = false;
                        }
                        break;
                    case 2:
                        if(Tablero[0][2] == -1){
                            Tablero[0][2] = 1;
                            ub[0] = 0; ub[1] = 2;
                            bandera = false;
                        }
                        break;
                    case 3:
                        if(Tablero[1][0] == -1){
                            Tablero[1][0] = 1;
                            ub[0] = 1; ub[1] = 0;
                            bandera = false;
                        }
                        break;
                    case 4:
                        if(Tablero[1][1] == -1){
                            Tablero[1][1] = 1;
                            ub[0] = 1; ub[1] = 1;
                            bandera = false;
                        }
                        break;
                    case 5:
                        if(Tablero[1][2] == -1){
                            Tablero[1][2] = 1;
                            ub[0] = 1; ub[1] = 2;
                            bandera = false;
                        }
                        break;
                    case 6:
                        if(Tablero[2][0] == -1){
                            Tablero[2][0] = 1;
                            ub[0] = 2; ub[1] = 0;
                            bandera = false;
                        }
                        break;
                    case 7:
                        if(Tablero[2][1] == -1){
                            Tablero[2][1] = 1;
                            ub[0] = 2; ub[1] = 1;
                            bandera = false;
                        }
                        break;
                    case 8:
                        if(Tablero[2][2] == -1){
                            Tablero[2][2] = 1;
                            ub[0] = 2; ub[1] = 2;
                            bandera = false;
                        }
                        break;
                }
            }
        }
    }
}
