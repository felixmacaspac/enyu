import java.util.Scanner;
public class ProblemOne {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number:");
        int inputNumber = input.nextInt();
        
        if (inputNumber >= 1) {
            System.out.println("Positive number");
        } else if (inputNumber <= -1) {
            System.out.println("Negative number");
        } else if (inputNumber == 0) {
            System.out.println("Number is zero");
        }
    }
}

/*
TEST CASES: 

Test A:
Enter the number:
12
Positive number

Test B.
Enter the number:
-9
Negative number

Test C:
Enter the number:
0
Number is zero
 */