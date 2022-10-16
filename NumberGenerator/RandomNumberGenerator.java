import java.util.Arrays;
import java.util.Random;

public class RandomNumberGenerator {
    public static void main (String[] args) {
        Random rand = new Random();
        int min = 1, max = 100, slots = 6;

        // all elements will be initialized from zero
        int[] lotteryArray = new int[slots];
        boolean isRepeated;
        int randomNumber = 0;

        for (int indexDrawn = 0; indexDrawn < slots; indexDrawn++) {
            do {
                isRepeated = false;
                // 1 to 49
                randomNumber = rand.nextInt(max + 1 - min) + min;
                // check for repeated number against
                // the filled non-zero elements
                for (int k = 0; k <= indexDrawn; k++) {
                    // check against each filled non-zero elements
                    // hope that none repeats the random number
                    if (lotteryArray[k] == randomNumber) {
                        isRepeated = true;
                        break;
                    }
                }
            } while (isRepeated);
            lotteryArray[indexDrawn] = randomNumber;
        }

        Arrays.sort(lotteryArray);
        System.out.println("The result of the lottery:");
        for (int i = 0; i < slots; i++) {
            System.out.println(lotteryArray[i] + " ");
        }
    }
}
