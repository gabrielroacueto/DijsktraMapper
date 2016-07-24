package src;
import java.util.*;

public class Vertex {
  public String id;
  public LinkedList<Edge> adj;
  public double dist;
  public Vertex prev;
  public int scratch;

  public Vertex (String id){
    this.id = id;
    adj = new LinkedList<Edge>();
    reset();
  }

  public void reset(){
    dist = Graph.INFINITY;
    prev = null;
    scratch = 0;
  }
}
