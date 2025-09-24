import java.io.*;
import java.util.*;

public class dijkstra {
    class Node {
        public int vert;
        public int wgt;
        public Node next;

        public Node(int v, int w) {
            vert = v;
            wgt = w;
            next = null;
        }
    }

    private int V, E;
    private Node[] adj;
    private Node z;

    public dijkstra(String graphFile) throws IOException {
        int u, v, wgt;
        Node t;

        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();
        String[] parts = line.split(" +");

        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);

        z = new Node(-1, -1);
        z.next = z;

        adj = new Node[V + 1];
        for (v = 1; v <= V; ++v) adj[v] = z;

        for (int i = 0; i < E; i++) {
            line = reader.readLine();
            parts = line.split(" +");
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]);
            wgt = Integer.parseInt(parts[2]);

            t = new Node(v, wgt);
            t.next = adj[u];
            adj[u] = t;

            t = new Node(u, wgt);
            t.next = adj[v];
            adj[v] = t;
        }

        reader.close();
    }

    private char toChar(int u) {
        return (char) (u + 64);
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

        System.out.print("Enter graph file name: ");
        String fname = scanner.nextLine();

        System.out.print("Enter starting vertex: ");
        int s = scanner.nextInt();

        dijkstra g = new dijkstra(fname);
        g.Dijkstra(s);
    }
}
