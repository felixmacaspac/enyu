import java.util.Scanner;

public class Main {
    // ANSI color code declarations
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE_BOLD = "\033[1;35m";
    public static final String ANSI_WHITE = "\033[0;37m";

    static class BinaryTree {
        Node root;

        static class Node {
            String val;
            Node left;
            Node right;

            Node(String data) {
                val = data;
                left = null; // yes
                right = null; // no
            }
        }

        BinaryTree() {
            initializeTree();
        }

        // Initial questions/tree
        private void initializeTree() {
            // Root question
            root = new Node("Q. Is it living thing?");

            // Branch for animal
            root.left = new Node("Q. Is it an animal?");
            root.left.left = new Node("Q. Does it fly");
            root.left.left.left = new Node("A. Bird");

            root.left.left.right = new Node("Q. Does it have a tail?");
            root.left.left.right.left = new Node("A. Dog");

            root.left.left.right.right = new Node("Q. Is it an Aquatic Animal?");
            root.left.left.right.right.left = new Node("A. Fish?");

            // Branch for person
            root.left.right = new Node("Q. Is it a person?");
            root.left.right.left = new Node("Q. Is it a singer?");

            root.left.right.left.right = new Node("Q. Is it an actor?");
            root.left.right.left.right.left = new Node("Q. Is it a male actor?");
            root.left.right.left.right.left.left = new Node("A. Daniel Padilla");
            root.left.right.left.right.left.right = new Node("Q. Is it a female actress?");
            root.left.right.left.right.left.right.left = new Node("A. Kathryn Bernardo");

            root.left.right.left.left = new Node("Q. Is it a female singer?");
            root.left.right.left.left.left = new Node("A. Moira dela Torre");

            root.left.right.left.left.right = new Node("Q. Is it a male singer?");
            root.left.right.left.left.right.left = new Node("A. Zack Tabudlo?");

            // Branch for place
            root.right = new Node("Q. Is it a place?");
            root.right.left = new Node("Q. Is it a tourist spot?");
            root.right.left.left = new Node("A. Beach");

            // Branch for food
            root.right.right = new Node("Q. Is it a food?");
            root.right.right.left = new Node("Q. Is it a local food?");
            root.right.right.left.left = new Node("A. Adobo");

            // Branch for thing
            root.right.right.right = new Node("Q. Is it a thing?");
            root.right.right.right.left = new Node("Q. Is it fragile?");
            root.right.right.right.left.left = new Node("A. Glass?");
        }

    }

