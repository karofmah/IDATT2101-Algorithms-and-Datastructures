import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    int N;
    int K;
    int I;
    Node[] nodes;
    int counted;
    public int inf = 1000000000;
    public void shorten(Node n, Wedge w, PriorityQueue<Node> pq) {
        Forgj nd = n.data, md = w.to.data;
        if (md.dist > nd.dist + w.weight) {
            md.dist = nd.dist + w.weight;
            md.pre = n;
            pq.remove(w.to);
            pq.add(w.to);
        }
    }

    public void initPrev(Node s) {
        for (int i = N; i -->0;) {
            nodes[i].data = new Forgj();
        }
        (s.data).dist = 0;
    }

    public void newGraph(BufferedReader er, BufferedReader nr, BufferedReader ir) throws IOException {
        StringTokenizer erst = new StringTokenizer(er.readLine());
        StringTokenizer nrst  = new StringTokenizer(nr.readLine());
        StringTokenizer irst = new StringTokenizer(ir.readLine());
        N = Integer.parseInt(nrst.nextToken());
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(); //new nodes based on the number of nodes
            nrst = new StringTokenizer(nr.readLine());
            Double.parseDouble(nrst.nextToken()); //skips next token
            nodes[i].cords = new Coordinates(Double.parseDouble(nrst.nextToken()), Double.parseDouble(nrst.nextToken()));
        }
        I = Integer.parseInt(irst.nextToken());
        for (int i = 0; i < I; i++){
            irst = new StringTokenizer(ir.readLine());
            int index = Integer.parseInt(irst.nextToken());
            int type = Integer.parseInt(irst.nextToken()); //petrol station?
            String name = "";
            while (irst.hasMoreTokens()) {
                name += irst.nextToken() + " ";
            }
            nodes[index].name = name;
            nodes[index].type = type;
        }
        K = Integer.parseInt(erst.nextToken());
        for (int i = 0; i < K; i++) {
            erst = new StringTokenizer(er.readLine());
            int from = Integer.parseInt(erst.nextToken());
            int to = Integer.parseInt(erst.nextToken());
            int weight = Integer.parseInt(erst.nextToken());
            //new weighted edge with node reference, next edge, and weight
            Wedge w = new Wedge(nodes[to], (Wedge) nodes[from].edge, weight);
            //sets the edge og the node to the new edge, and the nr to from-value
            nodes[from].edge = w;
            nodes[from].number = from;
        }
    }


    public void print(int s, int i) {
        Node source = nodes[s];
        System.out.println("Node " + "     Forgj  " + "      Tid" + "      Landmark?  " + "    Latitude " + "Longitude");
        Node destination = nodes[i];
        if (destination == source) {
            System.out.println(i + "     source       " + (destination.data).dist);
        } else {
            if ((destination.data).dist == inf) {
                System.out.println(i + "               not reached");
            } else {
                while (destination != source) {
                    System.out.println(destination.number + "     " + (destination.data).pre.number + "        " + (destination.data).dist + "   " + destination.name + "          " + destination.cords.latitude + " " + destination.cords.longitude);
                    destination = destination.data.pre;
                }
                System.out.println(destination.number + "    N/A          " + 0 + "      " + destination.name);
                System.out.print(destination.name + " to " + nodes[i].name + " takes approx " + hundredsToTime(nodes[i].data.dist) + "\n");
            }
        }
        fillCordinates(source, i);
    }

    private String hundredsToTime(int hundreds){
        int totalSecs = hundreds/100;
        int seconds = totalSecs%60;
        int totalMinutes = totalSecs / 60;
        int minutes = totalMinutes % 60;
        int hours = totalMinutes / 60;

        return (hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
    }

    public void fillCordinates(Node s, int i){
        ArrayList coordinates = new ArrayList();
        Node destination = nodes[i];
        while(destination != s) {
            coordinates.add(destination.cords);
            destination = destination.data.pre;
        }
    }

    public PriorityQueue<Node> create_priority(Node source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(source);
        return priorityQueue;
    }

    public void dijkstra(int s, int f) {
        int count = 0;
        Node source = nodes[s]; //finds the node in pos of source
        initPrev(source); //initialize based on our source node
        PriorityQueue<Node> pq = create_priority(source); //creates a prioriy queue with java.util priority queue
        Node n = null;
        while (n != nodes[f]){
            count++;
            n = pq.poll();
            for (Wedge w = (Wedge) n.edge; w != null; w = (Wedge) w.next) { //as long as there is an edge
                shorten(n, w, pq); //shorten the queue
            }
        }
        counted = count;
    }

    public static void main(String[] args) {
        Dijkstra weightedGraph = new Dijkstra();
        int k = 6013683;
        int g = 6225195;
        int s = k;
        int f = g;
        try {
            BufferedReader edge = new BufferedReader(new FileReader("src/kanter.txt"));
            BufferedReader node = new BufferedReader(new FileReader("src/noder.txt"));
            BufferedReader interestreader = new BufferedReader(new FileReader("src/interessepkt.txt"));
            weightedGraph.newGraph(edge,node,interestreader);
            weightedGraph.dijkstra(s,f);
            weightedGraph.print(s,f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

