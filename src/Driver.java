import java.util.Scanner;
public class Driver {
    public static void main(String[] args){

        Scanner s = new Scanner(System.in);

        System.out.println("Please input a string for x: ");
        String x = s.next();

        System.out.println("Please input a string for y: ");
        String y = s.next();

//        String y = "TAAGGTCA";
//        String x = "AACAGTTACC";
//
//        String y1 = "GACATATTAC";
//        String x1 = "AACGTAGAC";

        Sequence s1 = new Sequence();

        s1.startSequence(x, y);
        //s1.startSequence(x1, y1);
    }
}
