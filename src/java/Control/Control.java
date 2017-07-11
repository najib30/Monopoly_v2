/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Contenedor.Casilla;
import Contenedor.Dado;
import Contenedor.Jugador;
import Contenedor.Tablero;
import Contenedor.Tarjeta;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Najib e Ibai
 */
public class Control implements Serializable {
    
    private Tablero tablero;
    private Dado dado1;
    private Dado dado2;
    private String rutaPartidas;
    private String rutaConf;
    private int turnoJugador;
    private int sePuedeComprar;
    private Casilla casillaAcomprar;
    private int sePuedeEdificar;
    private int numeroAEdificar;
    private Jugador jugadorQueCompra;
    private int turnoTarjetaSuerte;
    private int turnoTarjetaComunidad;
    private int acaboJuego;
    
    public Control(String rutaPartidas, String rutaConf, String n1, String n2, String n3, String n4
                                     ,String n5,String n6,String n7, String n8) {
        System.out.println("Control: rutaPartidas " + rutaPartidas);
        System.out.println("Control: rutaConf " + rutaConf);         
        File carpeta = new File(rutaPartidas);
        if (!carpeta.exists()) {
            System.out.println("Control: creamos carpeta " + rutaPartidas);
            carpeta.mkdir();
        }
        this.rutaPartidas = rutaPartidas;
        this.rutaConf = rutaConf;
        this.turnoJugador = 0;
        ArrayList casillas = crearCasillas();
        ArrayList jugadores = crearJugadores(n1, n2, n3, n4, n5, n6, n7, n8);
        ArrayList tarjetasS = crearTarjetas(0);
        ArrayList tarjetasC = crearTarjetas(1);
        this.tablero = new Tablero(casillas, jugadores, tarjetasS, tarjetasC);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.casillaAcomprar = null;
        this.sePuedeEdificar = 0;
        this.numeroAEdificar = 0;
        this.jugadorQueCompra = null;
        Random rnd = new Random();
        this.turnoTarjetaSuerte = (int)(rnd.nextDouble() * 14 + 1); //hay 15
        this.turnoTarjetaComunidad = (int)(rnd.nextDouble() * 15 + 1); //hay 16
        this.acaboJuego = 0;
    }
    
