//-----------------------------------------------------
// Author: Olivia Anastassov
// Date: 16/4/21
// Class:
// Description: data structures for Shortest path
//-----------------------------------------------------
import java.util.*;
import java.io.*;
public class ShortestPathDataStructures{
    private int nrV;
    private int nrE;

//for dijkstras
    public int [] previous;
    public int [] distance;

//Local var
    final static boolean DEBUG = false;

//------------------------------------- 
//Utilities
//-------------------------------------
    public static int toMathId(int n){
        return n+1;
    }
    public static void printDebug(String message){
        if(DEBUG){
            System.out.println(message);
        }
    }
    public static void printArrayInt(int [] array){
        for(int i = 0; i<array.length; i++){
            printDebug(""+ toMathId(i)+ ": " + array[i]);
        }
    }
    public static void printArrayBoolean(boolean [] array){
        for(int i = 0; i<array.length; i++){
            printDebug("" + toMathId(i) + ": " + array[i]);
        }
    }
//------------------------------------- 
// Constructors 
//-------------------------------------
    public ShortestPathDataStructures(int nrV){
        this.nrV = nrV;
        this.previous = new int [nrV];
        this.distance = new int [nrV];
    }
//-------------------------------------
//Serialization and Print
//-------------------------------------
    public String ShortestPathToString(){
        String output = "";
        output = output + "{";
        for(int i= 0; i<distance.length ; i++){
            output = output + distance[i];
            output = output + ", ";
        }
        output = output + "}\n";
        return output.replaceAll(", }", "}").replaceAll("2147483647", "INF");
    }
    public void printShortestPath(){
        System.out.print(ShortestPathToString());
    }
    public String ShortestPathTreeToString(){
        String output = "";
        output = output + "{\n";
         for(int i= 0; i<previous.length ; i++){
            if(previous[i] != -1){
                output = output + "{" + (previous[i]+1) + ",";
                output = output + (i + 1) + "},";
            }
        }
        output = output + "\n}";
        return output.replaceAll(",\n}", "\n}");
    }
    public void printShortestPathTree(){
        System.out.print(ShortestPathTreeToString());
    }
//-------------------------------------
// WriteToFile
//------------------------------------- 
  public void writeToMathematicaFile(String filename){
    try {
        FileWriter myWriter = new FileWriter(filename); 
        myWriter.write(ShortestPathToString());
        myWriter.close();
    } 
    catch (IOException e) { 
        System.out.println("An error occurred."); 
        e.printStackTrace();
    } 
    }
    public void writeSPTToMathematicaFile(String filename){
    try {
        FileWriter myWriter = new FileWriter(filename); 
        myWriter.write(ShortestPathTreeToString());
        myWriter.close();
    } 
    catch (IOException e) { 
        System.out.println("An error occurred."); 
        e.printStackTrace();
    } 
    }
}