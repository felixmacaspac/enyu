import java.util.Scanner;
public class ProblemTwo {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the age:");
    int age = input.nextInt();
    System.out.println("Enter the weight:");
    int weight = input.nextInt();
    
    if (age >= 18 && weight >= 50) {
        System.out.println("You're eligible to donate blood");
    } else if (age < 18) {
        System.out.println("Age must be greater than 18");
    } else if (weight < 50) {
        System.out.println("You're not eligible to donate blood");
    }
  }
}

/*
TEST CASES: 

Test A:
Enter the age:
18
Enter the weight:
55
You are eligible to donate blood

Test B:
Enter the age:
17
Enter the weight:
50
Age must be greater than 18

Test C:
Enter the age:
19
Enter the weight:
45
You are not eligible to donate blood
 */