    private ArrayList crearCasillas() {
        ArrayList casillas = new ArrayList();
        try {    
            File inputFile = new File(this.getRutaConf() + "\\casillas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("casilla");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);               
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  int id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                  String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                  //System.out.println("Control crearCasilla: nombre " + nombre);
                  int puedeSerComprado = Integer.parseInt(eElement.getElementsByTagName("se_puede_precio_compra").item(0).getTextContent());
                  int precioCompra = Integer.parseInt(eElement.getElementsByTagName("precio_compra").item(0).getTextContent());
                  String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                  int grupo = Integer.parseInt(eElement.getElementsByTagName("grupo").item(0).getTextContent());
                  int idGrupo = Integer.parseInt(eElement.getElementsByTagName("id_grupo").item(0).getTextContent());
                  int tipo = Integer.parseInt(eElement.getElementsByTagName("tipo").item(0).getTextContent());
                  int pago = Integer.parseInt(eElement.getElementsByTagName("pago").item(0).getTextContent());
                  int pago1Casa = Integer.parseInt(eElement.getElementsByTagName("pago_1_casa").item(0).getTextContent());
                  int pago2Casa = Integer.parseInt(eElement.getElementsByTagName("pago_2_casas").item(0).getTextContent());
                  int pago3Casa = Integer.parseInt(eElement.getElementsByTagName("pago_3_casas").item(0).getTextContent());
                  int pago4Casa = Integer.parseInt(eElement.getElementsByTagName("pago_4_casas").item(0).getTextContent());
                  int pagoHotel = Integer.parseInt(eElement.getElementsByTagName("pago_hotel").item(0).getTextContent());        
                  int precioCasa = Integer.parseInt(eElement.getElementsByTagName("precio_compra_casa").item(0).getTextContent());
                  int precioHotel = Integer.parseInt(eElement.getElementsByTagName("precio_compra_hotel").item(0).getTextContent());
                  Casilla casilla = new Casilla(id, nombre, puedeSerComprado, precioCompra, color, grupo, idGrupo, tipo, pago, pago1Casa, pago2Casa, pago3Casa, pago4Casa, pagoHotel, tipo, precioCasa, precioHotel);                  
                  casillas.add(casilla);
               }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return casillas;        
    }
    
    private ArrayList crearTarjetas(int tipoTarjeta) {
        ArrayList tarjetas = new ArrayList();
        try {    
            File inputFile = new File(this.getRutaConf() + "\\tarjetas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("tarjeta");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);               
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  int id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                  String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                  //System.out.println("Control crearCasilla: nombre " + nombre);
                  int tipo = Integer.parseInt(eElement.getElementsByTagName("tipo").item(0).getTextContent());
                  int cantidad = Integer.parseInt(eElement.getElementsByTagName("cantidad").item(0).getTextContent());
                  int aPagar = Integer.parseInt(eElement.getElementsByTagName("a_pagar").item(0).getTextContent());
                  Tarjeta tarjeta = new Tarjeta(id, nombre, tipo, cantidad, aPagar);
                  if (tipo == tipoTarjeta) {
                    tarjetas.add(tarjeta);
                  }
               }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tarjetas;                
    }    
    
    private ArrayList crearJugadores(String n1,String n2,String n3,String n4,
                                    String n5,String n6,String n7, String n8) {
        ArrayList jugadores = new ArrayList();
        if (n1 == null || n1.isEmpty()) {
            n1 = "Jugador1";
        }
        Jugador jugador1 = new Jugador (0, n1);
        if (n2 == null || n2.isEmpty()) {
            n2 = "Jugador2";
        }        
        Jugador jugador2 = new Jugador (1, n2);
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        if (n3 != null && !n3.isEmpty()) {
            Jugador jugador3 = new Jugador (2, n3);
            jugadores.add(jugador3);
        }
        if (n4 != null && !n4.isEmpty()) {
            Jugador jugador4 = new Jugador (3, n4);
            jugadores.add(jugador4);
        }
        if (n5 != null && !n5.isEmpty()) {
            Jugador jugador5 = new Jugador (4, n5);
            jugadores.add(jugador5);
        }
        if (n6 != null && !n6.isEmpty()) {
            Jugador jugador6 = new Jugador (5, n6);
            jugadores.add(jugador6);
        }
        if (n7 != null && !n7.isEmpty()) {
            Jugador jugador7 = new Jugador (6, n7);
            jugadores.add(jugador7);
        }
        if (n8 != null && !n8.isEmpty()) {
            Jugador jugador8 = new Jugador (7, n8);
            jugadores.add(jugador8);
        }
        
        return jugadores;        
    }        

    /**
     * @return the tablero
     */
    
    
    public String jugar() {
        String textConsola = "";
        this.sePuedeComprar = 0;
        this.casillaAcomprar = null;
        this.sePuedeEdificar = 0;
        this.numeroAEdificar = 0;
        this.jugadorQueCompra = null;
        
        int resultadoDado1 = dado1.lanzar();
        int resultadoDado2 = dado2.lanzar();
        Jugador jugadorActual = (Jugador)(tablero.getJugadores().get(turnoJugador));
        String nombreJugadorActual = jugadorActual.getNombre();
        int posicionAntesDeTirar = jugadorActual.getPosicion();
        int posicionDespuesDeTirar = posicionAntesDeTirar + resultadoDado1 + resultadoDado2;
        if (posicionDespuesDeTirar > 39) {
            posicionDespuesDeTirar = posicionDespuesDeTirar - 40;
            jugadorActual.setDinero(jugadorActual.getDinero() + 200); 
        }
        Casilla casillaAntesDeTirar = (Casilla)(tablero.getCasillas().get(posicionAntesDeTirar));
        String nombreCasillaAntesDeTirar = casillaAntesDeTirar.getNombre();
        Casilla casillaDespuesDeTirar = (Casilla)(tablero.getCasillas().get(posicionDespuesDeTirar));
        String nombreCasillaDespuesDeTirar = casillaDespuesDeTirar.getNombre();
        int tipoCasillaAntesDeTirar = casillaAntesDeTirar.getTipo();
        int tipoCasillaDespuesDeTirar = casillaDespuesDeTirar.getTipo();
        boolean esTiradaDoble = false;
        
        /* Para comprar o edificar */
        
        
        textConsola += "El jugador " + nombreJugadorActual + " ha sacado un " + resultadoDado1 + " y un " + resultadoDado2;
        
        if (resultadoDado1 == resultadoDado2) {
            esTiradaDoble = true; 
            jugadorActual.setTiradasDoble(jugadorActual.getTiradasDoble() + 1);
                if (jugadorActual.getTiradasDoble() >= 3) {
                    textConsola += ".3 Tirada doble, A la carcel!!!";
                    jugadorActual.setTiradasDoble(0);
                    jugadorActual.setPosicion(0);
                    pasarTurno();
                    return textConsola;
                }            
        }        
        if (tipoCasillaAntesDeTirar == 6 && jugadorActual.getTiradasEnCarcel() < 3 && !esTiradaDoble) {//jugador en la carcel
            if(jugadorActual.getTarjetaSalirCarcel() > 0) {
                jugadorActual.setPosicion(posicionDespuesDeTirar);
                jugadorActual.setTarjetaSalirCarcel(jugadorActual.getTarjetaSalirCarcel() - 1);
                textConsola += ".Usa una tarjeta de salir de la carcel. Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
            }
            else {
                if(jugadorActual.getTiradasEnCarcel() >= 3) {
                    jugadorActual.setTiradasEnCarcel(0);
                    jugadorActual.setPosicion(posicionDespuesDeTirar);
                    textConsola += ".Es su cuarto turno en la carcel. Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
                }
                else {
                    jugadorActual.setTiradasEnCarcel(jugadorActual.getTiradasEnCarcel() + 1);
                    textConsola += ".No ha sacado doble y lleva " + jugadorActual.getTiradasEnCarcel() + " tiradas en la carcel";
                    pasarTurno();
                    while ( !(((Jugador)(tablero.getJugadores().get(this.turnoJugador))).isActivo()) ) {
                        pasarTurno();
                    }                               
                }
            }            
        }
        else {
            if (tipoCasillaAntesDeTirar == 6) {
                if (esTiradaDoble) {//jugador sale de la carcel y tira el otra vez
                    jugadorActual.setTiradasEnCarcel(0);
                    textConsola += ".(es una tirada doble asÃ­ que sale de la carcel)";
                    jugadorActual.setTiradasDoble(jugadorActual.getTiradasDoble() + 1);
                }
                else {
                    if(jugadorActual.getTiradasEnCarcel() >= 3) {
                        jugadorActual.setTiradasEnCarcel(0);
                        textConsola += ".Es su cuarto turno en la carcel";
                    }
                }
            }            
            textConsola += ".Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
            if (tipoCasillaDespuesDeTirar == 0) {//jugador en la salida                
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }
            int propietario = getPropietarioDeCasilla(casillaDespuesDeTirar.getId());
            
            if (tipoCasillaDespuesDeTirar == 1) {//calle                
                if (propietario == -1) {//se puede comprar
                    if (jugadorActual.getDinero() > casillaDespuesDeTirar.getPrecioCompra()) {
                        textConsola += ".La casilla se puede comprar (cuesta " + casillaDespuesDeTirar.getPrecioCompra() + " euros y el jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros) o lanzar el dado si se quiere que tire el jugador siguiente";
                        this.sePuedeComprar = 1;
                        this.casillaAcomprar = casillaDespuesDeTirar;                                
                        this.jugadorQueCompra = jugadorActual;                        
                    }
                    else {
                        textConsola += ".La casilla no se puede comprar. El jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros y la casilla cuesta " + casillaDespuesDeTirar.getPrecioCompra();
                    }
                }
                else {
                    if (propietario == jugadorActual.getId()) {// es del jugador que tiene Turno: Se puede Edificar
                        int posesionesPropietario = getPosesionesDeGrupo(propietario, casillaDespuesDeTirar.getIdGrupo());
                        if (casillaDespuesDeTirar.getGrupo() == posesionesPropietario) {
                            if (casillaDespuesDeTirar.getNumeroDeHoteles() == 1) {
                                textConsola += ".La casilla pertenece a ese jugador y ya estÃ¡ totalmente edificada";
                            }
                            else {
                                textConsola += ".La casilla pertenece a ese jugador con lo que se puede edificar";
                                this.casillaAcomprar = casillaDespuesDeTirar;
                                this.sePuedeEdificar = 1;
                                this.numeroAEdificar = casillaDespuesDeTirar.getNumeroDeCasas();
                                this.jugadorQueCompra = jugadorActual;                        
                            }
                        }
                        else {
                            textConsola += ".El jugador posee " + posesionesPropietario + " de ese grupo y el total son " + casillaDespuesDeTirar.getGrupo();
                         }
                    }
                    else {//Pertenece a Otro, a Pagar
                        textConsola += ".La casilla pertenece al jugador " + (propietario + 1) + " con lo que hay que pagar";                        
                        textConsola += "." + pagarEnPropiedad(casillaDespuesDeTirar.getId(), casillaDespuesDeTirar.getTipo(), jugadorActual.getId(), propietario, casillaDespuesDeTirar.getIdGrupo(), casillaDespuesDeTirar.getGrupo());
                    }
                }
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }
            if (tipoCasillaDespuesDeTirar == 2) {//empresa
                if (propietario == -1) {//se puede comprar
                    if (jugadorActual.getDinero() > casillaDespuesDeTirar.getPrecioCompra()) {
                        textConsola += ".La casilla se puede comprar (cuesta " + casillaDespuesDeTirar.getPrecioCompra() + " euros y el jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros) o lanzar el dado si se quiere que tire el jugador siguiente";
                        this.sePuedeComprar = 1;
                        this.casillaAcomprar = casillaDespuesDeTirar;                                
                        this.jugadorQueCompra = jugadorActual;                        
                    }
                    else {
                        textConsola += ".La casilla no se puede comprar. El jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros y la casilla cuesta " + casillaDespuesDeTirar.getPrecioCompra();
                    }                    
                }
                else {
                    if (propietario == jugadorActual.getId()) {// es del jugador que tiene Turno
                        textConsola += ".La casilla pertenece a ese jugador, con lo que se pasa el turno al jugador siguiente";
                    }
                    else {//Pertenece a Otro, a Pagar
                        textConsola += ".La casilla pertenece al jugador " + (propietario + 1) + " con lo que hay que pagar";
                        textConsola += "." + pagarEnPropiedad(casillaDespuesDeTirar.getId(), casillaDespuesDeTirar.getTipo(), jugadorActual.getId(), propietario, casillaDespuesDeTirar.getIdGrupo(), casillaDespuesDeTirar.getGrupo());                        
                    }
                }
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }
            if (tipoCasillaDespuesDeTirar == 3) {//estacion
                if (propietario == -1) {//se puede comprar
                    if (jugadorActual.getDinero() > casillaDespuesDeTirar.getPrecioCompra()) {
                        textConsola += ".La casilla se puede comprar (cuesta " + casillaDespuesDeTirar.getPrecioCompra() + " euros y el jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros) o lanzar el dado si se quiere que tire el jugador siguiente";
                        this.sePuedeComprar = 1;
                        this.casillaAcomprar = casillaDespuesDeTirar;                                
                        this.jugadorQueCompra = jugadorActual;                        
                    }
                    else {
                        textConsola += ".La casilla no se puede comprar. El jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros y la casilla cuesta " + casillaDespuesDeTirar.getPrecioCompra();
                    }                    
                }
                else {
                    if (propietario == jugadorActual.getId()) {// es del jugador que tiene Turno
                        textConsola += ".La casilla pertenece a ese jugador, con lo que se pasa el turno al jugador siguiente";
                    }
                    else {//Pertenece a Otro, a Pagar
                        textConsola += ".La casilla pertenece al jugador " + (propietario + 1) + " con lo que hay que pagar";
                        textConsola += "." + pagarEnPropiedad(casillaDespuesDeTirar.getId(), casillaDespuesDeTirar.getTipo(), jugadorActual.getId(), propietario, casillaDespuesDeTirar.getIdGrupo(), casillaDespuesDeTirar.getGrupo());                        
                    }
                }
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }            
            if (tipoCasillaDespuesDeTirar == 4) {//caja comunidad o suerte
                jugadorActual.setPosicion(posicionDespuesDeTirar);
                if (posicionDespuesDeTirar == 7 || posicionDespuesDeTirar == 22 || posicionDespuesDeTirar == 36) { //suerte tipo 0
                    Tarjeta tarjeta =  (Tarjeta)(tablero.getTarjetasS().get(turnoTarjetaSuerte));
                    int idTarjetaSuerte = tarjeta.getId();
                    textConsola += ".Tarjeta suerte: " + tarjeta.getNombre();

                    if (idTarjetaSuerte == 0) { // casilla Salida
                        jugadorActual.setPosicion(0);
                    }
                    if (idTarjetaSuerte == 1) { // ir a ultima propiedad
                        jugadorActual.setPosicion(39);                        
                    }
                    if (idTarjetaSuerte == 2) { // ir a la primera propiedad
                        jugadorActual.setPosicion(1);                        
                    }
                    if (idTarjetaSuerte == 3) { // ir a servicio publico
                        jugadorActual.setPosicion(12); 
                    }
                    if (idTarjetaSuerte == 4) { // ir a estacion
                        jugadorActual.setPosicion(5);                         
                    }
                    if (idTarjetaSuerte == 5) { // cobra 50
                        jugadorActual.setDinero(jugadorActual.getDinero() + 50);
                    }
                    if (idTarjetaSuerte == 6) { // Salir de carcel
                        jugadorActual.setTarjetaSalirCarcel(jugadorActual.getTarjetaSalirCarcel() + 1); 
                    }
                    if (idTarjetaSuerte == 7) { // Retorceder 3 casillas
                        int posicionTemp = jugadorActual.getPosicion() - 3;
                        if (posicionTemp < 0) {
                           posicionTemp = posicionTemp + 40; 
                        }
                        jugadorActual.setPosicion(posicionTemp); 
                    }
                    if (idTarjetaSuerte == 8) { // a la carcel
                        jugadorActual.setPosicion(10); 
                    }
                    if (idTarjetaSuerte == 9) { // pagar 25 por casa y 100 por hotel
                        pagarPosCasas(jugadorActual, 25, 100);
                    }
                    if (idTarjetaSuerte == 10) { // pagar 15
                        jugadorActual.setDinero(jugadorActual.getDinero() - 15);                        
                    }
                    if (idTarjetaSuerte == 11) { // ir a primera estacion
                        jugadorActual.setPosicion(5);                        
                    }
                    if (idTarjetaSuerte == 12) { // cada jugador te da 50
                        cobarDeotros(jugadorActual, 50);
                    }
                    if (idTarjetaSuerte == 13) { // ganas 150
                        jugadorActual.setDinero(jugadorActual.getDinero() + 150);
                        
                    }
                    if (idTarjetaSuerte == 14) { // ganas 100
                        jugadorActual.setDinero(jugadorActual.getDinero() + 100);
                    }                    
                    this.turnoTarjetaSuerte = this.turnoTarjetaSuerte + 1;//hay 15
                    if (this.turnoTarjetaSuerte > 14) {
                        this.turnoTarjetaSuerte = 0;
                    }
        
                }
                if (posicionDespuesDeTirar == 2 || posicionDespuesDeTirar == 17 || posicionDespuesDeTirar == 33) { //comunidad tipo 1
                    Tarjeta tarjeta =  (Tarjeta)(tablero.getTarjetasC().get(turnoTarjetaComunidad));
                    int idTarjetaComunidad = tarjeta.getId();
                    textConsola += ".Tarjeta Comunidad: " + tarjeta.getNombre();
                    if (idTarjetaComunidad == 15) { // casilla Salida
                        jugadorActual.setPosicion(0); 
                    }
                    if (idTarjetaComunidad == 16) { //ganas 200
                        jugadorActual.setDinero(jugadorActual.getDinero() + 200);                        
                    }
                    if (idTarjetaComunidad == 17) { // pagas 50
                        jugadorActual.setDinero(jugadorActual.getDinero() - 50);
                    }
                    if (idTarjetaComunidad == 18) { // ganas 80
                        jugadorActual.setDinero(jugadorActual.getDinero() + 80);
                    }
                    if (idTarjetaComunidad == 19) { // libre de carcel
                        jugadorActual.setTarjetaSalirCarcel(jugadorActual.getTarjetaSalirCarcel() + 1);
                    }
                    if (idTarjetaComunidad == 20) { // a la carcel
                        jugadorActual.setPosicion(10);
                    }
                    if (idTarjetaComunidad == 21) { // cada jugador te da 50
                        cobarDeotros(jugadorActual, 50);
                    }
                    if (idTarjetaComunidad == 22) { // ganas 100
                        jugadorActual.setDinero(jugadorActual.getDinero() + 100); 
                    }
                    if (idTarjetaComunidad == 23) { // ganas 20
                        jugadorActual.setDinero(jugadorActual.getDinero() + 20); 
                    }
                    if (idTarjetaComunidad == 24) { // ganas 10 de cada jugador
                        cobarDeotros(jugadorActual, 10);
                    }
                    if (idTarjetaComunidad == 25) { // ganas 100
                        jugadorActual.setDinero(jugadorActual.getDinero() + 100); 
                    }
                    if (idTarjetaComunidad == 26) { // pagas 100
                        jugadorActual.setDinero(jugadorActual.getDinero() - 100);                         
                    }
                    if (idTarjetaComunidad == 27) { // pagas 150
                        jugadorActual.setDinero(jugadorActual.getDinero() - 150);                         
                    }
                    if (idTarjetaComunidad == 28) { // ganas 25
                        jugadorActual.setDinero(jugadorActual.getDinero() + 25); 
                    }
                    if (idTarjetaComunidad == 29) { // pagas 40 por casa y 115 por hotel
                        pagarPosCasas(jugadorActual, 40, 115);
                    }
                    if (idTarjetaComunidad == 30) { // ganas 10
                        jugadorActual.setDinero(jugadorActual.getDinero() + 10); 
                    }                   
                    this.turnoTarjetaComunidad = this.turnoTarjetaComunidad + 1; //hay 16
                    if (this.turnoTarjetaComunidad > 15) {
                        this.turnoTarjetaComunidad = 0;
                    }
                }                
            }
            if (tipoCasillaDespuesDeTirar == 5) {//ir a la carcel
                jugadorActual.setTiradasEnCarcel(0);
                jugadorActual.setPosicion(10);
            }            
            if (tipoCasillaDespuesDeTirar == 6) {//la carcel
                jugadorActual.setTiradasEnCarcel(0);
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }                        
            if (tipoCasillaDespuesDeTirar == 7) {//impuesto
                //Pagar
                textConsola += ".Hay que pagar. El jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros";
                jugadorActual.setDinero(jugadorActual.getDinero() - casillaDespuesDeTirar.getPrecioCompra());
                jugadorActual.setPosicion(posicionDespuesDeTirar);
                textConsola += ".DespuÃ©s de pagar el jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros";
            }
            if (tipoCasillaDespuesDeTirar == 8) {//parking
                //No pasa nada
                textConsola += ".En esta casilla no ocurre nada. El jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros";
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }
            if(!esTiradaDoble) {
                jugadorActual.setTiradasDoble(0);
                pasarTurno();
                while ( !(((Jugador)(tablero.getJugadores().get(this.turnoJugador))).isActivo()) ) {
                    pasarTurno();
                }                       
            }
            else {
                if (tipoCasillaDespuesDeTirar != 5 && tipoCasillaDespuesDeTirar != 6) {
                    textConsola += ".Tirada doble, el jugador vuelve a tirar";                
                }
                else {
                    pasarTurno();
                }                
            }
        }
        comprobarJugadorActivo(jugadorActual);
        if (comprobarNumeroJugadoresActivos() == 1) {
            Jugador ganador = comprobarGanador();            
            textConsola += ".El juago a terminado. El jugador " + ganador.getNombre() + " ha ganado la partida!!!!";
            this.acaboJuego = 1;
        }
        return textConsola;        
    }
    
    public void pasarTurno() {
        int turnoTemporal = this.turnoJugador + 1;
        if (turnoTemporal >= tablero.getJugadores().size()) {
            turnoTemporal = 0;
        }
        setTurnoJugador(turnoTemporal);
    }
    
    private void cobarDeotros(Jugador jugadorQueCobra, int cantidad) {
        ArrayList jugadores = tablero.getJugadores();
        int idJugadorQueCobra = jugadorQueCobra.getId();
        int aCobrar = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            if (idJugadorQueCobra != ((Jugador)(jugadores.get(i))).getId())
            if(((Jugador)(jugadores.get(i))).isActivo()) {
               aCobrar += cantidad;
               ((Jugador)(jugadores.get(i))).setDinero(((Jugador)(jugadores.get(i))).getDinero() - cantidad);
            }
        }
        jugadorQueCobra.setDinero(jugadorQueCobra.getDinero() + aCobrar); 
        
    }

    private void pagarPosCasas(Jugador jugador, int porCasa, int porHotel) {
        int aPagar = 0;
        ArrayList propiedades = jugador.getPropiedades();
        for (int i = 0; i < propiedades.size(); i++) {
            int idCasilla = (Integer)propiedades.get(i);
            Casilla casilla = (Casilla)(tablero.getCasillas().get(idCasilla));    
            aPagar += (casilla.getNumeroDeCasas() * porCasa) + (casilla.getNumeroDeHoteles() * porHotel);
        } 
        jugador.setDinero(jugador.getDinero() - aPagar); 
    }
    
    
    
    private void comprobarJugadorActivo(Jugador jugadorActual) {
        if (jugadorActual.getDinero() <= 0) {
            jugadorActual.setActivo(false);
        }
    }

    private int comprobarNumeroJugadoresActivos() {
        int numeroJugadoresActivos = 0;
        ArrayList jugadores = tablero.getJugadores();        
        for (int i = 0; i < jugadores.size(); i++) {
            if(((Jugador)(jugadores.get(i))).isActivo()) {
               numeroJugadoresActivos++; 
            }
        } 
        return numeroJugadoresActivos;
    }

    private Jugador comprobarGanador() {
        Jugador ganador = null;
        ArrayList jugadores = tablero.getJugadores();        
        for (int i = 0; i < jugadores.size(); i++) {
            if(((Jugador)(jugadores.get(i))).isActivo()) {
               ganador = ((Jugador)(jugadores.get(i)));
            }
        } 
        return ganador;    
    }
    
    private int getPropietarioDeCasilla(int idCasilla) {
        int propietario = -1;
        ArrayList jugadores = tablero.getJugadores();
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = (Jugador)(jugadores.get(i));
            ArrayList propiedades = jugador.getPropiedades();
            for (int j = 0; j < propiedades.size(); j++) {
                if (idCasilla == (Integer)propiedades.get(j)) {
                    propietario = jugador.getId();
                    return propietario;
                }
            }
        } 
        return propietario;
    }
          
