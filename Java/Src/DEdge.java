//-----------------------------------------------------
// Author: Olivia Anastassov
// Date: 16/4/21
// Description: Class for: directed edge (for a digraph)
//-----------------------------------------------------
import java.util.*;
public class DEdge{
    private int u;
    private int v;
    private int weight;
//------------------------------------- 
// Constructors 
//-------------------------------------
    public DEdge(int u, int v){
        this.u = u; this.v = v; this.weight = 1; 
    }
    public DEdge(int u, int v, int weight){
        this.u = u; this.v = v; this.weight = weight;
    }
//------------------------------------- 
// Getters 
//-------------------------------------
    public int getVertex1(){
        return u;
    }
    public int getVertex2(){
        return v;
    }
    public int getWeight(){
        return weight;
    }
//------------------------------------- 
// Testers
//-------------------------------------
    public boolean equals(DEdge edge){
        if(this.u == edge.u && this.v == edge.v){
            return true;
        }
        return false;
    }
}