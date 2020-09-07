package zais5275.kettering.edu.cs102.Quiz3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyQueue {
    private static int[] array = {0,1,2,3,4,5,6,7,8,9,10,11};; // Circular array.
    private static int front = 0; // Index of the queue front.
    private static int back = 11; // Index of the queue back.
    private static int numItemsMax = 10; // Max num of items this queue can store.
    private static int numItems = 10; // Current num of items in this queue.

    private static Queue<Integer> queue1 = new LinkedList<>();
    private static Queue<Integer> queue3 = new LinkedList<>();
    private static Stack<Integer> stack2 = new Stack<>();

    public static void main(String[] args) {
        reverse();
        System.out.println(Arrays.toString(array));

        queue1.add(10);
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        queue1.add(4);
        queue1.add(5);
        queue1.add(10);
        queue1.add(6);
        queue1.add(7);
        queue1.add(10);
        deleteAllEqualToCount();
    }

    // Reverse Queue in place
    public static void reverse() {
        int f = front;
        int b = back;
        // Iterate through until we get to the center of the queue
        while (f != (front + back) / 2) {
            int temp = array[b];
            array[b] = array[f];
            array[f] = temp;
            f++;
            b--;
        }
    }

    public static void deleteAllEqualToCount() {
        int count = 0;
        // Push queue to stack, coincidentally reversing it for us as well, also count
        while(!queue1.isEmpty()) {
            stack2.push(queue1.remove());
            count++;
        }

        // Pop stack and transfer back to queue1, not adding values equal to count
        while(!stack2.isEmpty()) {
            int temp = stack2.pop();
            if(temp != count) queue1.add(temp);
        }
    }
}
