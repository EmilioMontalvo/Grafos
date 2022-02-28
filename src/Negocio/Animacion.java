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
 *
 * @author HP
 */
public class Animacion extends Thread{

    int[][]sol;
    JButton[][] laberinto;
    int n;
    Icon raton;
    Icon queso;
    Laberinto l;
    int iniciox;
    int inicioy;

    public Animacion(JButton[][] laberinto, int n, Icon raton, Icon queso, Laberinto l, int iniciox, int inicioy) {
        this.laberinto = laberinto;
        this.n = n;
        this.raton = raton;
        this.queso = queso;
        this.l = l;
        this.iniciox = iniciox;
        this.inicioy = inicioy;
    }

  
    
    
    
    @Override
    public void run() {
        int[][]sol=l.resolver(iniciox, inicioy);
        
                
            for (int[][] x : l.getPasos()) {
            x[iniciox][inicioy]=1;
            setMatriz(x);
            try {
                Thread.sleep(750);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sol[iniciox][inicioy]=1;
        setMatriz(sol);
        
        
    }
    
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
    }
    
    
}
