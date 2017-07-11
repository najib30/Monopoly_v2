/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Najib e Ibai
 */
public class Tablero implements Serializable {
    private ArrayList casillas;
    private ArrayList jugadores;
    private ArrayList tarjetasS;
    private ArrayList tarjetasC;

    public Tablero(ArrayList casillas, ArrayList jugadores, ArrayList tarjetasS, ArrayList tarjetasC) {
        this.casillas = casillas;
        this.jugadores = jugadores;
        this.tarjetasS = tarjetasS;        
        this.tarjetasC = tarjetasC;        
    }    
    
    /**
     * @return the casillas
     */
    public ArrayList getCasillas() {
        return casillas;
    }

    /**
     * @param casillas the casillas to set
     */
    public void setCasillas(ArrayList casillas) {
        this.casillas = casillas;
    }

    /**
     * @return the jugadores
     */
    public ArrayList getJugadores() {
        return jugadores;
    }

    /**
     * @param jugadores the jugadores to set
     */
    public void setJugadores(ArrayList jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * @return the tarjetasS
     */
    public ArrayList getTarjetasS() {
        return tarjetasS;
    }

    /**
     * @param tarjetasS the tarjetasS to set
     */
    public void setTarjetasS(ArrayList tarjetasS) {
        this.tarjetasS = tarjetasS;
    }

    /**
     * @return the tarjetasC
     */
    public ArrayList getTarjetasC() {
        return tarjetasC;
    }

    /**
     * @param tarjetasC the tarjetasC to set
     */
    public void setTarjetasC(ArrayList tarjetasC) {
        this.tarjetasC = tarjetasC;
    }

}
