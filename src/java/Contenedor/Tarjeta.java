/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedor;

import java.io.Serializable;

/**
 *
 * @author Najib e Ibai
 */
public class Tarjeta implements Serializable {
    private int id;
    private String nombre;
    private int tipo;
    private int cantidad;
    private int aPagar;

    public Tarjeta(int id,String nombre, int tipo,int cantidad,int aPagar) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.aPagar = aPagar;
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
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the aPagar
     */
    public int getaPagar() {
        return aPagar;
    }

    /**
     * @param aPagar the aPagar to set
     */
    public void setaPagar(int aPagar) {
        this.aPagar = aPagar;
    }
}
