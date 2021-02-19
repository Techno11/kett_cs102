package zais5275.kettering.edu.cs102.Final.ll.Quiz2;

// Java program to print DFS traversal from a given given graph
import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class Graph
{
    private int V; // No. of vertices
    private String[] let = {"A", "B", "C", "D", "E", "F", "G", "", "", "", ""};

    // Array of lists for Adjacency List Representation
    private LinkedList<Integer> adj[];

    // Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to v's list.
    }

    // A function used by DFS
    void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        //System.out.print(v+" ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            System.out.println("At " + let[v] + " Visiting " + let[n]);
            try{
                if (!visited[n]) {
                    DFSUtil(n, visited);
                }
            } catch (ArrayIndexOutOfBoundsException e){}

        }
    }

    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS(int v)
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

    public static void main(String args[])
    {
        Graph g = new Graph(8);

        g.addEdge(1, 9);
        g.addEdge(0, 9);
        g.addEdge(3, 4);
        g.addEdge(0, 7);
        g.addEdge(0, 5);
        g.addEdge(0, 8);
        g.addEdge(5, 6);
        g.addEdge(0, 1);

        g.addEdge(5, 8);
        g.addEdge(6, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 2);
        g.addEdge(6, 6);
        g.addEdge(2, 3);

        g.addEdge(3, 7);
        g.addEdge(4, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);

        g.addEdge(4, 5);

        g.addEdge(7, 1);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 2)");

        g.DFS(0);
    }
}

