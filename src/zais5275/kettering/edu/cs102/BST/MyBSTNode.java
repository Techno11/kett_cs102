package zais5275.kettering.edu.cs102.BST;

class MyBSTNode {

    MyBSTNode childLeft;
    MyBSTNode childRight;
    String item; // Item (Key)

    public MyBSTNode(MyBSTNode childLeft, MyBSTNode childRight, String item) {
        this.childLeft = childLeft;
        this.childRight = childRight;
        this.item = item;
    }

    public MyBSTNode(String item) {
        this.childLeft = null;
        this.childRight = null;
        this.item = item;
    }
}
