/*
Instructions
1. Create a program that will print the following:
2. Linked list : [2 4 6 8 10]
3. Value to add at the beginning : [0 1]
4. After adding the value at the beginning, the list is: [0 1 2 4 6 8 10]
5. Value to add at the end: [11 12]
6. After adding the value at the end, the list is: [0 1 2 4 6 8 10 11 12]
7. Value to add in between 1 and 2: [3]
8. After adding the value, the list is: [0 1 3 2 4 6 8 10 11 12]
*/

class LinkedListOne {
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

    public void insertAfter(Node prev_node, int new_data) {
        if (prev_node == null) {
            System.out.println("The given previous node cannot be null");
            return;
        }
        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;
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

    public void printList() {
        Node tnode = head;
        System.out.print("[");
        while (tnode != null) {
            System.out.print(tnode.data);
            tnode = tnode.next;
            if(tnode != null) System.out.print(" ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        LinkedListOne llist = new LinkedListOne();

        // Linked list : [2 4 6 8 10]
        llist.insertAtEnd(2);
        llist.insertAtEnd(4);
        llist.insertAtEnd(6);
        llist.insertAtEnd(8);
        llist.insertAtEnd(10);
        System.out.println("\nLinked list : ");
        llist.printList();

        // Value to add at the beginning : [0 1]
        System.out.println("\nValue to add at the beginning : [0 1]");
        llist.insertAtBeginning(1);
        llist.insertAtBeginning(0);

        System.out.println("After adding the value at the beginning, the list is: ");
        llist.printList();

        // Value to add at the end: [11 12]
        System.out.println("\nValue to add at the end: [11 12]");
        llist.insertAtEnd(11);
        llist.insertAtEnd(12);
        System.out.println("After adding the value at the end, the list is: ");
        llist.printList();

        // 7. Value to add in between 1 and 2: [3]
        System.out.println("\nValue to add in between 1 and 2: [3]");
        llist.insertAfter(llist.head.next, 3); // Adding after node with value 1.
        System.out.println("After adding the value, the list is: ");
        llist.printList();
    }
}
