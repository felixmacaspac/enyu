import java.util.Scanner;
public class ProblemSix {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your numeric test score");
    int grade = input.nextInt();
    
    if (grade >= 90 && grade <= 100) {
        System.out.println("Your grade is A");
    } else if (grade >= 80 && grade <= 89) {
        System.out.println("Your grade is B");
    } else if (grade >= 70 && grade <= 79) {
        System.out.println("Your grade is C");
    } else if (grade >= 50 && grade <= 69) {
        System.out.println("Your grade is D");
    } else if (grade <= 49) {
        System.out.println("Your grade is F");
    }
  }
}

/*
Test A: 
Enter your numeric test score:
56 Your grade is D

Test B:
Enter your numeric test score:
89 Your grade is B
 */