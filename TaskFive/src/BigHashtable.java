import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class BigHashtable {
    static int A = 1327217885;
    int x;
    static int[] table;
    int collisions;

    public BigHashtable(int x, int size) {
        this.x = x;
        table = new int[size];
    }


    public int getCollisions() {
        return collisions;
    }

    public static double getLoadFactor(int amountOfNumbers){
        return (double) amountOfNumbers / (double) table.length;
    }

    public int hashFunctionOne(int k) {
        return (k * A >>> (31 - x)) % table.length; //Function from NTNU foils
    }

    public int hashFunctionTwo(int k){
        return (k % (table.length - 1)) + 1;
    }

    void add(int x) {
        int pos = hashFunctionOne(x);
        if (table[pos] == 0) {
            table[pos] = x;
            return;
        }
        int h2 = hashFunctionTwo(x);
        for (;;) {
            pos += h2;

            if(pos >= table.length){
                pos = pos % table.length;
            }
            if (table[pos] == 0) {
                table[pos] = x;
                return;
            }
            collisions++;
        }
    }



    public static void fillHashTable(BigHashtable hashTable, int amountOfNumbers){
        Random random = new Random();
        int[] arrRandom = random.ints(amountOfNumbers, 2,20000000).toArray();
        for (int value : arrRandom) {
            hashTable.add(value);
        }
    }

    public static void fillHashMap(HashMap hashMap, int amountOfNumbers){
        Random random = new Random();
        int[] arrRandom = random.ints(amountOfNumbers, 2,20000000).toArray();
        for (int value : arrRandom) {
            hashMap.put(value, value);
        }
    }


    public static String runAlgorithmAndRecordTimeWithHashMap(HashMap hashMap,int amountOfNumbers){
        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;
        do {
            fillHashMap(hashMap,amountOfNumbers);
            end = new Date();
            ++rounds;
        } while (end.getTime()-start.getTime() < 1);
        time = (double) (end.getTime()-start.getTime()) / rounds;

        return "Time used with java built in HashMap and " + amountOfNumbers + " total numbers is: " + time + " ms";
    }


    public static String runAlgorithmAndRecordTime(BigHashtable hashTable, int amountOfNumbers) {
        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;
        double loadFactor;
        do {
            fillHashTable(hashTable,amountOfNumbers);
            loadFactor = getLoadFactor(amountOfNumbers);
            end = new Date();
            ++rounds;
        } while (end.getTime()-start.getTime() < 1);
        time = (double) (end.getTime()-start.getTime()) / rounds;

        return "Time used with " + amountOfNumbers + " total numbers is: " + time + " ms" +
                "\nTotal number of collisions: " + hashTable.getCollisions() +
                "\nLoadFactor is: " + loadFactor + "\n";
    }





    public static void main(String[] args) {
        int x = 24;
        int size = 13000027;
        BigHashtable hashTable = new BigHashtable(x,size);
        HashMap<Integer, Integer> hashMap = new HashMap();

        System.out.println(runAlgorithmAndRecordTime(hashTable,10000000));
        System.out.println(runAlgorithmAndRecordTimeWithHashMap(hashMap,10000000));
    }
}
