/***
* MapJSGenration.java
* Given a text file with a list of coordinates.
* I will generate a JavaScript file to be used as script for
* drawing in Google Maps.
*/
package src;
import java.util.*;
import java.io.*;


public class MapJSGeneration {
  public static void main(String[] args){
    generateMapJS(args[0]);
  }

  public static void generateMapJS(String pathCoords){
    ArrayList<String[]> coordList = new ArrayList<String[]>();

    try {
      Scanner in = new Scanner(new File(pathCoords));
      PrintWriter writer = new PrintWriter("client/map.js", "UTF-8");
      in.nextLine();
      while(in.hasNextLine()){
        coordList.add(in.nextLine().split(" "));
      }

      //Now write to file.
      writer.println("function initMap() {");
      writer.println("   var map = new google.maps.Map(document.getElementById('map'), {");
      writer.println("      zoom: 3,\n      center: {lat: 41, lng: -73},\n      mapTypeId: google.maps.MapTypeId.TERRAIN");
      writer.println("   });");

      writer.println("   var roadMapCoordinates = [");
      //Dynamic coordinates:
      for (int i = 0; i < coordList.size(); i++){
        if (i != coordList.size() - 1){
          String[] split = coordList.get(i);
          writer.println("      { lat: " + split[1] + ", lng: " + split[0] + "},");
        } else {
          String[] split = coordList.get(i);
          writer.println("      { lat: " + split[1] + ", lng: " + split[0] + "}");
        }
      }

      writer.println("   ];");

      writer.println("   var roadPath = new google.maps.Polyline({");
      writer.println("      path: roadMapCoordinates,");
      writer.println("      geodisc: true,");
      writer.println("      strokeColor: '#FF0000',");
      writer.println("      strokeOpacity: 1.0,");
      writer.println("      strokeWeight: 2");
      writer.println("   });");
      writer.println("   roadPath.setMap(map);");
      writer.println("}");

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
