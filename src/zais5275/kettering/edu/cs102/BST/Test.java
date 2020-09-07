package zais5275.kettering.edu.cs102.BST;

public class Test {

    public static void main(String[] args) {
        MyBST bst = new MyBST();

        bst.insert("Jane");

        bst.insert("Bob");
        bst.insert("Tom");

        bst.insert("Alan");
        bst.insert("Ellen");
        bst.insert("Nancy");
        bst.insert("Wendy");

        bst.insert("Carl");

        boolean res1 = bst.search("Nancy");
        boolean res2 = bst.search("Zaiser");
    }
}