    public String comprarPropiedad() {
        String textConsola = "";
        this.jugadorQueCompra.setDinero(this.jugadorQueCompra.getDinero() - this.casillaAcomprar.getPrecioCompra());
        textConsola += "Se compra la casilla " + this.casillaAcomprar.getNombre() + " por " + this.casillaAcomprar.getPrecioCompra() + " euros y el jugador " + this.jugadorQueCompra.getNombre() + " tiene " + this.jugadorQueCompra.getDinero() + " euros despuÃ©s de la compra";
        textConsola += ".El turno lo tiene el jugador " + (this.turnoJugador + 1);
        ArrayList prop = this.jugadorQueCompra.getPropiedades();
        prop.add(this.casillaAcomprar.getId());
        this.jugadorQueCompra.setPropiedades(prop);
        this.casillaAcomprar = null;
        this.jugadorQueCompra = null;
        this.sePuedeComprar = 0;
        return textConsola;
    }

    public String edificarPropiedad(int numeroCasas, int numeroHotel) {
        String textConsola = "";
        int aPagar = 0;
        if (numeroCasas > 0) {
            aPagar = this.casillaAcomprar.getPrecioCasa() * numeroCasas;
            textConsola += "Edificar " + numeroCasas + " casas cuesta" + aPagar + " euros y el jugador " + this.jugadorQueCompra.getNombre() + " tiene " + this.jugadorQueCompra.getDinero() + " euros";
            if (aPagar >=  this.jugadorQueCompra.getDinero()) {
                textConsola += ".No se puede edificar tantas casas";
            }
            else {
               this.jugadorQueCompra.setDinero(this.jugadorQueCompra.getDinero() - aPagar); 
               textConsola += ".Edificado y el jugador " + this.jugadorQueCompra.getNombre() + " tiene " + this.jugadorQueCompra.getDinero() + " euros";
               this.casillaAcomprar.setNumeroDeCasas(numeroCasas);
            }
        }
        if (numeroHotel > 0) {
            aPagar = this.casillaAcomprar.getPrecioHotel();
            textConsola += "Edificar 1 hotel cuesta" + aPagar + " euros y el jugador " + this.jugadorQueCompra.getNombre() + " tiene " + this.jugadorQueCompra.getDinero() + " euros";
            if (aPagar >=  this.jugadorQueCompra.getDinero()) {
                textConsola += ".No se puede edificar";
            }
            else {
               this.jugadorQueCompra.setDinero(this.jugadorQueCompra.getDinero() - aPagar); 
               textConsola += ".Edificado y el jugador " + this.jugadorQueCompra.getNombre() + " tiene " + this.jugadorQueCompra.getDinero() + " euros";
               this.casillaAcomprar.setNumeroDeHoteles(numeroHotel);
            }            
        }
        this.casillaAcomprar = null;
        this.sePuedeEdificar = 0;
        this.numeroAEdificar = 0;
        this.jugadorQueCompra = null;                                                            
        return textConsola;
    }

