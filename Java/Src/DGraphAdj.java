//-----------------------------------------------------
// Author: Olivia Anastassov     
// Date:   16/4/21     
// Description: directed DGraph represented with adjacency lists
//              templates for Shortest path
//-----------------------------------------------------
import java.util.*;
public class DGraphAdj{
    private ArrayList<Integer>[] adjLists;
    private ArrayList<DEdge> edgesList;

//local var - used to help with debugging
    final static boolean DEBUG = false;

//utilities
    public static int toMathId(int n){
        return n+ 1;
    }
    public static void printDebug(String message){
        if (DEBUG){
            System.out.println(message);
        }
    }
    public static void printArrayInt(int [] array){
        for (int i = 0; i< array.length; i++){
            printDebug("" + toMathId(i)+ ": "+ array[i]);
        }
    }

//------------------------------------- 
// Constructors 
//-------------------------------------
    public DGraphAdj(int nrVertices){
        adjLists = new ArrayList[nrVertices]; 
        edgesList = new ArrayList<DEdge>();
        for(int i = 0; i<nrVertices; i++){
            adjLists[i] = new ArrayList<Integer>();
        }
    }
    public DGraphAdj(ArrayList<Integer>[] adjLists){
        this.adjLists = adjLists;
    }

//------------------------------------- 
// Getters
//-------------------------------------
    public ArrayList<Integer>[] getAdjLists(){
        return adjLists;
    }
    public ArrayList<Integer> getAdjLists(int i){
        return adjLists[i];
    }
    public int getNrVertices(){
        return adjLists.length;
    }
    public DEdge getEdge(int u, int v){
        for (int i = 0; i<edgesList.size(); i++){
            DEdge currentEdge = edgesList.get(i);
            if(currentEdge.getVertex1() == u && currentEdge.getVertex2() == v){return currentEdge;}
        }
        return new DEdge(0,0);
    }

//------------------------------------- 
// Setters
//-------------------------------------
    public void setAdjLists(ArrayList<Integer>[] adjLists){
        this.adjLists = adjLists;
    }

//------------------------------------- 
// Serialize and print
//-------------------------------------
    public String toString(){
        int nrVertices = this.getNrVertices();
        String graphString = "";

        for(int v = 0; v<nrVertices; v++){
            graphString += toMathId(v) + ": ";
            ArrayList<Integer> adjList = getAdjLists(v);
            for(int i = 0; i < adjList.size(); i++){
                int neighbr = adjList.get(i);
                graphString += "" + toMathId(neighbr) + " ";
            }
            if (v < nrVertices - 1){
                graphString += "\n";
            }
        }
        return graphString;
    }
    // public String toMathString(){
    //     //STUB
    // }
    public void printDGraph(){
        System.out.println(this.toString());
    }

//------------------------------------- 
// Modifiers
//-------------------------------------
    public void addEdge(int u, int v, int weight){
        if (u != v ){
            adjLists[u].add(v);
            edgesList.add(new DEdge(u,v,weight));
        }
    }

//------------------------------------- 
// Converters
//-------------------------------------
    public DGraphEdges toDGraphEdges(){
        ArrayList<DEdge> dEdges = new ArrayList<DEdge>();
        for(int i = 0; i<this.getNrVertices(); i++){
            for(int r = 0; r<adjLists[i].size(); r++){
                dEdges.add(new DEdge(i,adjLists[i].get(r)));
            }
        }
        DGraphEdges toDGraph = new DGraphEdges(this.getNrVertices(),dEdges);
        return toDGraph;
    }

//---------------------------------------------------- 
// Dijkstras Algorithm 
//----------------------------------------------------
public ShortestPathDataStructures dijkstrasAlg(int s){
    ShortestPathDataStructures SPData = new ShortestPathDataStructures(this.getNrVertices());
    ArrayList<Integer> remaining = new ArrayList<Integer>();
    for(int i = 0; i<this.getNrVertices(); i++){
        SPData.distance[i] = Integer.MAX_VALUE;
        SPData.previous[i] = -1;
        remaining.add(i);//adds verts 
    }
    SPData.distance[s] = 0;
    while(remaining.size() != 0){//loop through every vertex 
        int u = minDistance(remaining, SPData); //current vertex we're on //starts at vertex with shortest path to it
        remaining.remove(Integer.valueOf(u));//removes int u not index u so we can't visit it again
        if(SPData.distance[u] == Integer.MAX_VALUE){
            continue;//break but goes right back into loop
        }
        for(int i = 0; i< adjLists[u].size(); i++){//loops through everything a vertex is connected to
            int v = adjLists[u].get(i); //temp vertex that represents a vertex u is connected to by an edge
            int alt = SPData.distance[u] + length(u,v); //distance between u and v plus the distance between u and the source // distance from source to v
            if(alt< SPData.distance[v]){//checks shortest path //if new path shorter than current shortest path then add that instead of current one
                SPData.distance[v] = alt; //set the distance so new distance is what the shortest distance is
                SPData.previous[v] = u; //storing the vertex it came from 
            }
        }
    }
    return SPData;
}
//---------------------------------------------------- 
// Helper
//----------------------------------------------------
public int minDistance(ArrayList<Integer> remaining, ShortestPathDataStructures SPData){//finding the vertex connected to soure with smallest distance //only considering verteces that haven't already been passed
    int outputIndex = remaining.get(0);
    int currentMin = Integer.MAX_VALUE;
    for (int i = 0; i<remaining.size(); i++){
        if(SPData.distance[remaining.get(i)] < currentMin){
            outputIndex = remaining.get(i);
            currentMin = SPData.distance[remaining.get(i)];
        }
    }
    return outputIndex;
}
public int length(int u, int v){//getting weight from an edge
    DEdge e = getEdge(u,v);
    return e.getWeight();
}
}