package src;
import java.util.*;

public class Path implements Comparable<Path> {
  public Vertex dest;
  public double cost;

  public Path (Vertex d, double c){
    dest = d;
    cost = c;
  }

  public int compareTo (Path o){
    double otherCost = o.cost;
    return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
  }
}
