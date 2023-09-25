/*
Instructions

1. Here are the initial values:
2
4
6

2. Now inserting the value 5.
Here are the nodes now.
2
4
5
6

3. Now deleting the last node.
Here are the nodes left.
2
4
5
*/

class LinkedListTwo {
    Node head;

    // Create a node
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public void insertAtBeginning(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    public void insertAfter(int value, int new_data) {
        Node prev_node = searchForNode(value);

        if (prev_node == null) {
            System.out.println("The given value " + value + " is not found in the list");
            return;
        }

        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;
    }

    private Node searchForNode(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void insertAtEnd(int new_data) {
        Node new_node = new Node(new_data);
        if (head == null) {
            head = new Node(new_data);
            return;
        }
        new_node.next = null;

        Node last = head;
        while (last.next != null)
            last = last.next;
        last.next = new_node;
        return;
    }

    public void deleteLastNode() {
        if (head == null) return;
        if (head.next == null) {
            head = null;
            return;
        }

        Node second_last = head;
        while (second_last.next.next != null)
            second_last = second_last.next;

        second_last.next = null;
    }

    public void printList() {
        Node tnode = head;
        while (tnode != null) {
            System.out.println(tnode.data);
            tnode = tnode.next;
        }
    }

    public static void main(String[] args) {
        LinkedListTwo llist = new LinkedListTwo();

        // Linked list : [2 4 6]
        llist.insertAtEnd(2);
        llist.insertAtEnd(4);
        llist.insertAtEnd(6);
        System.out.println("Here are the initial values:");
        llist.printList();

        // Inserting the value 5 after 4
        System.out.println("\nNow inserting the value 5.");
        llist.insertAfter(4, 5);

        System.out.println("Here are the nodes now.");
        llist.printList();

        // Deleting the last node
        System.out.println("\nNow deleting the last node.");
        llist.deleteLastNode();

        System.out.println("Here are the nodes left.");
        llist.printList();
    }
}
