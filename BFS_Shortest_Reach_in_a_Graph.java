import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Graph {

        private int V;
        private LinkedList<Integer>[] adj;
        public Graph(int size) {
            V = size;
            adj = new LinkedList[size];
            for (int l = 0; l < V; ++l){
                adj[l] = new LinkedList<Integer>();
            }
        }

        public void addEdge(int first, int second) {
            adj[first].add(second);
            adj[second].add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int out[] = new int[V];
            Arrays.fill(out, -1);
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();
            q.add(startId);
            out[startId] = 0;
            
            while(!q.isEmpty()){
                int x = q.poll(); 
                for (int v : adj[x]){
                    if (out[v] < 0){
                        q.add(v);
                        out[v] = out[x] + 6;
                    }
                }
            }
            return out;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
