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
public class Jugador implements Serializable {
    private int id;
    private String nombre;
    private double dinero;
    private int posicion;
    private ArrayList propiedades;
    private boolean carcel;
    private int tiradasEnCarcel;
    private int tiradasDoble;
    private int tarjetaSalirCarcel;
    private boolean activo;

    public Jugador(int id, String nombre) {
       this.id = id;
       this.nombre = nombre;
       this.posicion = 0;
       this.dinero = 1500;
       this.propiedades = new ArrayList();
       this.carcel = false;
       this.tiradasEnCarcel = 0;
       this.tiradasDoble = 0;
       this.tarjetaSalirCarcel = 0;
       this.activo = true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the dinero
     */
    public double getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the propiedades
     */
    public ArrayList getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(ArrayList propiedades) {
        this.propiedades = propiedades;
    }

    /**
     * @return the carcel
     */
    public boolean isCarcel() {
        return carcel;
    }

    /**
     * @param carcel the carcel to set
     */
    public void setCarcel(boolean carcel) {
        this.carcel = carcel;
    }

    /**
     * @return the tiradasEnCarcel
     */
    public int getTiradasEnCarcel() {
        return tiradasEnCarcel;
    }

    /**
     * @param tiradasEnCarcel the tiradasEnCarcel to set
     */
    public void setTiradasEnCarcel(int tiradasEnCarcel) {
        this.tiradasEnCarcel = tiradasEnCarcel;
    }

    /**
     * @return the tarjetaSalirCarcel
     */
    public int getTarjetaSalirCarcel() {
        return tarjetaSalirCarcel;
    }

    /**
     * @param tarjetaSalirCarcel the tarjetaSalirCarcel to set
     */
    public void setTarjetaSalirCarcel(int tarjetaSalirCarcel) {
        this.tarjetaSalirCarcel = tarjetaSalirCarcel;
    }

    /**
     * @return the tiradasDoble
     */
    public int getTiradasDoble() {
        return tiradasDoble;
    }

    /**
     * @param tiradasDoble the tiradasDoble to set
     */
    public void setTiradasDoble(int tiradasDoble) {
        this.tiradasDoble = tiradasDoble;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
