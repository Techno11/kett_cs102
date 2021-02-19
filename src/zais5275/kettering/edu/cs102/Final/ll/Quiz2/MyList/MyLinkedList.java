package zais5275.kettering.edu.cs102.Final.ll.Quiz2.MyList;

public class MyLinkedList {
    private MyLLNode head;


    public void removeInRange ( int indexA, int indexB ) {
        int i = 0;
        MyLLNode currRoot = head;
        int totalToRemove = indexB - indexA;
        int totalRemoved = 0;
        // Run loop while linked list index is less than end index, or until we reach the end of the list
        while(totalRemoved <= totalToRemove && currRoot.next != head) {
            if(i + 1 >= indexA) { // If the next node is within the index to be removed
                // Set remove the next node from the list
                currRoot.next = currRoot.next.next;
                // Updated # removed
                totalRemoved++;
            } else {
                currRoot = currRoot.next;
            }
            // In case there is an error in the linked list, exit gracefully
            if (currRoot == null) break;
            i++;
        }
    }


    // FOR TESTING

    private MyLLNode tail;
    private int size = 0;
    public void add(int n) {
        MyLLNode newNode = new MyLLNode();
        newNode.item = n;
        if(head == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void printlist() {
        MyLLNode temp = head;
        while(temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }
}

