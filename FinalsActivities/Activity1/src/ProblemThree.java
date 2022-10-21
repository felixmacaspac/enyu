import java.util.Scanner;
public class ProblemThree {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a number:");
    int number = input.nextInt();
    
    if (number > 50 && number < 100) {
        System.out.println("The entered number is less than 100");
        System.out.println("The entered number is less than 50");
    } else if (number < 100) {
        System.out.println("The entered number is less than 100");
    } else if (number > 100) {
        System.out.println("The entered number is greater than 100");
    }
  }
}

/*
Test A:
Enter a number:20
The entered
number is
less than 100

Test B:
Enter a number:60
The entered
number is
less than 100
The entered
number is
greater than 50

Test C:
Enter a number:120
The entered
number is
greater than
100
*/
