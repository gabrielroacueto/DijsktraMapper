import src.*;

public class MapCoordinates {
  public static void main(String[] args){
    mapThis(args[0], args[1], args[2], args[3]);
  }

  public static void mapThis(String graphFile, String coordinateFile, String source, String dest){
    graphFile = "data/" + graphFile;
    coordinateFile = "data/" + coordinateFile;

    FindShortestRoadMap.findShortestRoadMap(graphFile, source, dest, "data/ShortestRoadMap.txt");
    RenderCoords.renderCoords("data/ShortestRoadMap.txt", coordinateFile);
    MapJSGeneration.generateMapJS("data/PathRender.txt");

  }
}
