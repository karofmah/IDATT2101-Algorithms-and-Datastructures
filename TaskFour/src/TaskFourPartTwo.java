import java.util.Objects;
import java.util.Scanner;

public class TaskFourPartTwo {

    Node root; // root of the tree
    String[] temp = new String[]{"", "", "", ""}; //list to hold string values we're going to print

    TaskFourPartTwo() {
        root = null;
    }

    class Node {
        String word;
        Node left = null;
        Node right = null;

        Node(String word) {
            this.word = word;
        }
    }

    public void newNode(String word) {
        // Checks if root exists, if not it creates one.
        if (root == null) root = new Node(word);
        else {
            //Sets parent as current root
            Node parent = root;
            while (true) {
                //Compares sizes of words to the root to decide if the word is going to the left or right
                int i = word.compareToIgnoreCase(parent.word);
                if (i < 0) {
                    // If left spot is open, word will go there
                    if (parent.left == null) {
                        parent.left = new Node(word);
                        return;
                    }
                    //update the parent to be the left spot, and we can do the loop again
                    parent = parent.left;
                } else if (i > 0) {
                    //If right spot is open word will go there
                    if (parent.right == null) {
                        parent.right = new Node(word);
                        return;
                    }
                    parent = parent.right;
                }
            }
        }
    }

    private void formatRows(Node node, int row) {
        int pixelSpace = 64;
        int rowDistance = (int) (pixelSpace / Math.pow(2, row));

        if (node != null) {
            temp[row] += " ".repeat((rowDistance - node.word.length()) / 2) + node.word + " ".repeat((rowDistance - node.word.length()) / 2);
        } else temp[row] += " ".repeat(rowDistance);

        if (row < temp.length - 1) {
            if (node != null) {
                formatRows(node.left, row + 1);
                formatRows(node.right, row + 1);
            } else {
                formatRows(null, row + 1);
                formatRows(null, row + 1);
            }
        }
    }

    public void printTree() {
        formatRows(root, 0);
        for (String row : temp) {
            System.out.println(row);
        }
    }


    public static void main(String[] args) {
        TaskFourPartTwo tree = new TaskFourPartTwo();

        Scanner myObj = new Scanner(System.in);
        boolean go = true;
        while (go) {
            System.out.println("Write a word and hit enter. \nFinished with your sentence? Write 'done' and hit enter.");
            String wordInput = myObj.nextLine();
            if (Objects.equals(wordInput, "done")) {
                go = false;
            } else {
                tree.newNode(wordInput);
            }
        }
        tree.printTree();
    }
}
