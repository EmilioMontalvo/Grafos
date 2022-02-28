/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author HP
 */
public class LaberintoHilo extends Thread {
    Laberinto l;
    boolean camino=false;
    int iniciox;
    int inicioy;

    public LaberintoHilo(Laberinto l) {
        this.l = l;
       
    }

    @Override
    public void run() {
        System.out.println("Hola");
        camino=l.paso(iniciox, inicioy);
        
    }

    public Laberinto getL() {
        return l;
    }

    public void setL(Laberinto l) {
        this.l = l;
    }

   
    public int getIniciox() {
        return iniciox;
    }

    public boolean getCamino() {
        return camino;
    }

    public void setCamino(boolean camino) {
        this.camino = camino;
    }

    public void setIniciox(int iniciox) {
        this.iniciox = iniciox;
    }

    public int getInicioy() {
        return inicioy;
    }

    public void setInicioy(int inicioy) {
        this.inicioy = inicioy;
    }
    
    
    
}
