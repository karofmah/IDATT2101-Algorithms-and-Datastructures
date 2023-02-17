import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeightedGraph {
    int N, K; //Number of nodes and edge
    Node[] nodes;

    /**
     * Datastructure to implement weighted edges
     */
    class Wedge extends Edge {
        int weight;
        public Wedge(Node n, Wedge next, int wgt) {
            super(n, next);
            weight = wgt;
        }
    }

    class Node implements Comparable<Node>{ // Comparable for right priority in queue
        Edge edge;
        int nr;
        Forgj data;
        boolean finished;
        public Node() {
        }

        public int compareTo(Node n) {
            if (data.dist == n.data.dist) { //if forgj node distance is equal to current node return 0
                return 0;
            } else if (data.dist < n.data.dist) { //if forgj node distance is less  than current node return -1
                return -1;
            } else return 1; // forgj distance is larger than current node return 1
        }
    }

    class Forgj {
        int dist;
        Node pre;
        static int infinite = 1000000000;

        public Forgj() {
            dist = infinite;
        }
    }

    public void initforgj(Node s) {
        for (int i = N; i -->0;) {
            nodes[i].data = new Forgj();
        }
        ((Forgj)s.data).dist = 0;
    }

    class Edge {
        Edge next;
        Node to;

        public Edge(Node to, Edge next) {
            this.next = next;
            this.to = to;
        }
    }

    public void shorten(Node n, Wedge w, PriorityQueue<Node> pq) {
        Forgj nd = n.data, md = w.to.data;
        if (md.dist > nd.dist + w.weight) {
            md.dist = nd.dist + w.weight;
            md.pre = n;
            pq.remove(w.to);
            pq.add(w.to);
        }
    }

    public void newGraph(BufferedReader br)throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        for(int i = 0; i < N; ++i) nodes[i] = new Node();
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; ++i){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Wedge w = new Wedge(nodes[to], (Wedge) nodes[from].edge , weight);
            nodes[from].edge = w;
            nodes[from].nr = from;
        }
    }

    public PriorityQueue<Node> create_priority(Node[] pr) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(List.of(pr));
        return priorityQueue;
    }

    public void dijkstra(int s) {
        Node source = nodes[s]; //finds the node in pos of source
        initforgj(source); //initialize based on our source node
        Node[] pri = nodes;
        PriorityQueue pq = create_priority(pri); //creates a prioriy queue
        for (int i = N; i > 1; i--) { //iterates downwards through all nodes except source
            Node n = (Node) pq.poll();
            for (Wedge w = (Wedge) n.edge; w != null; w = (Wedge) w.next) { //as long as there is an edge
                shorten(n, w, pq); //shorten the queue
            }
        }
        print(source);
    }

    public void print(Node s) {
        System.out.println("Node " + " Forgj  " + "   Dis");
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == s) {
                System.out.println(i + "     source       " + (nodes[i].data).dist);
            } else {
                if (((Forgj) nodes[i].data).dist == Forgj.infinite) {
                    System.out.println(i + "               not reached");
                } else {
                    System.out.println(i + "     " + (nodes[i].data).pre.nr + "        " + (nodes[i].data).dist);
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();
        String file_name = "src/vg5.txt";
        int s = 0;
        try {
            BufferedReader br = new BufferedReader(new  FileReader(file_name));
            weightedGraph.newGraph(br);
            System.out.println(file_name + " with start node: " + s);
            weightedGraph.dijkstra(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}