    private static final Scanner sc = new Scanner(System.in);
    private static final BinaryTree tree = new BinaryTree();

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE_BOLD + "Welcome to CCDATRCL Pinoy Henyo - Algoholics!" + ANSI_RESET);
        boolean isOver = false;

        while (!isOver) {
            System.out.println(ANSI_WHITE + "--------------------------------------------------------------------------------" + ANSI_RESET);
            System.out.println("[1] Computer vs Player 1");
            System.out.println("[2] Player 1 vs. Player 2");
            System.out.println("[3] Exit");
            String command = sc.nextLine();

            switch (command.toLowerCase()) {
                case "1":
                    computerVsPlayer();
                    break;
                case "2":
                    playerVsPlayer();
                    break;
                case "3":
                    isOver = true;
                    System.out.println("Exiting the Program!");
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
    }

    private static void computerVsPlayer() {
        // Display the game header and instructions
        System.out.println(ANSI_WHITE + "--------------------------------------------------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE_BOLD + "Instructions:" + ANSI_RESET);
        System.out.println("Think of an item and the computer will try to guess it.");
        System.out.println("Answer with 'yes' or 'no'.");
        System.out.println(ANSI_WHITE + "--------------------------------------------------------------------------------" + ANSI_RESET);

        // Initialize the current node to the root of the tree and prepare game state variables
        BinaryTree.Node current = tree.root;
        boolean isGameOver = false;

        // Game loop that runs until the game is over
        while (!isGameOver) {
            // If the current node is null, the computer has reached the end of its decision tree
            if (current == null) {
                System.out.println(ANSI_GREEN + "The computer could not guess what you are thinking. You win!" + ANSI_RESET);
                isGameOver = true;
                continue;
            }

            // Check if the current node is an answer node
            boolean isNodeAnswer = current.val.startsWith("A.");
            // Prompt the player for a response based on whether the current node is a question or an answer
            System.out.print((isNodeAnswer ? "Answer: Is it " + current.val.substring(3) + "?" : current.val) + " (Yes/No): ");
            String response = sc.nextLine().trim().toLowerCase();

            if ("yes".equals(response)) {
                // If the node is an answer and the response is yes, the computer wins
                if (isNodeAnswer) {
                    System.out.println(ANSI_GREEN + "The computer guessed it correctly!" + ANSI_RESET);
                    isGameOver = true;
                } else {
                    // Otherwise, move to the next node in the yes (left) direction
                    current = current.left;
                }
            } else if ("no".equals(response)) {
                // If the node is an answer and the response is no, the player wins
                if (isNodeAnswer) {
                    System.out.println(ANSI_GREEN + "The computer gives up, you win!" + ANSI_RESET);
                    isGameOver = true;
                } else {
                    // Otherwise, move to the next node in the no (right) direction
                    current = current.right;
                }
            } else {
                // If the response is not yes, no, quit, or q, it's invalid
                System.out.println(ANSI_RED + "Invalid input! Please answer 'yes' or 'no'." + ANSI_RESET);
            }
        }
    }

    private static void playerVsPlayer() {
        // Display game instructions for player vs. player mode
        System.out.println(ANSI_WHITE + "--------------------------------------------------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE_BOLD + "Instructions:" + ANSI_RESET);
        System.out.println("Player 2, think of an item but do not reveal it.");
        System.out.println("Player 2, type 'quit' or 'q' if Player 1 guesses the item correctly.");
        System.out.println("Player 1, you have 15 questions to guess what Player 2 is thinking.");
        System.out.println(ANSI_WHITE + "--------------------------------------------------------------------------------" + ANSI_RESET);

        // Set the maximum number of guesses and initialize the game state
        int guessLimit = 15;
        int guessesTaken = 0;
        boolean guessedCorrectly = false;

        // Loop to allow Player 1 to make guesses until the limit is reached or the correct guess is made
        while (guessesTaken < guessLimit && !guessedCorrectly) {
            // Prompt Player 1 to ask a question
            System.out.print(ANSI_YELLOW + "Player 1, ask your question (" + (guessLimit - guessesTaken) + " guesses left): " + ANSI_RESET);
            String question = sc.nextLine().trim();

            // Allow Player 1 to give up by typing 'quit' or 'q'
            if ("quit".equalsIgnoreCase(question) || "q".equalsIgnoreCase(question)) {
                System.out.println(ANSI_GREEN + "Player 1 has given up. Player 2 wins!" + ANSI_RESET);
                break;
            }

            // Ensure Player 1 asks a question
            if (question.isEmpty()) {
                System.out.println(ANSI_RED + "You did not ask a valid question." + ANSI_RESET);
                continue; // Skip to the next iteration without counting this as a guess
            }

            // Wait for a valid response from Player 2
            boolean validResponse = false;
            while (!validResponse) {
                // Prompt Player 2 for an answer
                System.out.print(ANSI_BLUE +"Player 2, please answer 'yes', 'no', or type 'quit'/'q' if the guess is correct: " + ANSI_RESET);
                String response = sc.nextLine().trim().toLowerCase();

                // Validate Player 2's response
                if (response.isEmpty()) {
                    System.out.println(ANSI_RED + "Invalid input. You must answer the question." + ANSI_RESET);
                } else if (response.matches("yes|no|quit|q")) {
                    // Correctly formatted response
                    validResponse = true;

                    // Check if Player 1's guess is correct
                    if ("quit".equals(response) || "q".equals(response)) {
                        System.out.println(ANSI_GREEN + "Player 1 guessed correctly! Player 1 wins!" + ANSI_RESET);
                        guessedCorrectly = true;
                    }
                } else {
                    // Response was not 'yes', 'no', 'quit', or 'q'
                    System.out.println(ANSI_RED + "Invalid response. Please answer 'yes', 'no', or type 'quit'/'q' if the guess is correct." + ANSI_RESET);
                }
            }

            // Increment the guess count after a valid interaction
            if (!guessedCorrectly) {
                guessesTaken++;
            }
        }

        // Check if the guess limit was reached without a correct guess
        if (!guessedCorrectly && guessesTaken >= guessLimit) {
            System.out.println(ANSI_RED + "Guess limit reached. Player 2 wins!" + ANSI_RESET);
        }
    }
}
