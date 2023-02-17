class Edge {
    Edge next;
    Node to;

    public Edge(Node to, Edge next) {
        this.next = next;
        this.to = to;
    }
}