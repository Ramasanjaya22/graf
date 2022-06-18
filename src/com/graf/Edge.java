package com.graf;

import java.util.HashMap;
import java.util.HashSet;

class Edge implements Comparable<Edge>{
    HashSet<Integer> vertex;
    int cost;

    public Edge(HashSet<Integer> nodset,int w){
        this.vertex = new HashSet<Integer>(nodset);
        this.cost = w;
    }
    public int compareTo(Edge e){
        if(cost == e.cost)
            return 0;
        return cost - e.cost;
    }
    public HashSet<Integer>getNodes(){
        return this.vertex;
    }
    public int getCost(){
        return this.cost;
    }
    public int getNodeA() {
        Object[] vertex = this.vertex.toArray();
        return (int) vertex[0];
    }

    public int getNodeB() {
        Object[] vertex = this.vertex.toArray();
        return (int) vertex[1];
    }

}
