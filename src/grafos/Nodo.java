package grafos;

public class Nodo implements Comparable<Nodo> {
	/**
	 * label for Nodo
	 */
	public String nombre;
	/**
	 * length of shortest path from source
	 */
	public int distancia;
	/**
	 * previous vertex on path from sourxe
	 */
	public Nodo predecesor; // previous vertex

	/**
	 * a measure of the structural importance of a vertex. The value should
	 * initially be set to zero. A higher centrality score should mean a Vertex is
	 * more central.
	 */
	public double centrality;
	/**
	 * Infinite distancia indicates that there is no path from the source to this
 vertex
	 */
	public static final int INF = Integer.MAX_VALUE;

	public Nodo(String v) {
		nombre = v;
		distancia = INF; // start as infinity away
		predecesor = null;
		centrality = 0.0;
	}

	/**
	 * The nombre of the Nodo is assumed to be unique, so it is used as a HashCode
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return nombre.hashCode();
	}

	public String toString() {
		return nombre;
	}

	/**
	 * Compare on the basis of distancia from source first and then lexicographically
	 */
	public int compareTo(Nodo other) {
		int diff = distancia - other.distancia;
		if (diff != 0)
			return diff;
		else
			return nombre.compareTo(other.nombre);
	}
}
