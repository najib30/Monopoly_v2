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
                            <tr id="idIniciar">                                
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
                                                    Jugador 5: <input type="text" name="jug5">                                                    
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 6: <input type="text" name="jug6">
                                                </td>
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 7: <input type="text" name="jug7">
                                                </td>                                                
                                                <td style="height: 50%; width: 1%;">
                                                    Jugador 8: <input type="text" name="jug8">
                                                </td>                                                                                                
                                                <td style="height: 50%; width: 96%;">                                                    
                                                </td>                                                                                                                                                
                                            </tr>
                                            <tr>
                                                <td colspan="5">                                                                                                      
                                                    <input type="hidden" value="iniciar" name="comando">                                                   
                                                    <input type="submit" value="Iniciar">
                                                </td>
                                            </tr>
                                               
                                        </table>    
                                        
                                        
                                    </form>                                                
                                    
                                </td>
                            </tr>
                            
                            
                            <tr id="idCargar">                                
                                <td style="height: 1%; width: 100%;">
                                    <form action="Servlet" method="post"  target="consolaMonopoy">
                                        <table style="height: 100%; width: 100%;">
                                            <tr>
                                                <td style="height: 100%; width: 100%;">
                                                    O cargar Partida <input type="input" name="nombreCargar"><input type="submit" value="Cargar">
                                                </td>                                                                                                                                       
                                            </tr>
                                        </table>    
                                        <input type="hidden" value="cargar" name="comando">                                        
                                    </form>                                                                                    
                                </td>
                            </tr>

                            <tr id="idGuardar" style="display: none;">                                
                                <td style="height: 1%; width: 100%;">
                                    <form action="Servlet" method="post"  target="consolaMonopoy">
                                        <table style="height: 100%; width: 100%;">
                                            <tr>
                                                <td style="height: 100%; width: 100%;">
                                                    Guardar Partida <input type="input" name="nombreGuardar"><input type="submit" value="Guardar">
                                                </td>                                                                                                                                       
                                            </tr>
                                        </table>    
                                        <input type="hidden" value="guardar" name="comando">                                        
                                    </form>                                                                                    
                                </td>
                            </tr>

                            <tr id="idLanzar" style="display: none;"> 
                                <td style="height: 1%; width: 100%;">
                                    <table style="height: 100%; width: 100%;">
                                        <tr>

                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post" target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Lanzar Dados">
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="lanzar" name="comando">                                        
                                                </form>                                                                                    
                                            </td>
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input id = "idComprar" type="submit" value="Comprar" disabled>
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="comprar" name="comando">                                        
                                                </form>                                                                                    
                                            </td>
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Edif. 1 Casa" disabled>
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="edificarCasa1" name="comando">                                        
                                                </form>                                                                                    
                                            </td>                                
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Edif. 2 Casas" disabled>
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="edificarCasa2" name="comando">                                        
                                                </form>                                                                                    
                                            </td>                                
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Edif. 3 Casas" disabled>
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="edificarCasa3" name="comando">                                        
                                                </form>                                                                                    
                                            </td>                                
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Edif. 4 Casas" disabled>
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="edificarCasa4" name="comando">                                        
                                                </form>                                                                                    
                                            </td>                                
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Edif. Hotel" disabled>
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="edificarHotel" name="comando">                                        
                                                </form>                                                                                    
                                            </td>                                
                                            <td style="height: 1%; width: 1%;">
                                                <form action="Servlet" method="post"  target="consolaMonopoy">
                                                    <table style="height: 100%; width: 100%;">
                                                        <tr>
                                                            <td style="height: 100%; width: 100%;">
                                                                <input type="submit" value="Resumen">
                                                            </td>                                                                                                                                       
                                                        </tr>
                                                    </table>    
                                                    <input type="hidden" value="resumen" name="comando">                                        
                                                </form>                                                                                    
                                            </td>                                
                                            
                                            <td style="height: 1%; width: 93%;">                                                    
                                            </td>                                                                                                                                                                                                                
                                        </tr>
                                    </table>
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
             <p>Proyectos InformÃ¡ticos 2 UOLS - Monopoly Najib e Ibai</p>
        </div>        
    </body>
</html>