    private String pagarEnPropiedad(int idCasilla, int tipoCasilla, int idJugadorQuePaga, int idJugadorPropietario, int idGrupo, int totalGrupo) {
        int posesionesPropietario = getPosesionesDeGrupo(idJugadorPropietario, idGrupo);
        Jugador jugadorQuePaga = (Jugador)(tablero.getJugadores().get(idJugadorQuePaga));  
        Jugador jugadorPropietario = (Jugador)(tablero.getJugadores().get(idJugadorPropietario));  
        Casilla casilla = (Casilla)(tablero.getCasillas().get(idCasilla));                
        
        int aPagar = 0;
        if (tipoCasilla == 1) {//casilla: En funcion de que haya o no edificios
            if (totalGrupo == posesionesPropietario) {//todo pertenece al que cobra
                //comprobar que no hay nada edificado
                if (getHayEdificacionesEnGrupo(idGrupo) > 0) {//se cobra lo que haya en la casilla                    
                    if (casilla.getNumeroDeHoteles() == 1) {
                        aPagar += casilla.getNumeroDeHoteles();
                    }
                    else {
                        if (casilla.getNumeroDeCasas() == 1) {
                            aPagar = casilla.getPago1Casa();
                        }
                        if (casilla.getNumeroDeCasas() == 2) {
                            aPagar += casilla.getPago2Casa();
                        }
                        if (casilla.getNumeroDeCasas() == 3) {
                            aPagar += casilla.getPago3Casa();
                        }
                        if (casilla.getNumeroDeCasas() == 4) {
                            aPagar += casilla.getPago4Casa();
                        }
                    }                    
                }
                else {// se cobra el doble de lo que valga la
                    aPagar = casilla.getPago() * 2;                    
                }
            }
            else {
                //se paga lo que ponga la casilla y no puede estar edificada
                aPagar = casilla.getPago();
                
            }            
        }
        if (tipoCasilla == 2) {//empresa: En funcion del numero
            int resultadoDado1 = dado1.lanzar();
            int resultadoDado2 = dado2.lanzar(); 
            if (totalGrupo == posesionesPropietario) {//todo pertenece al que cobra
                aPagar = (resultadoDado1 + resultadoDado2) * 4;
            }
            else {
                aPagar = (resultadoDado1 + resultadoDado2) * 10;
            }
        }
        if (tipoCasilla == 3) {//estacion: En funcion del numero que se tenga
            if (posesionesPropietario == 1) {
                aPagar = 25;
            }
            if (posesionesPropietario == 2) {
                aPagar = 50;
            }
            if (posesionesPropietario == 3) {
                aPagar = 100;
            }
            if (posesionesPropietario == 4) {
                aPagar = 200;
            }
        }  
        jugadorQuePaga.setDinero(jugadorQuePaga.getDinero() - aPagar);
        jugadorPropietario.setDinero(jugadorPropietario.getDinero() + aPagar);        
        return "El jugador " + jugadorQuePaga.getNombre() +  " ha tenido que pagar " + aPagar + " euros al jugador " + jugadorPropietario.getNombre();
    }
    
