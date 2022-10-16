import java.util.Scanner;
public class ProblemFive {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a year");
    int leapYear = input.nextInt();
    
    if ((leapYear % 400 == 0) || ((leapYear % 4 == 0) && (leapYear % 100 != 0))) {
      System.out.println("The year is leap year.");
    } else {
      System.out.println("The year is not leap year.");
    }
  }
}

/*
Test A:
Enter a year:
2008
The year is leap year

Test B: Enter a year:
2022
The year is not leap year
 */