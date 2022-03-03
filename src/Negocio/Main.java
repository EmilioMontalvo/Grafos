/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Interfaz.JFLaberinto;

/**
 * Clase Main que inicializa con la ejecución del programa
 * @version 1.0, 28/02/22
 * @author Leines Eduardo, Montalvo Emilio, Matute Israel GR11
 */

public class Main {

    /**
	 * Método Main() que crea el laberinto y tira la ventana del programa
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFLaberinto interfaz=new JFLaberinto();
        interfaz.setVisible(true);
        
       
    }
    
}
