import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class TaskThree {

    public static void bytt(int[]t, int i, int j) {
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

    public static void bookQuicksort(int[] t, int v, int h) {
        if (h - v > 2) {
            int delepos = splitt(t, v, h);
            bookQuicksort(t, v, delepos - 1);
            bookQuicksort(t, delepos + 1, h);
        } else median3sort(t, v, h);
    }

    private static int median3sort(int[] t, int v, int h) {
        int m = (v + h) / 2;
        if (t[v] > t[m]) bytt(t, v, m);
        if (t[m] > t[h]) {
            bytt(t, m, h);
            if (t[v] > t[m]) bytt(t, v, m);
        }
        return m;
    }

    private static int splitt(int[] t, int v, int h) {
        int iv, ih;
        int m = median3sort(t, v, h);
        int dv = t[m];
        bytt(t, m, h - 1);
        for (iv = v, ih = h - 1;;) {
            while (t[++iv] < dv);
            while (t[--ih] > dv);
            if (iv >= ih) break;
            bytt(t, iv, ih);
        }
        bytt(t, iv, h-1);
        return iv;
    }


    private static int[] partition(int[] arr, int low, int high) {
        bytt(arr, low, low + (high - low) / 3);
        bytt(arr, high, high - (high - low) / 3);
        if (arr[low] > arr[high]) bytt(arr, low, high);

        int j = low + 1;
        int g = high - 1;
        int k = low + 1;
        int p = arr[low];
        int q = arr[high];

        while (k <= g) {
            if (arr[k] < p) {
                bytt(arr, k, j);
                j++;
            }
            else if (arr[k] >= q) {
                while (arr[g] > q && k < g)
                    g--;

                bytt(arr, k, g);
                g--;
                if (arr[k] < p) {
                    bytt(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;
        bytt(arr, low, j);
        bytt(arr, high, g);
        return new int[] {j, g};
    }


    public static void dualPivotQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] piv = partition(arr, low, high);

            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
            dualPivotQuickSort(arr, piv[1] + 1, high);
        }
    }

    public static int[] fillListWithRandomNumbers(int amountOfNumbers, int min, int max) {
        Random random = new Random();
        int[] list = new int [amountOfNumbers];
        for (int i = 0; i < amountOfNumbers; i++) {
            list[i] = random.nextInt(min, max);
        }
        return list;
    }

    public static int[] sortedList(int amountOfNumbers) {
        int[] list = new int[amountOfNumbers];
        for (int i = 0; i < amountOfNumbers; i++) {
            list[i] = i;
        }
        return list;
    }

    public static int[] duplicatedNumbersList(int amountOfNumbers,int min ,int max) {
        int[] test = new int[amountOfNumbers*2];
        int[] list = fillListWithRandomNumbers(amountOfNumbers,min,max);

        for (int i = 0; i < amountOfNumbers*2; i++) {
            if (i < amountOfNumbers) {
                test[i] = list[i];
            } else {
                test[i] = list[i - amountOfNumbers];
            }
        }
        return test;
    }

    public static int sum(int[] table){
        return Arrays.stream(table).sum();
    }



    public static String runAlgorithmAndRecordTime(int algorithm,int amountOfNumbers,int min, int max) {
        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;
        int sumBefore;
        int sumAfter;
        boolean itIsSorted;
        do {
            if(algorithm == 1){
                int[] random = fillListWithRandomNumbers(amountOfNumbers,min,max);
                sumBefore = sum(random);
                bookQuicksort(random, 0, random.length-1);
                sumAfter = sum(random);
                itIsSorted = isSorted(random);
            }else if(algorithm == 2){
                int[] sorted = sortedList(amountOfNumbers);
                sumBefore = sum(sorted);
                bookQuicksort(sorted, 0, sorted.length-1);
                sumAfter = sum(sorted);
                itIsSorted = isSorted(sorted);
            }else if(algorithm == 3){
                int[] duplicate = duplicatedNumbersList(amountOfNumbers,min,max);
                sumBefore = sum(duplicate);
                bookQuicksort(duplicate, 0, duplicate.length-1);
                sumAfter = sum(duplicate);
                itIsSorted = isSorted(duplicate);
            }else if(algorithm == 4){
                int[] random = fillListWithRandomNumbers(amountOfNumbers,min,max);
                sumBefore = sum(random);
                dualPivotQuickSort(random, 0, random.length-1);
                sumAfter = sum(random);
                itIsSorted = isSorted(random);
            }else if(algorithm == 5){
                int[] sorted = sortedList(amountOfNumbers);
                sumBefore = sum(sorted);
                dualPivotQuickSort(sorted, 0, sorted.length-1);
                sumAfter = sum(sorted);
                itIsSorted = isSorted(sorted);
            }else{
                int[] duplicated = duplicatedNumbersList(amountOfNumbers,min,max);
                sumBefore = sum(duplicated);
                dualPivotQuickSort(duplicated, 0, duplicated.length-1);
                sumAfter = sum(duplicated);
                itIsSorted = isSorted(duplicated);
            }
            end = new Date();
            ++rounds;
        } while (end.getTime()-start.getTime() < 1);
        time = (double) (end.getTime()-start.getTime()) / rounds;

        return "The sum of the list before sorting is: " + sumBefore +
                "\n The sum after the sort is: " + sumAfter + " \n" +
                "Is it sorted: " + itIsSorted + " \n" +
                " With the runtime of: milliseconds per round: " + time;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i <= a.length - 1; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println("This is the run with single quicksort random list: \n"+ runAlgorithmAndRecordTime(1, 100000000, 0, 9) + "\n");
        System.out.println("This is the run with single quicksort and sorted list: \n" + runAlgorithmAndRecordTime(2, 100000000, 0, 9) + "\n");
        System.out.println("This is the run with single quicksort and duplicated list: \n" + runAlgorithmAndRecordTime(3, 100000000, 0, 9) + "\n");
        System.out.println("This is the run with dual quicksort and random list: \n" + runAlgorithmAndRecordTime(4, 100000000, 0, 9) + "\n");
        System.out.println("This is the run with dual quicksort and sorted list: \n" + runAlgorithmAndRecordTime(5, 100000000, 0, 9) + "\n");
        System.out.println("This is the run with dual quicksort and duplicated list: \n" + runAlgorithmAndRecordTime(6, 100000000   , 0, 9));

    }
}