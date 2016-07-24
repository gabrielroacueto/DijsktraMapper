/***
* RenderCoords.java
* Given a text file and a .co file it transforms the text file instead of
* directions into a list of coordinates.
*/
package src;
import java.util.*;
import java.io.*;

public class RenderCoords {
  private static Map<String, String[]> nodeMap = new HashMap<String, String[]>();

  public static void renderCoords(String fileName, String coordFile){
    createMap(coordFile);

    try {
      Scanner in = new Scanner(new File(fileName));
      String outputFile = "data/PathRender.txt";

      PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
      writer.println("Coordinates for path " + fileName);

      in.nextLine(); //dump

      String line = in.nextLine();
      String[] inTokens = line.split(" ");

      writer.println(getCoordinates(inTokens[0]));

      line = in.nextLine();

      while (in.hasNextLine() && !line.isEmpty()){
        inTokens = line.split(" ");

        writer.println(getCoordinates(inTokens[1]));
        line = in.nextLine();
      }

      writer.close();
      System.out.println("Wrote coordinates to file " + outputFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void createMap(String file){
    try {
      Scanner in = new Scanner(new File(file));

      String[] inTokens;
      while (in.hasNextLine()){
        inTokens = in.nextLine().split(" ");

        if (inTokens[0].charAt(0) == 'v'){
          String[] coords = {inTokens[2], inTokens[3]};
          nodeMap.put(inTokens[1], coords);
        }
      }
    } catch (Exception e){
      e.printStackTrace();
    }

  }

  private static String getCoordinates(String nodeId){
    String[] coords = nodeMap.get(nodeId);
    if (coords == null){
      return "";
    }
    String x, y;

    if (coords[0].charAt(0) == '-'){
      x = coords[0].substring(0, 3) + "." + coords[0].substring(3, coords[0].length());
    } else {
      x = coords[0].substring(0, 2) + "." + coords[0].substring(2, coords[0].length());
    }

    if (coords[1].charAt(0) == '-'){
        y = coords[1].substring(0, 3) + "." + coords[1].substring(3, coords[1].length());
    } else {
        y = coords[1].substring(0, 2) + "." + coords[1].substring(2, coords[1].length());
    }

    return x + " " + y;
  }
}
