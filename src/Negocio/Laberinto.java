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
public class Laberinto {

  public int contador = 0;
	
	public int[][] maze; /*=
		   {{'5', '5', '5', '5', '5', '5', '5', '5', '5', '5'},
			{'5', ' ', ' ', ' ', '5', ' ', '5', ' ', ' ', '5'},
			{'5', ' ', ' ', ' ', '5', ' ', '5', ' ', '5', '5'},
			{'5', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '5'},
			{'5', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '5'},
			{'5', '5', '5', ' ', ' ', ' ', ' ', ' ', ' ', '5'},
			{'5', ' ', ' ', ' ', ' ', '5', '5', '5', '5', '5'},
			{'5', ' ', '5', ' ', ' ', ' ', '5', ' ', '5', '5'},
			{'5', ' ', '5', ' ', ' ', ' ', ' ', ' ', ' ', '5'},
			{'5', ' ', '5', '5', '5', '5', '5', '5', '5', '5'},
                   {'5', '5', '5', '5', '5', '5', '5', '5', '5', '5'}};*/

	// Obtener la ubicación de inicio (x, y) e intentar resolver el laberinto
	public void resolver(int x, int y) {
		if (paso(x,y)) {
			maze[x][y] = 1;
		}
	}
	
	// Backtracking method
	public boolean paso(int x, int y) {
		
		contador++;
		
		//System.out.println(this.toString());
		
		/** Caso aceptar - Se encontro la salida **/
		if (maze[x][y] == 2) {
			return true;
		}
		
		/** Caso rechazo - Chocamos con un muro o un recorrido **/
		if (maze[x][y] == 5 || maze[x][y] == 3) {
			return false;
		}
		
		/** Pasos backtracking **/
		
		// Marcar esta ubicación como parte de nuestro camino
		maze[x][y] = 3;
		boolean resultado;	
		
		// Intenta ir a la derecha
		resultado = paso(x, y+1);
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
		maze[x][y] = 0;
		
		// Regresar
		return false;
	}
	
	public String toString() {
		String salida = "";
		for (int x=0; x<11; x++) {
			for (int y=0; y<10; y++) {
				salida += maze[x][y] + " ";
			}
			salida += "\n";
		}
		return salida;
	}
	/*
	public static void main(String[] args) {
		Laberinto m = new Laberinto();
		// Indicar la salida
		m.maze[5][4] = '2';
		
		// Empezar a resolver el laberinto desde(9, 1)
		m.resolver(9, 1);
		System.out.println(m);
		System.out.println("Llamadas de la funcion: " + m.contador);
	}
*/
}
