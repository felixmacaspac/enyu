import java.util.Scanner

public class ActivityOne {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Input a number: ");

    int outputNumber = input.nextInt();
    // call the method
    result(outputNumber);
  }

  // method header
  public static void result(int number) {
    if (number == 0) {
      System.out.println(number + " is zero");
    } else if (number % 2 == 0) {
      System.out.println(number + " is even");
    } else {
      System.out.println(number + " is ");
    }
  } // method definition
}

/* 
return type is "void"
method name is "result"
parameter list is "int number"
*/
