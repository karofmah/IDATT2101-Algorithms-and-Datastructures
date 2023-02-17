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
            } else if (data.dist < n.data.dist) { //if forgj node distance is less  than current node return 1
                return -1;
            } else return 1; // forgj distance is larger than current node return -1
        }
    }