    private int getPosesionesDeGrupo(int idJugador, int idGrupo) {
        //Jugador jugador = (Jugador)(tablero.getJugadores().get(idJugador));
        ArrayList casillas = tablero.getCasillas();
        //Casilla casilla = (Casilla)(casillas.get(idCasilla));                
        int totalJugador = 0;
        for (int i = 0; i < casillas.size(); i++) {
            if (idGrupo == ((Casilla)(casillas.get(i))).getIdGrupo()) {
                if (idJugador == getPropietarioDeCasilla(((Casilla)(casillas.get(i))).getId())) {
                    totalJugador++;
                }
            }
        }
        return totalJugador;
    }
    
    private int getHayEdificacionesEnGrupo(int idGrupo) {
        ArrayList casillas = tablero.getCasillas();
        for (int i = 0; i < casillas.size(); i++) {
            if (idGrupo == ((Casilla)(casillas.get(i))).getIdGrupo()) {                
                if ( ((Casilla)(casillas.get(i))).getNumeroDeCasas() > 0) {
                    return 1;
                }
            }
        }
        return 0;
    }
    
    
    private int getNunmeroDeGrupo(int idCasilla) {
        return 0;
    }

    
    
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * @param tablero the tablero to set
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * @return the dado1
     */
    public Dado getDado1() {
        return dado1;
    }

