import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;

class Graph {
    private int N;
    private int K;
    private LinkedList<Integer> nodes[];

    Graph(){
    }
    Graph(int s) {
        N = s;
        nodes = new LinkedList[s];
        for (int i = 0; i < s; ++i){
            nodes[i] = new LinkedList();
        }
    }
    public void addEdge(int s, int d) {
        nodes[s].add(d);
    }
    public void dfs(int s, boolean visitedCorners[]) {
        visitedCorners[s] = true;
        System.out.print(s + " ");
        int n;

        Iterator<Integer> i = nodes[s].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visitedCorners[n]){
                dfs(n, visitedCorners);
            }
        }
    }
    public Graph transposeGraph() {
        Graph graph = new Graph(N);
        for (int s = 0; s < N; s++) {
            Iterator<Integer> i = nodes[s].listIterator();
            while (i.hasNext()) {
                graph.nodes[i.next()].add(s);
            }
        }
        return graph;
    }
    public void fill(int s, boolean visitedCorners[], ArrayList list) {
        visitedCorners[s] = true;
        Iterator<Integer> i = nodes[s].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visitedCorners[n]){
                fill(n, visitedCorners, list);
            }
        }
        list.add(s);
    }

    public void print() {
        ArrayList list = new ArrayList();

        boolean visitedVertices[] = new boolean[N];
        for (int i = 0; i < N; i++){
            visitedVertices[i] = false;
        }
        for (int i = 0; i < N; i++){
            if (visitedVertices[i] == false){
                fill(i, visitedVertices, list);
            }
        }

        Graph gr = transposeGraph();

        for (int i = 0; i < N; i++){
            visitedVertices[i] = false;
        }
        while (list.isEmpty() == false) {
            int s = (int) list.remove(list.size() - 1);

            if (visitedVertices[s] == false) {
                gr.dfs(s, visitedVertices);
                System.out.println();
            }
        }
    }

    public void newGraph(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new LinkedList[N];
        for (int i = 0; i < N; i++){
            nodes[i] = new LinkedList<>();
        }
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            addEdge(from, to);
        }
    }


    public static void main(String args[]) {
        Graph graphOne = new Graph();
        Graph graphTwo = new Graph();
        Graph graphThree = new Graph();
        Graph graphFour = new Graph();
        try {
            BufferedReader brOne = new BufferedReader(new FileReader("src/o6g1"));
            BufferedReader brTwo = new BufferedReader(new FileReader("src/o6g2"));
            BufferedReader brThree = new BufferedReader(new FileReader("src/o6g5"));
            BufferedReader brFour = new BufferedReader(new FileReader("src/o6g6"));
            graphOne.newGraph(brOne);
            graphTwo.newGraph(brTwo);
            graphThree.newGraph(brThree);
            graphFour.newGraph(brFour);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Strongly Connected Components in: o6g1");
        graphOne.print();
        System.out.println("Strongly Connected Components in: o6g2");
        graphTwo.print();
        System.out.println("Strongly Connected Components in: o6g5");
        graphThree.print();
        System.out.println("Strongly Connected Components in: o6g6");
        graphFour.print();
    }
}