/*
 * Author: Aaron Maus
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Given a coordinates file, (.co format), find the node closest to the input lat and lon
public class FindClosestNode{
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 3){
            System.out.println("Usage: java FindClosestNode dataSet lat lon");
            System.out.println("    lat and lon must be given in decimal degrees");
            System.exit(1);
        }
        File f = new File(args[0]);
        double lat = Double.parseDouble(args[1]);
        double lon = Double.parseDouble(args[2]);
        Scanner fileReader = new Scanner(f);
        String line = "";
        String closestNodeID = "";
        double minDistance = Double.MAX_VALUE;
        // for each line, if that node is closer to our point of interest than
        // the known best node, update our minDistance and closestNodeID
        while(fileReader.hasNextLine()){
            // read in the line and split it
            line = fileReader.nextLine();
            String[] tokens = line.split(" ");
            // line is formatted as follows
            // v NodeID lon lat
            // lat and lon are in decimal degrees to 6 decimal places of precision,
            // listed as integers
            if(tokens[0].equals("v")){ // if the line lists a vertex
                String ID = tokens[1];
                double curLat = Double.parseDouble(tokens[3]);
                curLat = curLat * 0.000001; // need to move decimal place to proper location
                double curLon = Double.parseDouble(tokens[2]);
                curLon = curLon * 0.000001;
                // calculate the distance
                double distance = calculateHaversineDist(lat, lon, curLat, curLon);
                // update if necessary
                if(distance < minDistance){
                    closestNodeID = ID;
                    minDistance = distance;
                }
            }
        }
        System.out.println("Closest Node: " + closestNodeID);
    }

    // This is the equation for calculating the Haversine Distance between two
    // polar coordinates on a sphere. Assumes the earth is a perfect sphere,
    // but good enough for our purposes
    // @ require that the inputs be given in decimal degrees
    public static double calculateHaversineDist(double lat1, double lon1, double lat2, double lon2){
        double R = 6371; // Earth average radius in kilometers
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2-lat1);
        double deltaLon = Math.toRadians(lon2-lon1);

        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;
        return distance;
    }
}
