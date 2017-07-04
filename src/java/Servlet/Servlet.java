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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
            out.println(respuesta(debug(control)));
        }
        if (comando.compareToIgnoreCase("cargar") == 0) {
            
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
        this.control = new Control(rutaPartidas, rutaConf, n1, n2, n3, n4, n5, n6, n7);
        session.setAttribute("control", this.control);
        return "";
    }

    private String cargar() {
        return "";
    }
    
    private String debug(Control control) {
        String htmlDepurar = "";       
        Tablero tablero = control.getTablero();
        ArrayList jugadores = tablero.getJugadores(); 
        htmlDepurar += "<h1>Jugadores</h1>";
        for (int i = 0; i < jugadores.size(); i++) {
            htmlDepurar += "<p>";
            Jugador jugador = (Jugador)jugadores.get(i);            
            htmlDepurar += jugador.getNombre() + "(" + jugador.getId() +  "), " + jugador.getDinero() + ", "  + jugador.getPosicion() + "</p>";
        }
        htmlDepurar += "<h1>Calles</h1>";
        ArrayList Casillas = tablero.getCasillas();                
        for (int i = 0; i < Casillas.size(); i++) {
            htmlDepurar += "<p>";
            Casilla casilla = (Casilla)Casillas.get(i);
            htmlDepurar += casilla.getNombre() + "(" + casilla.getId() +  "), " + casilla.getIdGrupo() + ", "  + casilla.getGrupo()+ "</p>";
        }
        return htmlDepurar;
    }
    
    private String respuesta(String contenido) {
        String html = "";
        html += "<!DOCTYPE html>";
        html += "<html>";
        html += "<head>";
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
