package src;
import java.util.*;
import java.io.*;

public class FindShortestRoadMap {
  /***
  * Main method.
  * Requires .gr filename, source and destination nodes, and output file name
  * given in arguments.
  */
  public static void main(String[] args){
    if (args.length != 4){
      System.out.println("Please provide a file name, source and destination node and an output file.");
      System.exit(0);
    }

    findShortestRoadMap(args[0], args[1], args[2], args[3]);
  }

  /***
  * Copying main method into a reusable void.
  */
  public static void findShortestRoadMap(String fileName, String source, String dest, String outputName){
    try {
      PrintWriter writer = new PrintWriter(outputName, "UTF-8");

      Dijkstra d = new Dijkstra(new Graph(fileName));
      writer.println(d.getPath(source, dest));
      writer.close();

      System.out.println("Wrote path to file " + outputName);
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
