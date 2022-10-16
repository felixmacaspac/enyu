import java.util.Scanner;
public class ProblemSeven {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a string");
    String inputText = input.nextLine();
    
    switch(inputText) {
        case "java":
            System.out.println("Java is the best programming language");
            break;
        case "java string":
            System.out.println("This is an example Java Switch using string");
        default:
            System.out.println("Sorry, please enter the different word");
    }
  }
}

/*
Test A:
Enter a String: java
Java is the best programming language

Test B.:
Enter a String: java string
This is an example for Java Switch using String

Test C:
Enter a String: Anything
Sorry, please enter the different word
 */