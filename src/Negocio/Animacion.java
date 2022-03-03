/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.awt.Color;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Clase Animación que permite la presentación animada de la solución encontrada 
 * @version 1.0, 28/02/22
 * @author Leines Eduardo, Montalvo Emilio, Matute Israel GR11
 */
public class Animacion extends Thread{
	//Variables
    int[][]sol;				//Matriz solución 
    JButton[][] laberinto;  //Matriz de botones/celdas
    int n;					//dimensión matriz
    Icon raton;				//Icono Ratón
    Icon queso;				//Icono Queso
    Laberinto l;			//Laberinto 
    int iniciox;	        //Coordenada de inicio X
    int inicioy;			//Coordenada de inicio y
	/**
	 * Constructor de Animación 
	 * @param laberinto
	 * @param n
	 * @param raton
	 * @param queso
	 * @param l
	 * @param iniciox
	 * @param inicioy
	 */
    public Animacion(JButton[][] laberinto, int n, Icon raton, Icon queso, Laberinto l, int iniciox, int inicioy) {
        this.laberinto = laberinto;
        this.n = n;
        this.raton = raton;
        this.queso = queso;
        this.l = l;
        this.iniciox = iniciox;
        this.inicioy = inicioy;
    } //Fin del Constructor
	
	//METODOS
  
    @Override
	/**
	 * Método que busca la solución al laberinto teniendo en cuenta
	 * el punto de partida. 
	 */
    public void run() {
        int[][]sol=l.resolver(iniciox, inicioy);
        
                
            for (int[][] x : l.getPasos()) {
            x[iniciox][inicioy]=1;
			//Actualiza el laberinto con el cambio realizado según cada paso dado. 
            setMatriz(x);
            try {
                Thread.sleep(750);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		//Actualiza el laberinto con el cambio realizado final.  
        sol[iniciox][inicioy]=1;
        setMatriz(sol);
        
        
    }//Fin de Método 
    /**
	 * Método que construye una matriz según el estado actual de todos los objetos en 
	 * cada paso realizado. 
	 * @param matriz 
	 */
  private void setMatriz(int[][] matriz){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch(matriz[i][j]){
                    case 0:
                        laberinto[i][j].setIcon(null);
                        laberinto[i][j].setBackground(Color.white);
                        break;
                    case 1:
                        laberinto[i][j].setIcon(raton);
                        laberinto[i][j].setBackground(Color.white);
                        break;
                    case 2:
                        laberinto[i][j].setIcon(queso);
                        laberinto[i][j].setBackground(Color.white);
                        break;
                    case 3:
                        laberinto[i][j].setIcon(null);
                        laberinto[i][j].setBackground(Color.yellow);
                        break;
                    case 5:
                        laberinto[i][j].setIcon(null);
                        laberinto[i][j].setBackground(Color.black);
                        break;
                }
            }
        }
    }//Fin de Método 
}
