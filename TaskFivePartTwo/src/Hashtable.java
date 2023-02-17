import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Hashtable {

    private final int size;
    Node[] table;
    int collisions;

    public Hashtable(int size) {
        this.size = size;
        this.table = new Node[size];
    }

    public int stringToInt(String name) {
        int value = 0;
        for (int i = 0; i < name.length(); i++) {
            value += name.charAt(i) * (i+1);
        }
        return divHash(value);
    }

    public int divHash(int k) {
        return k%size;
    }

    public void add(int k, Node newNode) {
        int pos = divHash(k);
        if (table[pos] != null) {
            System.out.println("Collision. New name: "+newNode.value +", Old name: "+ table[pos].value +"\n");
            collisions++;

            newNode.next = table[pos];
            table[pos].next = null;
        }
        table[pos] = newNode; // Places node at open position in hashtable
    }

    public boolean find(String name) {
        int nameHashed = divHash(stringToInt(name));
        if (table[nameHashed] == null) return false;
        Node thisNode = table[nameHashed];

        for (int i = 0; i < table.length-nameHashed; i++) {
            if (table[nameHashed+i] == null) continue;
            while (table[nameHashed+i].next != null) {
                if (thisNode.value.equals(name)) return true;
                thisNode = thisNode.next;
            }
        }
        return false; // Doesnt exist
    }

    public double getCollisions() {
        return collisions;
    }

    public static Hashtable readNamesFromFileAndAdd(File file,Hashtable ht) throws FileNotFoundException {
        try (Scanner scan = new Scanner(file)){
            while (scan.hasNext()) {
                String line = scan.nextLine();
                Node newNode = new Node(line, 0, null);
                ht.add(ht.stringToInt(line), newNode);
            }
        }
        return ht;
    }

    public void printNodes() {
        for (Node node : table) {
            Node copyNode = node;
            if (copyNode != null) System.out.println("");
            while (copyNode != null) {
                System.out.print("Name: " + copyNode.getValue());
                copyNode = copyNode.next;
            }
        }
    }

    public static double getLoadFactor(int numberOfElements, Hashtable hashtable){
        return (double) numberOfElements / (double) hashtable.size;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Hashtable ht = new Hashtable(150);
        File f = new File("src/navn.txt");
        readNamesFromFileAndAdd(f, ht);
        //ht.printNodes(); // Unnecessary to print the list, but possible.
        System.out.println("\n");
        System.out.println("\nTotal collisions " + ht.getCollisions());
        System.out.println("LoadFactor " + getLoadFactor(114,ht));
        System.out.println("Collisions per person " + (ht.getCollisions()/114));
        System.out.println(ht.find("Stian Wilhelmsen")); //This person should be in the list
    }
}