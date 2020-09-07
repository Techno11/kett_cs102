package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import java.util.Iterator; // JCF interface
import java.util.NoSuchElementException; // ...

// Iterator class for the tennis player container
class TennisPlayerContainerIterator implements Iterator<TennisPlayer> {

    private TennisPlayerContainerNode root; // Entry Point to container data
    private TennisPlayerQueue queue;

    public TennisPlayerContainerIterator(TennisPlayerContainerNode r) {
        this.root = r;
        this.queue = new TennisPlayerQueue();
    }

    public boolean hasNext() {
        // Check if internal queue is not empty
        return this.queue.isEmpty();
    }

    public TennisPlayer next() {
        // Dequeue a player from internal queue, and return it
        try {
            return this.queue.dequeue();
        } catch (TennisDatabaseException e) {
            throw new NoSuchElementException( "No other elements in iterator");
        }
    }

    // Prepare inorder traversal
    public void setInorder() throws TennisDatabaseException {
        // Clean the internal queue, ready to be filled up
        this.queue = new TennisPlayerQueue();
        // Call recursive inplementation to traverse the BST using inorder strat
        setInorderRec(this.root);
    }

    // Recursive implementation for the "setInorder method"
    private void setInorderRec(TennisPlayerContainerNode currRoot) throws TennisDatabaseException {
        // Check if BST is empty
        if(currRoot == null) {
            // Do nothing
        } else {
            setInorderRec(currRoot.getLeftChild()); // Process left of currRoot
            this.queue.enqueue(currRoot.getPlayer()); // Queue player
            setInorderRec(this.root.getRightChild()); // Process right of currRoot
        }
    }

    // Prepare preorder traversal
    public void setPreorder() throws TennisDatabaseException {
        // Clean the internal queue, ready to be filled up
        this.queue = new TennisPlayerQueue();
        // Call recursive inplementation to traverse the BST using inorder strat
        setPreorderRec(this.root);
    }

    // Recursive implementation for the "setPreorder method"
    private void setPreorderRec(TennisPlayerContainerNode currRoot) throws TennisDatabaseException {
        // Check if BST is empty
        if(currRoot == null) {
            // Do nothing
        } else {
            this.queue.enqueue(currRoot.getPlayer()); // Queue player
            setPreorderRec(currRoot.getLeftChild()); // Process left of currRoot
            setPreorderRec(this.root.getRightChild()); // Process right of currRoot
        }
    }

    // Prepare postorder traversal
    public void setPostorder() throws TennisDatabaseException {
        // Clean the internal queue, ready to be filled up
        this.queue = new TennisPlayerQueue();
        // Call recursive inplementation to traverse the BST using inorder strat
        setPostorderRec(this.root);
    }

    // Recursive implementation for the "setPostorder method"
    private void setPostorderRec(TennisPlayerContainerNode currRoot) throws TennisDatabaseException {
        // Check if BST is empty
        if(currRoot == null) {
            // Do nothing
        } else {
            setPostorderRec(currRoot.getLeftChild()); // Process left of currRoot
            setPostorderRec(this.root.getRightChild()); // Process right of currRoot
            this.queue.enqueue(currRoot.getPlayer()); // Queue player
        }
    }
}
