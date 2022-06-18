package com.graf;

public class UsingGraf {

    public static void main(String[] args) {
        //
        Graf g, t;
        int[][] mt = {
                //A  B  C  D  E  F  G  H  I  J  K  L
                { 0, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}, //A
                { 2, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //B
                { 0, 3, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0}, //C
                { 0, 0, 1, 0, 0, 0, 0, 5, 0, 0, 0, 0}, //D
                { 3, 0, 0, 0, 0, 4, 0, 0, 4, 0, 0, 0}, //E
                { 0, 1, 0, 0, 4, 0, 3, 0, 0, 2, 0, 0}, //F
                { 0, 0, 2, 0, 0, 3, 0, 3, 0, 0, 4, 0}, //G
                { 0, 0, 0, 5, 0, 0, 3, 0, 0, 0, 0, 3}, //H
                { 0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0}, //I
                { 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3, 0}, //J
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //K
                { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 0}, //L

        };
        String[] labels = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        g = new Graf(12, mt);
        g.setLabel(labels);
        g.printMatriks();
        g.printEdges();
        System.out.println("Jumlah tetangga node A adalah " + g.jmlTetangga(0));
        for (int num : g.getTetangga(0)) {
            System.out.print(" " + g.nodeLabel(num));
        }

         System.out.println();
         System.out.println("Jumlah tetangga node C adalah " + g.jmlTetangga(2));
         for (int num : g.getTetangga(2)) {
           System.out.print(" " + g.nodeLabel(num));
         }

        System.out.println();
        System.out.println("g has cycle: " + g.hasCycle());

        t = g.Prim(1);
        t.setLabel(labels);
        t.printMatriks();
        t.printEdges();
        System.out.println(g.Prim(1));
        t = g.algoPrim(1);
        t = g.Kruskal();
        t.setLabel(labels);
        t.printMatriks();
        t.printEdges();

    }

}
