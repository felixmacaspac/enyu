public class ProblemThree {
  public static void main(String[] args) {
      int count = 0;
      for (int num = 1; num <= 5; num++) {
          System.out.println(num * 5 / 2);
          if (num % 2 == 0)
              count += 3;
          else
              count += 2;
          System.out.print(count + " ");
      }
  }
}