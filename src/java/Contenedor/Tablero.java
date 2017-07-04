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
    private ArrayList tarjetasSuerte;
    private ArrayList tarjetasComunidad;    

    public Tablero(ArrayList casillas, ArrayList jugadores, ArrayList tarjetasSuerte, ArrayList tarjetasComunidad) {
        this.casillas = casillas;
        this.jugadores = jugadores;
        this.tarjetasSuerte = tarjetasSuerte;
        this.tarjetasComunidad = tarjetasComunidad;
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
     * @return the tarjetasSuerte
     */
    public ArrayList getTarjetasSuerte() {
        return tarjetasSuerte;
    }

    /**
     * @param tarjetasSuerte the tarjetasSuerte to set
     */
    public void setTarjetasSuerte(ArrayList tarjetasSuerte) {
        this.tarjetasSuerte = tarjetasSuerte;
    }

    /**
     * @return the tarjetasComunidad
     */
    public ArrayList getTarjetasComunidad() {
        return tarjetasComunidad;
    }

    /**
     * @param tarjetasComunidad the tarjetasComunidad to set
     */
    public void setTarjetasComunidad(ArrayList tarjetasComunidad) {
        this.tarjetasComunidad = tarjetasComunidad;
    }

}
