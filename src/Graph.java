package src;
import java.util.*;
import java.io.*;

/***
* Using an adaptation of Mark Allen Weiss Graph.java class.
* Creating constructor to take in a .gr file and create a graph based on the
* arcs and nodes given.
* Also returning the path as a String which can be printed out or written to a file.
* The Dijkstra method is implemented into the Graph class.
*/
public class Graph{
  public static final double INFINITY = Double.MAX_VALUE;
  public Map vertexMap;
  private int capacity;

  /**
  *  Gabriel Roa's Graph constructor
  *  @param FileName Takes in a string point to a .gr file and adds all edges
  * to the graph.
  */
  public Graph(String fileName){
    vertexMap = new HashMap<String, Vertex>();
    try {
      Scanner in = new Scanner(new File(fileName));
      String[] tokens;
      while(in.hasNextLine()){
        tokens = in.nextLine().split(" ");
        if (tokens[0].charAt(0) == 'p'){
          capacity = Integer.parseInt(tokens[3]);
        }

        if (tokens[0].charAt(0) == 'a'){
          String source = tokens[1];
          String dest = tokens[2];
          int cost = Integer.parseInt(tokens[3]);
          this.addEdge(source, dest, cost);
        }
      }

      System.out.println("Read file " + fileName);
      System.out.println(this.vertexMap.size() + " vertices.");

    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public void addEdge(String source, String dest, double cost){
    Vertex v = getVertex(source);
    Vertex w = getVertex(dest);
    v.adj.add(new Edge(w, cost));
  }



  private Vertex getVertex (String id){
    Vertex v = (Vertex) vertexMap.get(id);
    if (v == null){
      v = new Vertex(id);
      vertexMap.put(id, v);
    }
    return v;
  }



  //Get & Set
  public int getCapacity(){
    return this.capacity;
  }
}
