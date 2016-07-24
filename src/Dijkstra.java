package src;
import java.util.*;
public class Dijkstra {
  private Graph g;
  private String pathString;
  public static final double INFINITY = Double.MAX_VALUE;

  public Dijkstra(Graph g){
    this.g = g;
    pathString = "";
  }

  /***
  * Method performs Dijkstra's algorithm on the given graph for a given source
  * node.
  */
  private void dijkstra(String startName){
    BinaryHeap<Path> pq = new BinaryHeap<Path>(this.g.getCapacity());

    Vertex start = (Vertex) g.vertexMap.get(startName);
    if (start == null)
      throw new NoSuchElementException("Start vertex not found.");

    clearAll();
    pq.insert(new Path(start, 0));
    start.dist = 0;

    int nodesSeen = 0;
    while(!pq.isEmpty() && nodesSeen < g.vertexMap.size()){
      Path vrec = (Path) pq.deleteMin();
      Vertex v = vrec.dest;
      if (v.scratch != 0)
        continue;

      v.scratch = 1;
      nodesSeen++;

      for (Iterator itr = v.adj.iterator(); itr.hasNext(); ){
        Edge e = (Edge) itr.next();
        Vertex w = e.dest;
        double cvw = e.cost;

        if (w.dist > v.dist + cvw){
          w.dist = v.dist + cvw;
          w.prev = v;
          pq.insert(new Path(w, w.dist));
        }
      }
    }
  }

  /***
  * Method returns a string with the path from one node to the other.
  */
  public String getPath(String source, String dest){
    this.dijkstra(source);
    printPath(dest);
    return getPathString();
  }

  /***
  * Recursive method to get the get the path given a destination.
  */
  private void printPath(String destName){
    Vertex w = (Vertex)g.vertexMap.get(destName);
    if (w == null)
      throw new NoSuchElementException("Destination vertex not found");
    else if (w.dist == INFINITY)
      pathString += (destName + " is unreachable.");
    else {
      pathString += ("(Cost is : " + w.dist + ")\n");
      printPath(w);
      pathString += "\n";
    }
  }

  /***
  * Overloading previous method to get path given a destination Vertex.
  */
  private void printPath(Vertex dest){
    if (dest.prev != null){
      printPath(dest.prev);
      pathString += ("to ");
    }
    pathString += (dest.id + "\n");
  }

  private void clearAll(){
    for (Iterator itr = g.vertexMap.values().iterator(); itr.hasNext();)
      ((Vertex)itr.next()).reset();
  }

  public String getPathString(){
    return this.pathString;
  }
  public void clearPathString(){
    this.pathString = "";
  }
}
