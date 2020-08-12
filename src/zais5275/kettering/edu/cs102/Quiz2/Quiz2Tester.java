package zais5275.kettering.edu.cs102.Quiz2;

import zais5275.kettering.edu.cs102.Quiz2.MyList.MyLinkedList;

public class Quiz2Tester {
    public static void main (String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(95);
        list.add(5);
        list.add(5);
        list.add(5);

        System.out.println(list.range());
        System.out.println(list.removeDuplicates());
        System.out.println();
        list.printlist();
    }
}
