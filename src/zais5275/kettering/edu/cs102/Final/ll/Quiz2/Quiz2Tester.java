package zais5275.kettering.edu.cs102.Final.ll.Quiz2;

import zais5275.kettering.edu.cs102.Final.ll.Quiz2.MyList.MyLinkedList;

public class Quiz2Tester {
    public static void main (String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        list.removeInRange(2, 7);
        System.out.println();
        list.printlist();
    }
}
