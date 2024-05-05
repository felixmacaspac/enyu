import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinearSearchMonths {

    public static void main(String[] args) {
        // Used HashMap to store month descriptions as keys and month names as values
        HashMap<String, String> monthMap = new HashMap<>();

        // Created key-value pairs to the HashMap, associating descriptions with months
        monthMap.put("hearts", "February");
        monthMap.put("independence", "June");
        monthMap.put("fire", "March");
        monthMap.put("dead", "November");
        monthMap.put("gifts", "December");

        Scanner sc = new Scanner(System.in);

        System.out.println("Hi! I am a month. Can you guess what month I am?");
        System.out.println("Enter your guess based on the significant event of a month: ");
        String description = sc.nextLine();

        // Call the linearSearch function to find the month based on the user input
        String month = linearSearch(monthMap, description);

        // Display output
        if (month != null) {
            System.out.println("Congratulations, I'm the month of " + month);
        } else {
            System.out.println("No matching month found for the given description. Please try again");
        }
    }

    public static String linearSearch(HashMap<String, String> map, String key) {
        // Iterate through each key-value pair (entry) in the HashMap
        for (Map.Entry<String, String> entry : map.entrySet()) {
            // Check if the current key (description) matches the user input
            if (entry.getKey().equals(key)) {
                // If there's a match, return the corresponding value (month name)
                return entry.getValue();
            }
        }
        // If no match is found, return null
        return null;
    }
}
