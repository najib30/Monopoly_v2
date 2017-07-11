/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Contenedor.Casilla;
import Contenedor.Jugador;
import Contenedor.Tablero;
import Control.Control;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Najib e Ibai
 */

public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    
    Control control;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //response.setContentType( "text/html; charset=iso-8859-1" );
        PrintWriter out = response.getWriter();
        String comando = request.getParameter("comando");        
        System.out.println("doPost: Comando " + comando);
        if (comando.compareToIgnoreCase("iniciar") == 0) {
            iniciar(request, response);
            out.println(respuesta(debug(this.control)));
        }
        if (comando.compareToIgnoreCase("cargar") == 0) {
            cargar(request);
            out.println(respuesta(debug(this.control)));
        }
        if (comando.compareToIgnoreCase("guardar") == 0) {            
            out.println(htmlConsola(guardar(request)));            
        }                                    
        if (comando.compareToIgnoreCase("lanzar") == 0) {
            out.println(htmlConsola(lanzarDados(request)));
        }                                    
        if (comando.compareToIgnoreCase("comprar") == 0) {            
            out.println(htmlConsola(comprar(request)));
        }                                    
        if (comando.compareToIgnoreCase("edificarCasa1") == 0) {
            out.println(htmlConsola(edificar(1, 0, request)));            
        }                                    
        if (comando.compareToIgnoreCase("edificarCasa2") == 0) {
            out.println(htmlConsola(edificar(2, 0, request)));
        }                                    
        if (comando.compareToIgnoreCase("edificarCasa3") == 0) {
            out.println(htmlConsola(edificar(3, 0, request)));
        }                                    
        if (comando.compareToIgnoreCase("edificarCasa4") == 0) {
            out.println(htmlConsola(edificar(4, 0, request)));
        }                                    
        if (comando.compareToIgnoreCase("edificarHotel") == 0) {
            out.println(htmlConsola(edificar(0, 1, request)));
        }
        if (comando.compareToIgnoreCase("resumen") == 0) {
            HttpSession session = request.getSession();
            this.control = (Control)session.getAttribute("control");
            out.println(respuesta(debug(this.control)));
            session.setAttribute("control", this.control);                    
        }
        
       
        
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String iniciar(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String rutaPartidas = System.getProperty("user.home") + "\\Desktop\\confMonopoly\\";
        System.out.println("Servlet Iniciar: rutaPartidas " + rutaPartidas);
        ServletContext srvcon = getServletContext();
        String rutaConf = srvcon.getRealPath("WEB-INF");
        System.out.println("Servlet Iniciar: rutaConf " + rutaConf);
        String n1 = request.getParameter("jug1");
        String n2 = request.getParameter("jug2");
        String n3 = request.getParameter("jug3");
        String n4 = request.getParameter("jug4");
        String n5 = request.getParameter("jug5");
        String n6 = request.getParameter("jug6");
        String n7 = request.getParameter("jug7");
        String n8 = request.getParameter("jug8");
        this.control = new Control(rutaPartidas, rutaConf, n1, n2, n3, n4, n5, n6, n7, n8);
        session.setAttribute("control", this.control);
        return "";
    }

    private String cargar() {
        return "";
    }
    
    private String lanzarDados(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.control = (Control)session.getAttribute("control");
        String textoConsola = this.control.jugar();            
        session.setAttribute("control", this.control);
        return textoConsola;
    }
    private String comprar(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.control = (Control)session.getAttribute("control");
        String textoConsola = this.control.comprarPropiedad();
        session.setAttribute("control", this.control);        
        return textoConsola;
    }
    private String edificar(int numeroCasas, int numeroHotel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.control = (Control)session.getAttribute("control");
        String textoConsola = this.control.edificarPropiedad(numeroCasas, numeroHotel);
        session.setAttribute("control", this.control);        
        return textoConsola;
    }
        
    private String debug(Control control) {
        String htmlDepurar = "";       
        Tablero tablero = control.getTablero();
        ArrayList Casillas = tablero.getCasillas();                
        ArrayList jugadores = tablero.getJugadores(); 
        htmlDepurar += "<h1>Jugadores</h1>";
        for (int i = 0; i < jugadores.size(); i++) {
            htmlDepurar += "<p>";
            Jugador jugador = (Jugador)jugadores.get(i);            
            htmlDepurar += jugador.getNombre() + "(Jugador:" + (jugador.getId() + 1) +  "), Dinero: " + jugador.getDinero() + ", Posicion (0-39)"  + jugador.getPosicion() + "->" + ((Casilla)Casillas.get(jugador.getPosicion())).getNombre()+ "</p>";
            ArrayList posesiones = jugador.getPropiedades(); 
            htmlDepurar += "<p>Contiene las casillas:";
            for (int j = 0; j < posesiones.size(); j++) {
                int idCasilla = (Integer)posesiones.get(j);
                Casilla casilla = (Casilla)Casillas.get(idCasilla);
                htmlDepurar += " || " + casilla.getNombre();
            }
            htmlDepurar += "</p>";
        }
        htmlDepurar += "<h1>Calles</h1>";        
        for (int i = 0; i < Casillas.size(); i++) {
            htmlDepurar += "<p>";
            Casilla casilla = (Casilla)Casillas.get(i);
            htmlDepurar += casilla.getNombre() + "(id-" + casilla.getId() +  ",idG-" + casilla.getIdGrupo() + ",TotalG:"  + casilla.getGrupo()+ ")</p>";
            htmlDepurar += "<p>";
            htmlDepurar += "NumeroCasas:" + casilla.getNumeroDeCasas() +  ",NumeroHoteles:"  + casilla.getNumeroDeHoteles()+ "</p>";
            htmlDepurar += "<br>";
        }
        return htmlDepurar;
    }
    
   private String guardar(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String nombre = request.getParameter("nombreGuardar"); 
        this.control = (Control)session.getAttribute("control");
        session.setAttribute("control", this.control);      
        String rutaPartidas = this.control.getRutaPartidas();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaPartidas + "\\" + nombre));
            oos.writeObject(this.control);
            oos.close();            
        } 
        catch (IOException ex) {
        }
        session.setAttribute("control", this.control);
        return "Partida guardada en " + rutaPartidas + "\\" + nombre;
    }
    
   
    private String cargar(HttpServletRequest request) {
        String rutaPartidas = System.getProperty("user.home") + "\\Desktop\\confMonopoly\\";
        String nombre = request.getParameter("nombreCargar"); 
        Object partida = null;       
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaPartidas + "\\" + nombre));        
            partida = ois.readObject();
            while (partida!=null) {
                partida = ois.readObject();
                ois.close();
            }            
        }
        catch (Exception ex) {            
        }
        HttpSession session = request.getSession();
        this.control = (Control)partida;
        session.setAttribute("control", (Control)partida);
        return "";        
    }  
    
    private String respuesta(String contenido) {
        String html = "";
        html += "<!DOCTYPE html>";
        html += "<html>";
        html += "<head>";
        html += "<script>";
        html += "window.parent.document.getElementById('idIniciar').style.display='none';";
        html += "window.parent.document.getElementById('idCargar').style.display='none';";
        html += "window.parent.document.getElementById('idGuardar').style.display='block';";
        html += "window.parent.document.getElementById('idLanzar').style.display='block';";
        html += "</script>";
        html += "<title>Servlet Servlet</title>"; 
        html += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">";
        html += "</head>";
        html += "<body>";
        html += contenido;
        html += "</body>";
        html += "</html>";
        return html;
    }
   
    private String htmlConsola(String contenido) {
        String html = "";
        html += "<!DOCTYPE html>";
        html += "<html>";
        html += "<head>";
        html += "<script>";        
        if (this.control.getAcaboJuego() == 0) {
            html += "window.parent.document.getElementById('idIniciar').style.display='none';";
            html += "window.parent.document.getElementById('idCargar').style.display='none';";
            html += "window.parent.document.getElementById('idGuardar').style.display='block';";
            html += "window.parent.document.getElementById('idLanzar').style.display='block';";
            if (this.control.getSePuedeComprar() != 0) {
                 html += "window.parent.document.getElementById('idComprar').disabled=false;";
            }
            else {
                html += "window.parent.document.getElementById('idComprar').disabled=true;";
            }
            if (this.control.getSePuedeEdificar()== 0) {
                html += "window.parent.document.getElementById('edificarCasa1').disabled=true;";
                html += "window.parent.document.getElementById('edificarCasa2').disabled=true;";
                html += "window.parent.document.getElementById('edificarCasa3').disabled=true;";
                html += "window.parent.document.getElementById('edificarCasa4').disabled=true;";
                html += "window.parent.document.getElementById('edificarHotel').disabled=true;";            
            }
            else {
                if (this.control.getNumeroAEdificar()== 4) {
                    html += "window.parent.document.getElementById('edificarHotel').disabled=false;";            
                }
                if (this.control.getNumeroAEdificar()== 3) {                
                    html += "window.parent.document.getElementById('edificarCasa1').disabled=false;";                                
                }
                if (this.control.getNumeroAEdificar()== 2) {                
                    html += "window.parent.document.getElementById('edificarCasa1').disabled=false;";
                    html += "window.parent.document.getElementById('edificarCasa2').disabled=false;";
                }
                if (this.control.getNumeroAEdificar()== 1) {                
                    html += "window.parent.document.getElementById('edificarCasa1').disabled=false;";                                
                    html += "window.parent.document.getElementById('edificarCasa2').disabled=false;";
                    html += "window.parent.document.getElementById('edificarCasa3').disabled=false;";
                }
                if (this.control.getNumeroAEdificar()== 0) {                
                    html += "window.parent.document.getElementById('edificarCasa1').disabled=false;";                                
                    html += "window.parent.document.getElementById('edificarCasa2').disabled=false;";
                    html += "window.parent.document.getElementById('edificarCasa3').disabled=false;";                
                    html += "window.parent.document.getElementById('edificarCasa4').disabled=false;";                
                }            
            }
        }
        else {
            html += "window.parent.document.getElementById('idIniciar').style.display='none';";
            html += "window.parent.document.getElementById('idCargar').style.display='none';";
            html += "window.parent.document.getElementById('idGuardar').style.display='none';";
            html += "window.parent.document.getElementById('idLanzar').style.display='none';";
            html += "window.parent.document.getElementById('idComprar').disabled=true;";
            html += "window.parent.document.getElementById('edificarCasa1').disabled=true;";
            html += "window.parent.document.getElementById('edificarCasa2').disabled=true;";
            html += "window.parent.document.getElementById('edificarCasa3').disabled=true;";
            html += "window.parent.document.getElementById('edificarCasa4').disabled=true;";
            html += "window.parent.document.getElementById('edificarHotel').disabled=true;";            
        }
        html += "</script>";
        html += "<title>Servlet Servlet</title>"; 
        html += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">";
        html += "</head>";
        html += "<body>";
        html += contenido;
        html += "</body>";
        html += "</html>";
        return html;
    }    
    
    
}
