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
public class Casilla implements Serializable {
    private int id;
    private String nombre;
    private int puedeSerComprado;
    private int precioCompra;
    private String color;    
    private int grupo;
    private int idGrupo;
    private int tipo;
    private int pago;
    private int pago1Casa;
    private int pago2Casa;
    private int pago3Casa;
    private int pago4Casa;
    private int pagoHotel;
    private int precioCasa;
    private int precioHotel;
    private int numeroDeCasas;
    private int numeroDeHoteles;
    
     public Casilla(int id,String nombre,int puedeSerComprado,int precioCompra,String color,int grupo,int idGrupo,int tipo,int pago,int pago1Casa,int pago2Casa,int pago3Casa,int pago4Casa,int pagoHotel,int hipoteca,int precioCasa,int precioHotel) {
        this.id = id;
        this.nombre = nombre;
        this.puedeSerComprado = puedeSerComprado;
        this.precioCompra = precioCompra;
        this.color = color;
        this.grupo = grupo;
        this.tipo = tipo;
        this.idGrupo = idGrupo;
        this.pago = pago;
        this.pago1Casa = pago1Casa;
        this.pago2Casa = pago2Casa;
        this.pago3Casa = pago3Casa;
        this.pago4Casa = pago4Casa;
        this.pagoHotel = pagoHotel;
        this.precioCasa = precioCasa;
        this.precioHotel = precioHotel;
        this.numeroDeCasas = 0;
        this.numeroDeHoteles = 0;
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
     * @return the puedeSerComprado
     */
    public int getPuedeSerComprado() {
        return puedeSerComprado;
    }

    /**
     * @param puedeSerComprado the puedeSerComprado to set
     */
    public void setPuedeSerComprado(int puedeSerComprado) {
        this.puedeSerComprado = puedeSerComprado;
    }

    /**
     * @return the precioCompra
     */
    public int getPrecioCompra() {
        return precioCompra;
    }

    /**
     * @param precioCompra the precioCompra to set
     */
    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the grupo
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the idGrupo
     */
    public int getIdGrupo() {
        return idGrupo;
    }

    /**
     * @param idGrupo the idGrupo to set
     */
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
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
     * @return the pago
     */
    public int getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(int pago) {
        this.pago = pago;
    }

    /**
     * @return the pago1Casa
     */
    public int getPago1Casa() {
        return pago1Casa;
    }

    /**
     * @param pago1Casa the pago1Casa to set
     */
    public void setPago1Casa(int pago1Casa) {
        this.pago1Casa = pago1Casa;
    }

    /**
     * @return the pago2Casa
     */
    public int getPago2Casa() {
        return pago2Casa;
    }

    /**
     * @param pago2Casa the pago2Casa to set
     */
    public void setPago2Casa(int pago2Casa) {
        this.pago2Casa = pago2Casa;
    }

    /**
     * @return the pago3Casa
     */
    public int getPago3Casa() {
        return pago3Casa;
    }

    /**
     * @param pago3Casa the pago3Casa to set
     */
    public void setPago3Casa(int pago3Casa) {
        this.pago3Casa = pago3Casa;
    }

    /**
     * @return the pago4Casa
     */
    public int getPago4Casa() {
        return pago4Casa;
    }

    /**
     * @param pago4Casa the pago4Casa to set
     */
    public void setPago4Casa(int pago4Casa) {
        this.pago4Casa = pago4Casa;
    }

    /**
     * @return the pagoHotel
     */
    public int getPagoHotel() {
        return pagoHotel;
    }

    /**
     * @param pagoHotel the pagoHotel to set
     */
    public void setPagoHotel(int pagoHotel) {
        this.pagoHotel = pagoHotel;
    }

    /**
     * @return the precioCasa
     */
    public int getPrecioCasa() {
        return precioCasa;
    }

    /**
     * @param precioCasa the precioCasa to set
     */
    public void setPrecioCasa(int precioCasa) {
        this.precioCasa = precioCasa;
    }

    /**
     * @return the precioHotel
     */
    public int getPrecioHotel() {
        return precioHotel;
    }

    /**
     * @param precioHotel the precioHotel to set
     */
    public void setPrecioHotel(int precioHotel) {
        this.precioHotel = precioHotel;
    }

    /**
     * @return the numeroDeCasas
     */
    public int getNumeroDeCasas() {
        return numeroDeCasas;
    }

    /**
     * @param numeroDeCasas the numeroDeCasas to set
     */
    public void setNumeroDeCasas(int numeroDeCasas) {
        this.numeroDeCasas = numeroDeCasas;
    }

    /**
     * @return the numeroDeHoteles
     */
    public int getNumeroDeHoteles() {
        return numeroDeHoteles;
    }

    /**
     * @param numeroDeHoteles the numeroDeHoteles to set
     */
    public void setNumeroDeHoteles(int numeroDeHoteles) {
        this.numeroDeHoteles = numeroDeHoteles;
    }
    
}
