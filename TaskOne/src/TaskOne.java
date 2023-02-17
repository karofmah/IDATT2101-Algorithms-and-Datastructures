import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class TaskOne {


    public static int[] fillListWithNumbers(int amountOfNumbers, int min, int max){
       Random random = new Random();
        int[] list = new int [amountOfNumbers];
        for (int i=0;i<amountOfNumbers;i++){
            list[i]=random.nextInt(min, max);
        }
        return list;
    }

    public static String findGreatestInterest(int[] listOfValueShareDifference){

        int interest;
        int maxInterest=0;
        int buyDay=0;
        int sellDay=0;

        for (int i = 0; i < listOfValueShareDifference.length; i++) {
            interest=0;
            for (int j = i+1; j < listOfValueShareDifference.length; j++){
                interest+=listOfValueShareDifference[j];
                if(interest>maxInterest){
                    maxInterest=interest;
                    buyDay=i;
                    sellDay=j;
                }
            }
        }
        return "Largest profit: " + maxInterest + "\n Buy day: " + (buyDay+1) + "\n Sell day: " + (sellDay+1);
    }
    public static void main(String[] args) {
        int [] listOfValuesShareDifference={-1,3,-9,2,2,-1,2,-1,-5};
        System.out.println(Arrays.toString(listOfValuesShareDifference));
        System.out.println(findGreatestInterest(listOfValuesShareDifference) +"\n");
        int numberOfValuesShareDifference=10000 ;

        while(numberOfValuesShareDifference<640000) {
            Date start = new Date();
            System.out.println(findGreatestInterest(fillListWithNumbers(numberOfValuesShareDifference,-10,10)));
            Date end = new Date();
            System.out.println("Running time of algorithm with " + numberOfValuesShareDifference + " numbers: " + (end.getTime() - start.getTime()) +" ms" + "\n");
            numberOfValuesShareDifference*=2;
        }
    }
}

