import java.util.Scanner;
public class ProblemFour {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the dept:");
    String dept = input.nextLine();
    
    boolean deptValid = true; 
    String deptOutput = "";
    
    switch(dept) {
        case "CIT":
            deptOutput = "The student is in the Computer Science Department";
            break;
        case "EEE":
            deptOutput = "The student is in the Electrical and Electronics Department";
            break;
        case "ME":
            deptOutput = "The student is in the Mechanical Department";
            break;
        default:
            deptValid = false;
            System.out.println("Enter a valid department");
    }
    
    if (deptValid) {
        System.out.println("Enter a year:");
        int year = input.nextInt();
        
        System.out.println(deptOutput);
        switch(year) {
            case 1:
                System.out.println("The student is in 1st year");
                break;
            case 2:
                System.out.println("The student is in 2nd year");
                break;
            case 3:
                System.out.println("The student is in 3rd year");
                break;
            default:
                System.out.println("Enter a valid year");
                break;
        }
    }
  }
}

/*
Test A:
Enter the dept:
CIT
Enter a year:
1
The student is in the Computer Science department
The student is in 1st year

Test B.
Enter the dept:
EEE
Enter a year:
2
The student is in the Electrical and Electronics department
The student is in 2nd year

Test C:
Enter the dept:
ME
Enter a year:
3
The student is in the Mechanical department
The student is in 3rd year

Test D:
Enter the dept:
me
Enter a valid department
*/
