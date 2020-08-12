package zais5275.kettering.edu.cs102.Quiz2.MyList;

public class MyLinkedList {
    private MyNode head;

    // Calculate the Range of the list
    public int range () {
        // Variables to track max and mins
        int max = head.item;
        int min = head.item;

        // Iterate through our list
        MyNode temp = head;
        while(temp != null) {
            if(temp.item > max) { // Check if item is greater than the current max
                max = temp.item;
            } else if (temp.item < min) { // check if item is less than min
                min = temp.item;
            }

            // Update temp for next iteration
            temp = temp.next;
        }

        // Calculate and return range
        return max - min;
    }

    // Remove all duplicates in the list
    public int removeDuplicates() {
        // Variable to track size
        int size = 0;
        // Iterate through list
        MyNode temp = head;
        while(temp != null) {
            // Iterate through the list, removing items equal to this first occurrence
            MyNode temp2 = temp;
            while (temp2.next != null) {
                // If these are equal, they are duplicates
                if(temp2.next.item == temp.item) {
                    temp2.next = temp2.next.next;
                } else { // If they're not equal, check the next item in the list
                    temp2 = temp2.next;
                }
            }
            // Update for next element
            temp = temp.next;
            // Increase size, if next element isn't null
            if(temp != null) size++;
        }

        return size;
    }

    // FOR TESTING

    private MyNode tail;
    private int size = 0;
    public void add(int n) {
        MyNode newNode = new MyNode();
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
        MyNode temp = head;
        while(temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }
}

