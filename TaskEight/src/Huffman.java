import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Huffman {

    public static String getStringFromFile(String file_path) {
        String str = "";
        try {
            str = new String(Files.readAllBytes(Paths.get(file_path)));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Node makeHuffManTree(String path){
        String str = getStringFromFile(path);
        int[] freq = new int[str.length()];
        Heap newHeap = new Heap(freq.length);

        newHeap.buildHeap(path);
        Node root = new Node();

        while (!newHeap.size()){
            Node left = newHeap.getMin();
            Node right = newHeap.getMin();

            int topFrequency = left.frequency + right.frequency;
            System.out.println(topFrequency);
            Node top = new Node('\0',topFrequency);
            top.left = left;
            top.right = right;

            newHeap.insertToHeap(top);
            root = top;
        }
        return root;
    }



    static void compress(String path,File file, String outputPath) throws IOException {
        int count[] = new int[256];
        DataInputStream f = new DataInputStream(new FileInputStream(file));
        int amount = f.available();
        for (int i = 0; i < amount; ++i) {
            int c = f.read();
            count[c]++;
        }
        f.close();

        Node tree;
        tree = makeHuffManTree(path);
        FileInputStream in = new FileInputStream(file);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(outputPath));
        for (int t : count) {
            out.writeInt(t);
        }
        int input;
        long writeByte = 0L;
        int i = 0;
        int j = 0;
        ArrayList<Byte> bytes = new ArrayList<>();
        for (int k = 0; k < amount; ++k) {
            input =Math.abs(in.read());
            j = 0;
            String bitString = tree.bitstring[input];
            while (j < bitString.length()) {
                if (bitString.charAt(j) == '0')writeByte = (writeByte<<1);
                else writeByte = ((writeByte<<1)|1);
                ++j;
                ++i;
                if (i == 8) {
                    bytes.add((byte)writeByte);
                    i = 0;
                    writeByte = 0L;
                }
            }
        }
        int lastByte = i;
        while (i < 8 && i != 0) {
            writeByte = (writeByte<<1);
            ++i;
        }
        bytes.add((byte)writeByte);
        out.writeInt(lastByte);
        for (Byte s : bytes) {
            out.write(s);
        }
        in.close();
        out.close();
    }

    static void decompress(String file, String outputPath) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int [] count = new int [256];
        for (int i = 0; i < count.length; i++) {
            int freq = in.readInt();
            count[i] = freq;
        }
        int lastByte = in.readInt();
        Node tree;
        tree = makeHuffManTree(file);
        File outputFile = new File(outputPath);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile));
        byte ch;
        byte [] bytes = in.readAllBytes();
        in.close();
        int length = bytes.length;
        Bitstring h = new Bitstring(0, 0);
        if(lastByte>0) length--;
        for (int i = 0; i <length; i++) {
            ch = bytes[i];
            Bitstring b = new Bitstring(8, ch);
            h = Bitstring.concat(h,b);
            h = writeChar(tree, h, out);
        }
        if(lastByte>0){
            Bitstring b = new Bitstring(lastByte, bytes[length]>>(8-lastByte));
            h = Bitstring.concat(h, b);
            writeChar(tree, h, out);
        }
        in.close();
        out.flush();
        out.close();
    }

    private static Bitstring writeChar(Node tree ,Bitstring h, DataOutputStream os) throws IOException {
        Node tempTree = tree;
        int c=0;
        for (long j = 1<< h.length-1; j!=0; j>>=1) {
            c++;
            if((h.bits & j) == 0)tempTree = tempTree.left;
            else tempTree = tempTree.right;
            if(tempTree.left == null){
                long cha = tempTree.data;
                os.write((byte)cha);
                long temp =(long) ~(0 << (h.length-c));
                h.bits = (h.bits & temp);
                h.length = h.length-c;
                c = 0;
                tempTree = tree;
            }
        }
        return h;
    }
    public static void main(String[] args) {
        try {
            File file = new File("src/test2");
            compress("src/test2",file, "src/compressedFile2");
            decompress("src/compressedFile2", "src/fileToBeDeCompressedTo");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}