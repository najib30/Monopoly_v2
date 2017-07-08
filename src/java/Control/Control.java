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
    private int sePuedeComprar;
    private Casilla casillaAcomprar;
    private int sePuedeEdificar;
    private int numeroAEdificar;
    private Jugador jugadorQueCompra;
    
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
        ArrayList tarjetas = new ArrayList();
        this.tablero = new Tablero(casillas, jugadores, tarjetas, tarjetas);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.casillaAcomprar = null;
        this.sePuedeEdificar = 0;
        this.numeroAEdificar = 0;
        this.jugadorQueCompra = null;
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
    
    private void crearTarjetas() {
        
    }    
    
    private ArrayList crearJugadores(String n1,String n2,String n3,String n4,
                                    String n5,String n6,String n7, String n8) {
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
        }        
        if (tipoCasillaAntesDeTirar == 6) {//jugador en la carcel
            if (esTiradaDoble) {//jugador sale de la carcel y tira el otra vez
                jugadorActual.setPosicion(posicionDespuesDeTirar);
                textConsola += "(es una tirada doble así que sale de la carcel). Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
            }
            else {//comprobar si es su 4 tirada desde la carcel o si tiene tarjeta salir de la carcel y le toca a el
                if(jugadorActual.getTarjetaSalirCarcel() > 0) {
                    jugadorActual.setPosicion(posicionDespuesDeTirar);
                    jugadorActual.setTarjetaSalirCarcel(jugadorActual.getTarjetaSalirCarcel() - 1);
                    textConsola += ".Usa una tarjeta de salir de la carcel. Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
                }
                else {
                    if(jugadorActual.getTiradasEnCarcel() > 3) {
                        jugadorActual.setTiradasEnCarcel(0);
                        jugadorActual.setPosicion(posicionDespuesDeTirar);
                        textConsola += ".Es su cuarto turno en la carcel. Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
                    }
                    else {
                        jugadorActual.setTiradasEnCarcel(jugadorActual.getTiradasEnCarcel() + 1);
                        textConsola += ".No ha sacado doble y lleva " + jugadorActual.getTiradasEnCarcel() + " tiradas en la carcel";
                        pasarTurno();
                    }
                }
            }
        }
        else {
            textConsola += ".Pasa de la casilla " + nombreCasillaAntesDeTirar + " a la casilla " + nombreCasillaDespuesDeTirar;
            if (tipoCasillaDespuesDeTirar == 0) {//jugador en la salida
                jugadorActual.setDinero(jugadorActual.getDinero() + 200); 
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
                        textConsola += ".La casilla pertenece a ese jugador con lo que se puede edificar";
                    }
                    else {//Pertenece a Otro, a Pagar
                        textConsola += ".La casilla pertenece al jugador " + (propietario + 1) + " con lo que hay que pagar";
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
                    if (propietario == jugadorActual.getId()) {// es del jugador que tiene Turno: Se puede Edificar
                        textConsola += ".La casilla pertenece a ese jugador, con lo que se pasa el turno al jugador siguiente";
                    }
                    else {//Pertenece a Otro, a Pagar
                        textConsola += ".La casilla pertenece al jugador " + (propietario + 1) + " con lo que hay que pagar";
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
                    if (propietario == jugadorActual.getId()) {// es del jugador que tiene Turno: Se puede Edificar
                        textConsola += ".La casilla pertenece a ese jugador, con lo que se pasa el turno al jugador siguiente";
                    }
                    else {//Pertenece a Otro, a Pagar
                        textConsola += ".La casilla pertenece al jugador " + (propietario + 1) + " con lo que hay que pagar";
                    }
                }
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }            
            if (tipoCasillaDespuesDeTirar == 4) {//caja comunidad o suerte
                jugadorActual.setPosicion(posicionDespuesDeTirar);
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
                textConsola += ".Después de pagar el jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros";
            }
            if (tipoCasillaDespuesDeTirar == 8) {//parking
                //No pasa nada
                textConsola += ".En esta casilla no ocurre nada. El jugador " + jugadorActual.getNombre() + " tiene " + jugadorActual.getDinero() + " euros";
                jugadorActual.setPosicion(posicionDespuesDeTirar);
            }
            if(!esTiradaDoble) {
                jugadorActual.setTiradasDoble(0);
                pasarTurno();
            }
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
        /*private Casilla casillaAcomprar;
        private int sePuedeEdificar;
        private int numeroAEdificar;
        private Jugador jugadorQueCompra;*/
        String textConsola = "";
        this.jugadorQueCompra.setDinero(this.jugadorQueCompra.getDinero() - this.casillaAcomprar.getPrecioCompra());
        textConsola += "Se compra la casilla " + this.casillaAcomprar.getNombre() + " por " + this.casillaAcomprar.getPrecioCompra() + " euros y el jugador " + this.jugadorQueCompra.getNombre() + " tiene " + this.jugadorQueCompra.getDinero() + " euros después de la compra";
        textConsola += ".El turno lo tiene el jugador " + (this.turnoJugador + 1);
        ArrayList prop = this.jugadorQueCompra.getPropiedades();
        prop.add(this.casillaAcomprar.getId());
        this.jugadorQueCompra.setPropiedades(prop);
        this.casillaAcomprar = null;
        this.jugadorQueCompra = null;
        this.sePuedeComprar = 0;
        return textConsola;
    }

    public String edificarPropiedad() {
        return "";
    }

    private String pagarEnPropiedad(int idCasilla, int tipoCasilla, int idJugadorQuePaga, int idJugadorPropietario, int idGrupo, int totalGrupo) {
        int posesionesPropietario = getPosesionesDeGrupo(idCasilla, idJugadorPropietario, idGrupo);
        Jugador jugadorQuePaga = (Jugador)(tablero.getJugadores().get(idJugadorQuePaga));  
        Jugador jugadorPropietario = (Jugador)(tablero.getJugadores().get(idJugadorQuePaga));  
        Casilla casilla = (Casilla)(tablero.getCasillas().get(idCasilla));                

        
        int aPagar = 0;
        if (tipoCasilla == 1) {//casilla: En funcion de que haya o no edificios
            if (totalGrupo == posesionesPropietario) {//todo pertenece al que cobra
                //comprobar que no hay nada edificado
                if (getHayEdificacionesEnGrupo(idGrupo) > 0) {//se cobra lo que haya en la casilla
                    
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
        }
        if (tipoCasilla == 3) {//estacion: En funcion del numero que se tenga
        
        }  
        jugadorQuePaga.setDinero(jugadorQuePaga.getDinero() - aPagar);
        jugadorPropietario.setDinero(jugadorPropietario.getDinero() + aPagar);        
        return "";
    }
    
    private int getPosesionesDeGrupo(int idCasilla, int idJugador, int idGrupo) {
        Jugador jugador = (Jugador)(tablero.getJugadores().get(idJugador));
        ArrayList casillas = tablero.getCasillas();
        Casilla casilla = (Casilla)(casillas.get(idCasilla));        
        int totalGrupo = 0;
        int totalJugador = 0;
        for (int i = 0; i < casillas.size(); i++) {
            if (idGrupo == ((Casilla)(casillas.get(i))).getIdGrupo()) {
                totalGrupo++;
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
}