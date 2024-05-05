import java.util.*;

public class BinarySearchMonths {

    private static int MyBinSearch(String[] array, String valuetoSearch) {
        int low = 0;
        int high = array.length - 1;

        System.out.println("Searching for: " + valuetoSearch);

        while (low <= high) {
            int midPos = (low + high) / 2;
            String midNum = array[midPos];

            if (valuetoSearch.equals(midNum)) {
                // we found the string and return the position in the array
                System.out.println("Found. " + valuetoSearch + "\n");
                return midPos;
            }
            if (valuetoSearch.compareTo(midNum) < 0) {
                // the data is located to the left or lower than the midNum
                high = midPos - 1; // reposition to the left less 1 as the high
            } else {
                low = midPos + 1; // reposition to the rith plus 1 as the low index
            }
        }
        // if after the search we reached the end or low <= high, we did NOT found it
        System.out.println("NOT Found. " + valuetoSearch + "\n");
        return -1; // NOT found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchMonths search = new BinarySearchMonths();
        String[] data = {"February", "March", "June", "November", "December"};

        // Sort the array before calling the binary search method
        Arrays.sort(data);

        System.out.println("Enter a month to search: ");
        String toSearch = sc.nextLine();
        search.MyBinSearch(data, toSearch);
    }
}