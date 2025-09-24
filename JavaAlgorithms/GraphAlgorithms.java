import java.io.*;
import java.util.*;

class GraphAlgorithms {
    // Node for adjacency list
    class Node {
        public int vert;
        public int wgt;
        public Node next;

        public Node(int v, int w) {
            vert = v;
            wgt = w;
            next = null;
        }

        public Node(int v, int w, Node n) {
            vert = v;
            wgt = w;
            next = n;
        }
    }

    private int V, E; // Number of vertices and edges
    private Node[] adj; // Adjacency list array
    private Node z; // Sentinel node for empty lists
    private int[] visited; // Array to track visited vertices

    // Constructor that initializes graph from a file
    public GraphAlgorithms(String graphFile) throws IOException {
        int u, v, wgt;
        Node t;

        // Initialize file reader
        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();

        if (line == null || line.trim().isEmpty()) {
            throw new IOException("File format is incorrect, first line is empty or missing.");
        }

        String[] parts = line.split(" +");

        // Read number of vertices and edges
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);

        // Create sentinel node
        z = new Node(-1, -1);
        z.next = z;

        // Initialize adjacency list array
        adj = new Node[V + 1]; // 1-based indexing
        visited = new int[V + 1];

        // Initialize each adjacency list
        for (v = 1; v <= V; ++v) {
            adj[v] = z;
        }

        // Read edges and populate adjacency lists
        System.out.println("Reading edges from text file");

        for (int i = 0; i < E; i++) {
            line = reader.readLine();
            parts = line.split(" +");
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]);
            wgt = Integer.parseInt(parts[2]);

            // Edge u → v
            adj[u] = new Node(v, wgt, adj[u]);
            // Edge v → u
            adj[v] = new Node(u, wgt, adj[v]);
        }

        reader.close();
    }

    private char toChar(int u) {
        return (char) (u + 64);
    }

    public void display() {
        for (int v = 1; v <= V; ++v) {
            System.out.print("\nadj[" + toChar(v) + "] ->");
            Node n = adj[v];
            while (n != z) {
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");
                n = n.next;
            }
        }
        System.out.println("");
    }

    public void DF(int s) {
        for (int i = 1; i <= V; ++i)
            visited[i] = 0;
        System.out.println("\nStarting DFS from vertex " + toChar(s));
        dfVisit(s, -1);
    }

    private void dfVisit(int v, int parent) {
        visited[v] = 1;
        if (parent == -1)
            System.out.println("Visited " + toChar(v) + " from root");
        else
            System.out.println("Visited " + toChar(v) + " from " + toChar(parent));

        Node n = adj[v];
        while (n != z) {
            if (visited[n.vert] == 0)
                dfVisit(n.vert, v);
            n = n.next;
        }
    }

    public void BFS(int s) {
        for (int i = 1; i <= V; ++i)
            visited[i] = 0;

        Queue<Integer> queue = new LinkedList<>();
        visited[s] = 1;
        queue.offer(s);

        System.out.println("\nStarting BFS from vertex " + toChar(s));

        while (!queue.isEmpty()) {
            int current = queue.poll();
            Node n = adj[current];

            while (n != z) {
                if (visited[n.vert] == 0) {
                    visited[n.vert] = 1;
                    queue.offer(n.vert);
                    System.out.println("Visited " + toChar(n.vert) + " from " + toChar(current));
                }
                n = n.next;
            }
        }
    }

    @SuppressWarnings("unchecked")


    public void Prim(int s) {
        System.out.println("\nPrim's Minimum Spanning Tree:");

        int[] dist = new int[V + 1];
        int[] parent = new int[V + 1];
        boolean[] inTree = new boolean[V + 1];

        for (int i = 1; i <= V; ++i) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            inTree[i] = false;
        }
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int uVert = u[0];

            if (inTree[uVert]) continue;
            inTree[uVert] = true;

            Node n = adj[uVert];
            while (n != z) {
                if (!inTree[n.vert] && n.wgt < dist[n.vert]) {
                    dist[n.vert] = n.wgt;
                    parent[n.vert] = uVert;
                    pq.offer(new int[]{n.vert, dist[n.vert]});
                }
                n = n.next;
            }
        }

        // Build adjacency list for MST
        List<Node>[] mstAdj = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            mstAdj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= V; i++) {
            if (parent[i] != -1) {
                mstAdj[i].add(new Node(parent[i], dist[i]));
                mstAdj[parent[i]].add(new Node(i, dist[i]));
            }
        }

        boolean[] visitedMST = new boolean[V + 1];
        System.out.println("Edges in MST (structured traversal):");
        dfsMSTPrint(s, -1, mstAdj, visitedMST);
    }

    private void dfsMSTPrint(int u, int parent, List<Node>[] mstAdj, boolean[] visitedMST) {
        visitedMST[u] = true;

        for (Node n : mstAdj[u]) {
            if (!visitedMST[n.vert]) {
                System.out.println(toChar(u) + " - " + toChar(n.vert) + " (" + n.wgt + ")");
                dfsMSTPrint(n.vert, u, mstAdj, visitedMST);
            }
        }
    }

    public void Dijkstra(int s) {
        System.out.println("\nDijkstra's Shortest Paths:");

        int[] dist = new int[V + 1];
        int[] parent = new int[V + 1];
        boolean[] visited = new boolean[V + 1];

        for (int i = 1; i <= V; ++i) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            visited[i] = false;
        }
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int uVert = u[0];

            if (visited[uVert]) continue;
            visited[uVert] = true;

            Node n = adj[uVert];
            while (n != z) {
                if (!visited[n.vert] && dist[uVert] + n.wgt < dist[n.vert]) {
                    dist[n.vert] = dist[uVert] + n.wgt;
                    parent[n.vert] = uVert;
                    pq.offer(new int[]{n.vert, dist[n.vert]});
                }
                n = n.next;
            }
        }

        System.out.println("Shortest paths from " + toChar(s) + ":");
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(toChar(s) + " to " + toChar(i) + " = no path");
            } else {
                System.out.println(toChar(s) + " to " + toChar(i) + " = " + dist[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the graph file: ");
        String fname = scanner.nextLine();

        System.out.print("Enter the starting vertex: ");
        int s = scanner.nextInt();

        GraphAlgorithms g = new GraphAlgorithms(fname);
        g.display();

        g.DF(s);
        g.BFS(s);
        g.Prim(s);
        g.Dijkstra(s);
    }
}
