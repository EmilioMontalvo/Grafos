/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Laberinto {

 public int contador = 0;
    public int[][] maze;
    ArrayList<int[][]> pasos;
    public boolean  encontro=false;

    public Laberinto(int[][] laberinto) {
        this.pasos = new ArrayList<>();
        this.maze = laberinto;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public ArrayList<int[][]> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<int[][]> pasos) {
        this.pasos = pasos;
    }
    
    
    // Obtener la ubicación de inicio (x, y) e intentar resolver el laberinto
    public int[][] resolver(int x, int y) {
	if (paso(x,y)) {   // intentará resolver el laberinto en estas coordenadas
            
            return maze; // introduce en las coordenadas (x, y) la entrada
        }else{
            return null;
        }
        
    }

    // Metodo Backtracking
    public boolean paso(int x, int y) {
		
	contador++;
		
	//System.out.println(x+" "+y);
        
	if(x>=maze.length||y>=maze[0].length||x<0||y<0){
            return false;
        }	
	/** Caso aceptar - Se encontro la salida **/
	if (maze[x][y] == 2) {   // si hemos llegado a 2 quiere decir que hemos encontrado solución
            encontro=true;
            return true;
        }
        
        /** Caso rechazo - Chocamos con un muro o un recorrido **/
        if (maze[x][y] == 5 || maze[x][y] == 3) {
            return false;  // entonces el laberinto no puede resolverse y termina.
        }
        /** Pasos backtracking **/

        // Marcar esta ubicación como parte de nuestro camino
        maze[x][y] = 3;
        addMatriz(maze);
        boolean resultado;	
        
        // Intenta ir a la derecha
       // try{
        resultado = paso(x, y+1);
        //}catch(Exception e){
        //  resultado=false;      
        //}
        if (resultado) { return true;}
		
	// Intenta ir arriba
        
        resultado = paso(x-1, y);
        
        if (resultado) { return true;}
		
	// Intenta ir a la izquierda
        
        resultado = paso(x, y-1);
       
        if (resultado) { return true;}		
		
	// Intenta ir abajo
        
        resultado = paso(x+1, y);
        
        if (resultado) { return true;}		
		
		
	/** Callejón sin salida - esta ubicación no puede ser parte de la solución **/
		
	// Desmarcar esta ubicación
        // en el caso de no ser el resultado, se marca con 0. Ya fue pisado
        
        maze[x][y] = 0;
	addMatriz(maze);
	// Si no hemos encontrado la solución en estos cuatros movimientos, volvemos atrás, aunque hay que
    	// considerar que en este punto, todas las llamadas recursivas han finalizado. Si en ninguna de ellas
    	// se ha conseguido el éxito, entonces el algoritmo termina y devuelve false.
        return false;
    }
    
    
    public void addMatriz(int[][]matriz) {
        
        int[][] nuevo=new int[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                nuevo[i][j]=matriz[i][j];
            }
        }
        pasos.add(nuevo);
    }
    
    public boolean salida(){
        
        return this.encontro;
    }
   

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String salida = "";
        for (int x=0; x<maze.length; x++) {
            for (int y=0; y<maze[0].length; y++) {
                salida += maze[x][y] + " ";
            }
            salida += "\n";
        }
        return salida;
    }
    
    public static void main(String[] args) {
        int[][] matrizInicial = {{5, 5, 5, 5, 5, 5, 5, 5},
			{5, 0, 0, 0, 5, 0, 5, 0},
			{5, 1, 0, 0, 5, 5, 5, 0},
			{5, 0, 0, 0, 5, 2, 5, 0},
			{5, 0, 0, 0, 5, 5, 5, 0},
			{5, 5, 5, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 5, 5, 5},
			{5, 0, 5, 0, 0, 0, 5, 0}
			};
                  
        int[][] matrizResuelta;
        Laberinto m = new Laberinto(matrizInicial);
        // Indicar la salida
       
		
	//Empezar a resolver el laberinto desde(9, 1)
	//m.resolver(9, 1);
        matrizResuelta = m.resolver(2, 1);
        
        //System.out.println();
        //matrizResuelta = m.resolver(9, 1);
        /*
        for (int x=0; x<matrizResuelta.length; x++) {
            for (int y=0; y<matrizResuelta[0].length; y++) {
                System.out.append(matrizResuelta[x][y] + " ");
            }
            System.out.append("\n");
        }
        System.out.println("Llamadas de la funcion: " + m.contador);
        */
        
        for (int[][] o:m.getPasos()){
            for (int x=0; x<o.length; x++) {
            for (int y=0; y<o[0].length; y++) {
                System.out.append(o[x][y] + " ");
            }
            System.out.append("\n");
        }
        System.out.println("Llamadas de la funcion: " + m.contador);
        }
    }
}
