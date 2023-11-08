// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.



import java.util.Scanner;



public class Main {
    static class BinaryTree {
        Node root;

        static class Node {
            String val;
            Node left;
            Node right;

            Node(String data) {
                val = data;
                left = null;
                right = null;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to CCDATRCL Pinoy Henyo - Algoholics! ");
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;

        while (!isOver) {
            System.out.println("[1] Computer vs Player 1");
            System.out.println("[2] Player 1 vs. Player 2");
            System.out.println("[3] Exit");
            String command = sc.nextLine();

            if (command.equalsIgnoreCase("3")) {
                isOver = true;
                System.out.println("Exiting the Program!");
            } else if (command.equalsIgnoreCase("1")) {
                computerVsPlayer();
            } else if (command.equalsIgnoreCase("2")) {
                playerVsPlayer();
            } else {
                System.out.println("Invalid Input!");
            }
        }
    }

    public static void computerVsPlayer() {
        Scanner sc = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node("Q. Is it an animal");
        tree.root.left = new BinaryTree.Node("Q.Can it fly");
        tree.root.right = new BinaryTree.Node("Q.Does it have wheels");
        tree.root.left.left = new BinaryTree.Node("A. Bird");
        tree.root.left.right = new BinaryTree.Node("Q. Does it have a tail?");
        tree.root.left.right.left = new BinaryTree.Node("Q. Does it squeak");
        tree.root.left.right.left.left = new BinaryTree.Node("A. Mouse");
        tree.root.left.right.left.right = new BinaryTree.Node("Q. Does it have a trunk?");
        tree.root.left.right.left.right.left = new BinaryTree.Node("A. Elephant");
        tree.root.left.right.left.right.right = new BinaryTree.Node("A. Lion");
        tree.root.left.left.left = new BinaryTree.Node("A. Spider");
        tree.root.right.left = new BinaryTree.Node("Q. Does it have an engine?");
        tree.root.right.left.left = new BinaryTree.Node("A. Car");
        tree.root.right.left.right = new BinaryTree.Node("A. Bicylcle");
        tree.root.right.right = new BinaryTree.Node("Q. Is it nice?");
        tree.root.right.right.left = new BinaryTree.Node("A. Student Leader");
        tree.root.right.right.right = new BinaryTree.Node("A. Teacher");

        boolean isOver = false;
        BinaryTree.Node current = tree.root;
        while (!isOver) {
            boolean isAnswer = current.val.charAt(0) == 'A';


            System.out.print(current.val);
            String command = sc.nextLine();

            if (command.equalsIgnoreCase("yes")) {
                if (isAnswer) {
                    System.out.println("Hooray, I win!");
                    isOver = true;
                }
                current = current.left;
            } else if (command.equalsIgnoreCase("no")) {
                current = current.right;
            } else {
                System.out.println("Invalid Input!");
            }
        }


    }

    public static void playerVsPlayer() {
        Scanner sc = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        boolean isOver = false;


        BinaryTree.Node current = tree.root;
        while (!isOver) {
            System.out.print("Question (q if over): ");
            String question = sc.nextLine();

            System.out.print("Answer: ");
            String answer = sc.nextLine();


            if (question.equalsIgnoreCase("q")) {
                System.out.println("PLayer 2 Wins!");
                isOver = true;
            } else {
                if (answer.equalsIgnoreCase("yes")) {
                    current = new BinaryTree.Node("question");
                    current = current.left;
                } else if (answer.equalsIgnoreCase("no")) {
                    current = new BinaryTree.Node("question");
                    current = current.right;
                } else {
                    System.out.println("Invalid input!");
                }
            }
        }
    }
}