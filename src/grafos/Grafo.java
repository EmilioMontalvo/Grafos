package grafos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Grafo {
	private HashMap<Nodo, TreeSet<Nodo>> listaDeAdjacencia;
	private HashMap<String, Nodo> nodos;
	private static final TreeSet<Nodo> EMPTY_SET = new TreeSet<Nodo>();
	private int numNodos;
	private int numAristas;

	/**
	 * Construct empty Graph
	 */
	public Grafo() {
		listaDeAdjacencia = new HashMap<Nodo, TreeSet<Nodo>>();
		nodos = new HashMap<String, Nodo>();
		numNodos = numAristas = 0;

	}

	/**
	 * Añade un nodo sin vecinos (si esque aun no existen nodos)
	 * 
	 * @param name vertex to be added
	 */
	public Nodo addNodo(String name) {
		Nodo v;
		v = nodos.get(name);
		if (v == null) {
			v = new Nodo(name);
			nodos.put(name, v);
			listaDeAdjacencia.put(v, new TreeSet<Nodo>());
			numNodos += 1;
		}
		return v;
	}

	/**
	 * Returns the Nodo matching v
	 * 
	 * @param nombre a String nombre of a Nodo that may be in this Grafo
	 * @return the Nodo with a nombre that matches v or null if no such Nodo
         exists in this Grafo
	 */
	public Nodo getVertices(String nombre) {
		return nodos.get(nombre);
	}

	/**
	 * Returns true iff v is in this Grafo, false otherwise
	 * 
	 * @param nombre a String nombre of a Nodo that may be in this Grafo
	 * @return true iff v is in this Grafo
	 */
	public boolean tieneNodo(String nombre) {
		return nodos.containsKey(nombre);
	}

	/**
	 * Is from-to, an edge in this Graph. The graph is undirected so the order of
	 * from and to does not matter.
	 * 
	 * @param origen the nombre of the first Nodo
	 * @param destino   the nombre of the second Nodo
	 * @return true iff origen-destino exists in this Grafo
	 */
	public boolean tieneAristas(String origen, String destino) {

		if (!tieneNodo(origen) || !tieneNodo(destino))
			return false;
		return listaDeAdjacencia.get(nodos.get(origen)).contains(nodos.get(destino));
	}

	/**
	 * Add to to from's set of neighbors, and add from to to's set of neighbors.
	 * Does not add an edge if another edge already exists
	 * 
	 * @param origen the nombre of the first Nodo
	 * @param destino   the nombre of the second Nodo
	 */
	public void addArista(String origen, String destino) {
		Nodo v;
                Nodo w;
		if (tieneAristas(origen, destino))
			return;
		numAristas += 1;
		if ((v = getVertices(origen)) == null)
			v = addNodo(origen);
		if ((w = getVertices(destino)) == null)
			w = addNodo(destino);
		listaDeAdjacencia.get(v).add(w);
		listaDeAdjacencia.get(w).add(v);
	}

	/**
	 * Return an iterator over the neighbors of Nodo named v
	 * 
	 * @param name the String nombre of a Nodo
	 * @return an Iterator over Vertices that are adjacent to the Nodo named v,
         empty set if v is not in graph
	 */
	public Iterable<Nodo> adjacenteA(String name) {
		if (!tieneNodo(name))
			return EMPTY_SET;
		return listaDeAdjacencia.get(getVertices(name));
	}

	/**
	 * Return an iterator over the neighbors of Nodo v
	 * 
	 * @param v the Nodo
	 * @return an Iterator over Vertices that are adjacent to the Nodo v, empty
         set if v is not in graph
	 */
	public Iterable<Nodo> adjacenteA(Nodo v) {
		if (!listaDeAdjacencia.containsKey(v))
			return EMPTY_SET;
		return listaDeAdjacencia.get(v);
	}

	/**
	 * Returns an Iterator over all Vertices in this Grafo
	 * 
	 * @return an Iterator over all Vertices in this Grafo
	 */
	public Iterable<Nodo> getVertices() {
		return nodos.values();
	}

	public int numNodos() {
		return numNodos;
	}

	public int numAristas() {
		return numAristas;
	}

	
	/*
	 * Returns adjacency-list representation of graph
	 */
	public String toString() {
		String s = "";
		for (Nodo v : nodos.values()) {
			s += v + ": ";
			for (Nodo w : listaDeAdjacencia.get(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}

	public String escapedVersion(String s) {
		return "\'" + s + "\'";

	}

	public void outputGDF(String fileName) {
		HashMap<Nodo, String> idToName = new HashMap<Nodo, String>();
		try {
			FileWriter out = new FileWriter(fileName);
			int count = 0;
			out.write("nodedef> name,label,style,distance INTEGER\n");
			// write nodos
			for (Nodo v : nodos.values()) {
				String id = "v" + count++;
				idToName.put(v, id);
				out.write(id + "," + escapedVersion(v.nombre));
				out.write(",6," + v.distancia + "\n");
			}
			out.write("edgedef> node1,node2,color\n");
			// write edges
			for (Nodo v : nodos.values())
				for (Nodo w : listaDeAdjacencia.get(v))
					if (v.compareTo(w) < 0) {
						out.write(idToName.get(v) + "," + idToName.get(w) + ",");
						if (v.predecesor == w || w.predecesor == v)
							out.write("blue");
						else
							out.write("gray");
						out.write("\n");
					}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
        
        public boolean DFS(String inicio,String fin){
            List abierto=new LinkedList();
            List cerrado=new LinkedList();
            abierto.add(inicio);
            String x=(String) abierto.get(0);
            while (!abierto.isEmpty() && !x.equals(fin) ) {                
                x=x=(String) abierto.get(0);
                abierto.remove(0);
                
                if (!cerrado.contains(x)){
                    cerrado.add(x);
                    this.listaDeAdjacencia.get((Nodo)this.nodos.get(x));
                }
            }
            
            
            return false;
        }
        
        

}