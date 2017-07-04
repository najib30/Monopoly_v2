/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contenedor;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Najib e Ibai
 */
public class Dado implements Serializable {
    
    public int lanzar() {
        Random rnd = new Random();
        return (int)(rnd.nextDouble() * 6 + 1);
    }
}
