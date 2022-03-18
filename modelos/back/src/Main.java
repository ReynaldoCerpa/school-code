import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] numArr;
        String temp;
        int streak = 0;
        
        System.out.print("Ingrese números: ");
        String input = scan.nextLine();
        numArr =  input.split(" ",0);

        while(true) {
            streak = 0;
            for (int i = 0; i < numArr.length - 1; i++) {
                if(Integer.parseInt(numArr[i]) > Integer.parseInt(numArr[i + 1])) {
                    temp = numArr[i + 1];
                    numArr[i + 1] = numArr[i];
                    numArr[i] = temp;
                } else {
                    streak++;
                }
            }
            if (streak == numArr.length - 1) {
               break; 
            }
        }

        System.out.print("Resultado: ");
        for (String x : numArr) {
            System.out.print(x + " ");
        }
    }
}
