<%-- 
    Document   : index
    Created on : 01.07.2017, 12:54:06
    Author     : Najib e Ibai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monopoly Najib e Ibai</title>
        <style>
         * {
         margin: 0;
         }
         html, body, iframe {
         height: 100%;
         }
         .wrapper {
         min-height: 100%;
         height: auto !important;
         height: 100%;
         margin: 0 auto -2em;
         }
         .footer, .push {
         height: 2em;
         }
        </style>        
    </head>
    <body>
        <div class="wrapper">
            <table style="height: 95%; width: 100%;">
                <tr>
                    <td style="height: 100%; width: 100%;">
                        <table style="height: 100%; width: 100%;">
                            <tr>                                
                                <td style="height: 1%; width: 100%;">
                                    <h2>Monopoly</h2>                                    
                                </td>
                            </tr>                            
                            <tr>                                
                                <td style="height: 1%; width: 100%;">
                                    <form action="Servlet" method="post" target="consolaMonopoy">
                                        <table style="height: 100%; width: 100%;">
                                            <tr>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 1: <input type="text" name="jug1">
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 2: <input type="text" name="jug2">                                                    
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 3: <input type="text" name="jug3">
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 4: <input type="text" name="jug4">
                                                </td>                                                
                                                <td style="height: 50%; width: 96%;">                                                    
                                                </td>                                                                                                
                                            </tr>
                                            <tr>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 5: <input type="text" name="jug2">                                                    
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 6: <input type="text" name="jug3">
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 7: <input type="text" name="jug4">
                                                </td>                                                
                                                <td style="height: 50%; width: 1%;">   
                                                    <br>
                                                    <input type="hidden" value="iniciar" name="comando">                                                   
                                                    <input type="submit" value="Iniciar">                                                    
                                                </td>                                                                                                
                                                <td style="height: 50%; width: 96%;">                                                    
                                                </td>                                                                                                                                                
                                            </tr>                                            
                                        </table>    
                                        
                                        
                                    </form>                                                
                                    
                                </td>
                            </tr>
                            <tr>                                
                                <td style="height: 1%; width: 100%;">
                                    <form action="Servlet" method="post" ENCTYPE="text/plain" target="consolaMonopoy">
                                        <table style="height: 100%; width: 100%;">
                                            <tr>
                                                <td style="height: 100%; width: 100%;">
                                                    O cargar Partida <input type="input" name="nombreCargar"><input type="submit" value="Cargar">
                                                </td>                                                                                                                                       
                                            </tr>
                                        </table>    
                                        <input type="hidden" value="prueba" name="cargar">                                        
                                    </form>                                                                                    
                                </td>
                            </tr>
                            
                            <tr>
                                <td style="height: 97%; width: 100%;">
                                    <iframe name="consolaMonopoy" style="overflow:hidden;height:100%;width:100%" height="100%" width="100%"></iframe>
                                </td>
                            </tr>
                            
                        </table>
                    </td>
                </tr>
            </table>
            <div class="push"></div>
        </div>
        <div class="footer">
             <p>Proyectos Informáticos 2 UOLS - Monopoly Najib e Ibai</p>
        </div>        
    </body>
</html>