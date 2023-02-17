
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

