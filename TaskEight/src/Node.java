import java.util.Comparator;



public class Node implements Comparator<Node> {

    int frequency;
    char character;

    Node left;
    Node right;

    public Node() {
    }

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compare(Node o1, Node o2) {
        return o1.frequency - o2.frequency;
    }
}