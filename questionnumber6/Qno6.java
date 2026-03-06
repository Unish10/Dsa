package questionnumber6;

import java.util.*;

public class Qno6 {

    static class Node {
        int id;
        double dist;
        Node(int id, double dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    static class Edge {
        int to;
        double weight;

        Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        int n = 4;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        addEdge(graph, 0, 1, 0.9);
        addEdge(graph, 0, 2, 0.8);
        addEdge(graph, 1, 3, 0.7);
        addEdge(graph, 2, 3, 0.6);

        double safestPath = dijkstra(graph, 0, 3);
        System.out.println("Safest Path Probability: " + safestPath);
    }

    static void addEdge(List<List<Edge>> graph, int u, int v, double prob) {
        graph.get(u).add(new Edge(v, -Math.log(prob)));
    }

    static double dijkstra(List<List<Edge>> graph, int src, int dest) {

        int n = graph.size();
        double[] dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.dist));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {

            Node curr = pq.poll();
            int node = curr.id;

            if (curr.dist > dist[node]) continue;

            for (Edge edge : graph.get(node)) {

                if (dist[node] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[node] + edge.weight;
                    pq.add(new Node(edge.to, dist[edge.to]));
                }
            }
        }

        return Math.exp(-dist[dest]);
    }
}
