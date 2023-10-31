import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;

    // initialize new node with data value
    public Node(int data) {
        this.data = data;
    }
}

class BinaryTree {
    Node root;

    // insert new node with given data
    public void insertNode(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        insertRecursive(root, data);
    }

    // Recursive method to insert a new node (left to right) and (top to bottom)
    private boolean insertRecursive(Node current, int data) {
        if (current.left == null) {
            current.left = new Node(data);
            return true;
        } else if (current.right == null) {
            current.right = new Node(data);
            return true;
        } else {
            if (insertRecursive(current.left, data)) {
                return true;
            } else {
                return insertRecursive(current.right, data);
            }
        }
    }

    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
    }
}

public class BinaryTreeActivity
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();

        // initial prompts
        System.out.print("Enter the value of the root node: ");
        tree.insertNode(scanner.nextInt());

        System.out.print("Enter the value for the left node: ");
        tree.insertNode(scanner.nextInt());

        System.out.print("Enter the value for the right node: ");
        tree.insertNode(scanner.nextInt());

        // Loop to keep adding nodes until the user decides to stop
        char choice;
        do {
            System.out.print("Do you want to add another node? [Y/N] ");
            choice = scanner.next().charAt(0);

            if (choice == 'Y' || choice == 'y') {
                System.out.print("Enter the value for the next node: ");
                tree.insertNode(scanner.nextInt());
            }

        } while (choice == 'Y' || choice == 'y');

        System.out.println("The height of the tree is " + tree.getHeight() + ".");
    }
}