    /**
     * @param dado1 the dado1 to set
     */
    public void setDado1(Dado dado1) {
        this.dado1 = dado1;
    }

    /**
     * @return the dado2
     */
    public Dado getDado2() {
        return dado2;
    }

    /**
     * @param dado2 the dado2 to set
     */
    public void setDado2(Dado dado2) {
        this.dado2 = dado2;
    }

    /**
     * @return the rutaPartidas
     */
    public String getRutaPartidas() {
        return rutaPartidas;
    }

    /**
     * @param rutaPartidas the rutaPartidas to set
     */
    public void setRutaPartidas(String rutaPartidas) {
        this.rutaPartidas = rutaPartidas;
    }

    /**
     * @return the rutaConf
     */
    public String getRutaConf() {
        return rutaConf;
    }

    /**
     * @param rutaConf the rutaConf to set
     */
    public void setRutaConf(String rutaConf) {
        this.rutaConf = rutaConf;
    }

    /**
     * @return the turnoJugador
     */
    public int getTurnoJugador() {
        return turnoJugador;
    }

    /**
     * @param turnoJugador the turnoJugador to set
     */
    public void setTurnoJugador(int turnoJugador) {
        this.turnoJugador = turnoJugador;
    }

    /**
     * @return the sePuedeComprar
     */
    public int getSePuedeComprar() {
        return sePuedeComprar;
    }

    /**
     * @param sePuedeComprar the sePuedeComprar to set
     */
    public void setSePuedeComprar(int sePuedeComprar) {
        this.sePuedeComprar = sePuedeComprar;
    }

    /**
     * @return the sePuedeEdificar
     */
    public int getSePuedeEdificar() {
        return sePuedeEdificar;
    }

    /**
     * @param sePuedeEdificar the sePuedeEdificar to set
     */
    public void setSePuedeEdificar(int sePuedeEdificar) {
        this.sePuedeEdificar = sePuedeEdificar;
    }

    /**
     * @return the numeroAEdificar
     */
    public int getNumeroAEdificar() {
        return numeroAEdificar;
    }

    /**
     * @param numeroAEdificar the numeroAEdificar to set
     */
    public void setNumeroAEdificar(int numeroAEdificar) {
        this.numeroAEdificar = numeroAEdificar;
    }

    /**
     * @return the acaboJuego
     */
    public int getAcaboJuego() {
        return acaboJuego;
    }

    /**
     * @param acaboJuego the acaboJuego to set
     */
    public void setAcaboJuego(int acaboJuego) {
        this.acaboJuego = acaboJuego;
    }
}