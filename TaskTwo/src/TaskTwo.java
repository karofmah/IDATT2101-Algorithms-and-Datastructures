import java.util.Date;

public class TaskTwo {

    public static double recursionAlgorithmOne(double x, double n){
        double answer;
        if(n==0){
            answer=1;
        }else{
            answer = x* recursionAlgorithmOne(x,n-1);
        }
        return answer;
    }

    public static double pow(double x, double n){
        return Math.pow(x,n);
    }

    public static double recursionAlgorithmTwo(double x, double n){
        double answer;
        if(n==0){
            answer=1;
        }else if(n % 2 == 0){
            answer = recursionAlgorithmTwo(x*x,n/2);
        }else{
            answer = x*recursionAlgorithmTwo(x*x,(n-1)/2);
        }
        return answer;
    }


    public static String runAlgorithmAndRecordTime(int algorithm,double x,double n) {
        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;
        double answer;
        do {
            if(algorithm == 1){
                answer = recursionAlgorithmOne(x,n);
            }else if(algorithm == 2){
                answer = recursionAlgorithmTwo(x,n);
            }else{
                answer = pow(x,n);
            }
            end = new Date();
            ++rounds;
        } while (end.getTime()-start.getTime() < 2000);
        time = (double) (end.getTime()-start.getTime()) / rounds;

        return answer + "\n" + "Milliseconds per round: " + time;
    }

    public static void main(String[] args) {
        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 100 " + runAlgorithmAndRecordTime(1,1.001,100) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 100 "+ runAlgorithmAndRecordTime(2,1.001,100) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 100 " + runAlgorithmAndRecordTime(3,1.001,100) + "\n");

        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 500 " + runAlgorithmAndRecordTime(1,1.001,500) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 500 "+ runAlgorithmAndRecordTime(2,1.001,500) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 500 " + runAlgorithmAndRecordTime(3,1.001,500) + "\n");

        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 1000 " + runAlgorithmAndRecordTime(1,1.001,1000) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 1000 "+ runAlgorithmAndRecordTime(2,1.001,1000) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 1000 " + runAlgorithmAndRecordTime(3,1.001,1000) + "\n");

        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 1500 " + runAlgorithmAndRecordTime(1,1.001,1500) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 1500 "+ runAlgorithmAndRecordTime(2,1.001,1500) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 1500 " + runAlgorithmAndRecordTime(3,1.001,1500) + "\n");

        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 2000 " + runAlgorithmAndRecordTime(1,1.001,2000) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 2000 "+ runAlgorithmAndRecordTime(2,1.001,2000) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 2000 " + runAlgorithmAndRecordTime(3,1.001,2000) + "\n");
        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 3500 " + runAlgorithmAndRecordTime(1,1.001,3500) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 3500 "+ runAlgorithmAndRecordTime(2,1.001,3500) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 3500 " + runAlgorithmAndRecordTime(3,1.001,3500) + "\n");

        System.out.println("recursionAlgorithmOne with x as 1.001 and n as 5000 " + runAlgorithmAndRecordTime(1,1.001,5000) + "\n");
        System.out.println("recursionAlgorithmTwo with x as 1.001 and n as 5000 "+ runAlgorithmAndRecordTime(2,1.001,5000) + "\n");
        System.out.println("pow algorithm with x as 1.001 and n as 5000 " + runAlgorithmAndRecordTime(3,1.001,5000) + "\n");

    }

}