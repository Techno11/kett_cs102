package zais5275.kettering.edu.cs102.BST;

class MyBST {

    MyBSTNode root;

    public MyBST() {
        this.root = null;
    }

    // Postorder traversal
    public void postorder() {
        postorderRec(this.root);
        System.out.println();
    }

    private void postorderRec(MyBSTNode currRoot) {
        if(currRoot == null) { }
        else {
            postorderRec(currRoot.childLeft);
            postorderRec(currRoot.childRight);
            System.out.print(" - " + currRoot.item + " - ");
        }
    }

    // Preorder traversal
    public void preorder() {
        preorderRec(this.root);
        System.out.println();
    }

    private void preorderRec(MyBSTNode currRoot) {
        if(currRoot == null) { }
        else {
            System.out.print(" - " + currRoot.item + " - ");
            preorderRec(currRoot.childLeft);
            preorderRec(currRoot.childRight);
        }
    }

    // Inorder traversal
    public void inorder() {
        inorderRec(this.root);
        System.out.println();
    }

    private void inorderRec(MyBSTNode currRoot) {
        if(currRoot == null) { }
        else {
            inorderRec(currRoot.childLeft);
            System.out.print(" - " + currRoot.item + " - ");
            inorderRec(currRoot.childRight);
        }
    }

    // Size
    public int size() {
        return sizeRec(this.root);
    }

    private int sizeRec(MyBSTNode currRoot) {
        if(currRoot == null) { return 0; }
        else {
            return 1 + sizeRec(currRoot.childLeft) + sizeRec(currRoot.childRight);
        }
    }

    // Depth
    public int depth() {
        return depthRec(this.root);
    }

    private int depthRec(MyBSTNode currRoot) {
        if(currRoot == null) { return 0; }
        else {
            int depthLeft = depthRec(currRoot.childLeft);
            int depthRight = depthRec(currRoot.childRight);
            int maxLR = Math.max(depthLeft, depthRight);
            return 1 + maxLR;
        }
    }

    // Search
    public boolean search(String newItem) {
        return searchRec(this.root, newItem);
    }

    // Search Insert
    // Input: Root Node of input BST
    // Input: New Item to insert
    private boolean searchRec(MyBSTNode currRoot, String newItem) {
        // Base Case, Check if empty
        if(currRoot == null) {
            return false; // Not Found
        } else { // Not First node, forward insert left or right subtree
            int compare = currRoot.item.compareTo(newItem);
            if(compare > 0) {
                // Search in Right Subtree
                return searchRec(currRoot.childRight, newItem);
            } else if (compare < 0) {
                // Search in Left Subtree
                return searchRec(currRoot.childLeft, newItem);
            } else {
                return true; // Found!
            }
        }
    }

    // Insert
    public void insert(String newItem) {
        this.root = insertRec(this.root, newItem);
    }

    // Recursive Insert
    // Input: Root Node of input BST
    // Input: New Item to insert
    private MyBSTNode insertRec(MyBSTNode currRoot, String newItem) {
        // Base Case, Check if empty
        if(currRoot == null) {
            return new MyBSTNode(newItem);
        } else { // Not First node, forward insert left or right subtree
            int compare = currRoot.item.compareTo(newItem);

            if(compare > 0) {
                // Insert in Right Subtree
                MyBSTNode newRightChild = insertRec(currRoot.childRight, newItem);
                currRoot.childRight = newRightChild;
                return currRoot;
            } else if (compare < 0) {
                // Insert in Left Subtree
                MyBSTNode newLeftChild = insertRec(currRoot.childLeft, newItem);
                currRoot.childLeft = newLeftChild;
                return currRoot;
            } else {
                // Error, Key already stored
                // throw new Exception()
                return currRoot; // Do Nothing
            }
        }
    }


}
