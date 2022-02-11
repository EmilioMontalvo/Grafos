package grafos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Grafo {
	private HashMap<Nodo, TreeSet<Nodo>> listaDeAdjacencia;
	private HashMap<String, Nodo> vertices;
	private static final TreeSet<Nodo> EMPTY_SET = new TreeSet<Nodo>();
	private int myNumVertices;
	private int myNumEdges;

	/**
	 * Construct empty Graph
	 */
	public Grafo() {
		listaDeAdjacencia = new HashMap<Nodo, TreeSet<Nodo>>();
		vertices = new HashMap<String, Nodo>();
		myNumVertices = myNumEdges = 0;

	}

	/**
	 * Añade un nodo sin vecinos (si esque aun no existen nodos)
	 * 
	 * @param name vertex to be added
	 */
	public Nodo addVertex(String name) {
		Nodo v;
		v = vertices.get(name);
		if (v == null) {
			v = new Nodo(name);
			vertices.put(name, v);
			listaDeAdjacencia.put(v, new TreeSet<Nodo>());
			myNumVertices += 1;
		}
		return v;
	}

	/**
	 * Returns the Nodo matching v
	 * 
	 * @param name a String nombre of a Nodo that may be in this Grafo
	 * @return the Nodo with a nombre that matches v or null if no such Nodo
         exists in this Grafo
	 */
	public Nodo getVertex(String name) {
		return vertices.get(name);
	}

	/**
	 * Returns true iff v is in this Grafo, false otherwise
	 * 
	 * @param name a String nombre of a Nodo that may be in this Grafo
	 * @return true iff v is in this Grafo
	 */
	public boolean hasVertex(String name) {
		return vertices.containsKey(name);
	}

	/**
	 * Is from-to, an edge in this Graph. The graph is undirected so the order of
	 * from and to does not matter.
	 * 
	 * @param from the nombre of the first Nodo
	 * @param to   the nombre of the second Nodo
	 * @return true iff from-to exists in this Grafo
	 */
	public boolean hasEdge(String from, String to) {

		if (!hasVertex(from) || !hasVertex(to))
			return false;
		return listaDeAdjacencia.get(vertices.get(from)).contains(vertices.get(to));
	}

	/**
	 * Add to to from's set of neighbors, and add from to to's set of neighbors.
	 * Does not add an edge if another edge already exists
	 * 
	 * @param from the nombre of the first Nodo
	 * @param to   the nombre of the second Nodo
	 */
	public void addEdge(String from, String to) {
		Nodo v;
                Nodo w;
		if (hasEdge(from, to))
			return;
		myNumEdges += 1;
		if ((v = getVertex(from)) == null)
			v = addVertex(from);
		if ((w = getVertex(to)) == null)
			w = addVertex(to);
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
	public Iterable<Nodo> adjacentTo(String name) {
		if (!hasVertex(name))
			return EMPTY_SET;
		return listaDeAdjacencia.get(getVertex(name));
	}

	/**
	 * Return an iterator over the neighbors of Nodo v
	 * 
	 * @param v the Nodo
	 * @return an Iterator over Vertices that are adjacent to the Nodo v, empty
         set if v is not in graph
	 */
	public Iterable<Nodo> adjacentTo(Nodo v) {
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
		return vertices.values();
	}

	public int numVertices() {
		return myNumVertices;
	}

	public int numEdges() {
		return myNumEdges;
	}

	/**
	 * Sets each Vertices' centrality field to the degree of each Vertex (i.e. the
	 * number of adjacent Vertices)
	 */
	public void degreeCentrality() {
		// TODO: complete degreeCentrality
	}

	/**
	 * Sets each Vertices' centrality field to the average distancia to every Nodo
	 */
	public void closenessCentrality() {
		// TODO: complete closenessCentrality
	}

	/**
	 * Sets each Vertices' centrality field to the proportion of geodesics (shortest
 paths) that this Nodo is on
	 */
	public void betweennessCentrality() {
		// TODO: complete betweennessCentrality
	}

	/*
	 * Returns adjacency-list representation of graph
	 */
	public String toString() {
		String s = "";
		for (Nodo v : vertices.values()) {
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
			// write vertices
			for (Nodo v : vertices.values()) {
				String id = "v" + count++;
				idToName.put(v, id);
				out.write(id + "," + escapedVersion(v.nombre));
				out.write(",6," + v.distancia + "\n");
			}
			out.write("edgedef> node1,node2,color\n");
			// write edges
			for (Nodo v : vertices.values())
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
                    this.listaDeAdjacencia.get((Nodo)this.vertices.get(x));
                }
            }
            
            
            return false;
        }
        
        

}