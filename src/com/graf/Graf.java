package com.graf;

import java.util.*;
import java.util.Collection;
//class untuk menyimpan graf dengan matriks ketetanggaan
class Graf {
    int nodes; // jumlah simpul
    String[] label;
    int[][] graf; // matriks ketentanggaan

    //Graph Constructor
    public Graf(int n, int[][] l )
    {
        this.nodes = n;
        this.graf = new int[n][n];
        this.label = new String[n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graf[i][j]=l[i][j];
    }

    public Graf(int n)
    {
        this.nodes = n;
        graf=new int[n][n];
        this.label = new String[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graf[i][j]=0;
    }



    public void printMatriks()
    {
        if(this.nodes > 0){
            for(int i=0;i<this.nodes;i++){
                for(int j=0;j<this.nodes-1;j++){
                    System.out.print(graf[i][j] + ", ");
                }
                System.out.println(graf[i][this.nodes-1]);
            }
        }
    }

    public int getNodes(){
        return this.nodes;
    }

    public void setLabel(String[] s){
        if(s.length >= this.nodes){
            for(int i=0;i<this.nodes;i++){
                this.label[i]=s[i];
            }
        }
    }

    // s = source; d = destination; c = cost;
    public void setCost(int s, int d, int c){
        if(s < this.nodes && d < this.nodes){
            graf[s][d]=c;
            graf[d][s]=c;
        }
    }


    // s = source; d = destination;
    public int getCost(int s, int d)
    {
        if (s < this.nodes && d < this.nodes){
            return this.graf[s][d];
        } else {
            return 0;
        }
    }

    public void addEdge(int from,int to,int cost){
        setCost(from,to,cost);
    }

    public void removeEdge(int from,int to){
        setCost(from,to,0);
    }

    // v = vertex or node;
    public int jmlTetangga(int v){
        int jml,n=0;
        n = getNodes();
        jml=0; //j = jumlah tetangga v
        if(v < n){
            for (int i=0; i<n;i++){
                if(this.graf[v][i] > 0) jml++;
            }
            return jml;
        }
        return 0;
    }

    //v = vertex or node;
    public int[] getTetangga(int v){
        int jml=0,j=0;
        int[] tetangga;
        if(v < this.nodes){
            jml = this.jmlTetangga(v);
            if(jml > 0){
                tetangga = new int[jml];
                for (int i=0; i<this.nodes;i++){
                    if(this.graf[v][i] != 0){ // jika edge tidak kosong
                        tetangga[j] = i; // tambahkan destination ke list tetangga
                        j++;
                    }
                }
                return tetangga;
            }
        }
        return null;
    }

    public String nodeLabel(int v) {
        String s;
        if (v < this.getNodes()) {
            s = new String(this.label[v]);
        } else s = new String("");
        return s;
    }

    Edge getMinEdge(ArrayList<Integer> processed){
        ArrayList<Edge> edgesList = new ArrayList<Edge>();
        for (int i = 0; i < processed.size(); i++) {
            if (jmlTetangga(processed.get(i)) > 0) {
                int[] tetangga = getTetangga(processed.get(i));
                for (int t : tetangga) {
                    if (!processed.contains(t)) {
                        HashSet<Integer> vertexs = new HashSet<Integer>();
                        vertexs.add(processed.get(i));
                        vertexs.add(t);
                        edgesList.add(new Edge(vertexs,getCost(processed.get(i),t)));
                    }
                }
            }
        }
        if (edgesList.size() > 0) {
            Collections.sort(edgesList);
            return edgesList.get(0);
        } else
            return null;

    }

    Graf algoPrim(int start){
        Graf t = new Graf(this.nodes);
        ArrayList<Integer>setT = new ArrayList<Integer>();
        setT.add(start);
        for (int i = 0; i < this.nodes -1; i++) {
            Edge e = getMinEdge(setT);
            System.out.println("addEdge(" + e.getNodeA() + "," + e.getNodeB() + "," + e.getCost() + ")");
            t.addEdge(e.getNodeA(),e.getNodeB(),e.getCost());
            if (setT.contains(e.getNodeA())) {
                setT.add(e.getNodeB());
            } else{
                setT.add(e.getNodeA());
            }
        }
        return t;
    }

    Graf Prim(int start) {
        Graf t = new Graf(this.nodes);
        ArrayList<Integer> diproses = new ArrayList<Integer>();
        ArrayList<Integer> asal = new ArrayList<Integer>();

        if (start >= this.nodes) // diluar graf
            return null;
        // langkah 1
        for (int i = 0; i < this.nodes; i++) // isi himpunan node yang akan diproses
            asal.add(i);

        diproses.add(start); asal.remove(start);
        do {
            primAlgoUtil(t, diproses, asal);
        } while (!asal.isEmpty());
        return t;
    }

    void primAlgoUtil(Graf t, ArrayList<Integer> sudah, ArrayList<Integer> belum) {
        int n = -1, minA = -1, minT = 0, minCost = 10000;
        for (int i = 0; i < sudah.size(); i++) {
            minT = getMinTetangga(sudah.get(i), belum);
            int cost = getCost(sudah.get(i), minT);
            if (minCost > cost) {
                minA = sudah.get(i);
                minCost = cost;
                n = minT;
            }
        }
    }


    int getMinTetangga(int n, ArrayList<Integer> dengan) {
        if (jmlTetangga(n) == 0) return -1;

        int[] tetangga = getTetangga(n);
        int minCost = 10000;
        int minT = tetangga[0];
        for (int i = 0; i < tetangga.length; i++) {
            if (dengan.contains(tetangga[i]) && minCost > getCost(n, tetangga[i])) {
                minCost = getCost(n, tetangga[i]);
                minT = tetangga[i];
            }
        }
        return minT;
    }

    boolean dfs(int v, ArrayList<Integer> visited, int parent) {
        visited.add(v);
        for (int i = 0; i < this.nodes; i++) {
            if (this.graf[v][i] > 0) {
                if (i == parent)
                    continue;
                if (visited.contains(i))
                    return true;
                if (dfs(i, visited, v))
                    return true;
            }
        }
        return false;
    }

    boolean hasCycle() {
        ArrayList<Integer> visited = new ArrayList<Integer>();
        for (int v = 0; v < this.nodes; v++) {
            if (visited.contains(v))
                continue;
            if (dfs(v, visited, -1))
                return true;
        }
        return false;
    }
    void printEdges(){
        for (int i = 0; i < this.nodes; i++) {
            for (int j = i; j < this.nodes; j++) {
                if(this.graf[i][j] > 0){
                    System.out.println(this.nodeLabel(i) + "--" + this.nodeLabel(j) + ":" + this.getCost(i,j));
                }
            }
        }
    }
    ArrayList<Edge> getAllEdgesSorted() {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < this.nodes; i++) {
            for (int j = 0; j < this.nodes; j++) {
                if (this.graf[i][j] > 0) {
                    HashSet<Integer> vertexs = new HashSet<Integer>();
                    vertexs.add(i);
                    vertexs.add(j);
                    Edge e = new Edge(vertexs, this.graf[i][j]);
                    edges.add(e);
                }
            }
        }
        Collections.sort(edges);
        return edges;
    }

    Graf Kruskal() {
            Graf t = new Graf(this.nodes);
            ArrayList<Edge> edges = getAllEdgesSorted();
            Iterator<Edge> itr = edges.iterator();
            for (int i = 0; i < this.nodes - 1; i++) {
                Edge e = itr.next();
                System.out.println("addEdge(" + e.getNodeA() + "," + e.getNodeB() + "," + e.getCost() +  ")");
                t.addEdge(e.getNodeA(), e.getNodeB(),  e.getCost());
                while (t.hasCycle() && itr.hasNext()) {
                    System.out.println("removesEdge(" +  e.getNodeA() + "," + e.getNodeB() + "," + e.getCost() + ")");
                    t.removeEdge(e.getNodeA(), e.getNodeB());
                    e = itr.next();
                    System.out.println("addEdge(" + e.getNodeA() + "," + e.getNodeB() + "," + e.getCost() + ")");
                    t.addEdge(e.getNodeA(), e.getNodeB(), e.getCost());
                }
            }
            return t;
    }
}