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
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    public Control(String rutaPartidas, String rutaConf, String n1, String n2, String n3, String n4
                                     ,String n5,String n6,String n7) {
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
        ArrayList jugadores = crearJugadores(n1, n2, n3, n4, n5, n6, n7);
        ArrayList tarjetas = new ArrayList();
        this.tablero = new Tablero(casillas, jugadores, tarjetas, tarjetas);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
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
                  int puedeSerComprado = Integer.parseInt(eElement.getElementsByTagName("se_puede_comprar").item(0).getTextContent());
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
    
    private void crearTarjetas() {
        
    }    
    
    private ArrayList crearJugadores(String n1,String n2,String n3,String n4,
                                    String n5,String n6,String n7) {
        ArrayList jugadores = new ArrayList();
        Jugador jugador1 = new Jugador (0, n1);
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
        return jugadores;        
    }        

    /**
     * @return the tablero
     */
     public String jugar() {
        int resultadoDado1 = dado1.lanzar();
        int resultadoDado2 = dado2.lanzar();
        Jugador jugadorActual = (Jugador)(tablero.getJugadores().get(turnoJugador));
        int posicionAntesDeTirar = jugadorActual.getPosicion();
        int posicionDespuesDeTirar = posicionAntesDeTirar + resultadoDado1 + resultadoDado1;
        if (posicionDespuesDeTirar > 39) {
            posicionDespuesDeTirar = posicionDespuesDeTirar - 40;
        }
        Casilla casillaAntesDeTirar = (Casilla)(tablero.getCasillas().get(posicionAntesDeTirar));
        Casilla casillaDespuesDeTirar = (Casilla)(tablero.getCasillas().get(posicionDespuesDeTirar));
        int tipoCasillaAntesDeTirar = casillaAntesDeTirar.getTipo();
        int tipoCasillaDespuesDeTirar = casillaDespuesDeTirar.getTipo();
        boolean esTiradaDoble = false;
        if (resultadoDado1 == resultadoDado2) {
            esTiradaDoble = true;
            
        }
        
        return "";
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
}
