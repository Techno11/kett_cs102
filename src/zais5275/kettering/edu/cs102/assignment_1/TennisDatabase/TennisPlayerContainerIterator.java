/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package TennisDatabase;

import java.util.Iterator; // JCF interface
import java.util.NoSuchElementException; // ...

// Iterator class for the tennis player container
class TennisPlayerContainerIterator implements Iterator<TennisPlayer> {

    private TennisPlayerContainerNode root; // Entry Point to container data
    private TennisPlayerQueue queue;

    /**
     * Create a new Tennis Player Container Iterator
     * @param r root node to iterate
     */
    public TennisPlayerContainerIterator(TennisPlayerContainerNode r) {
        this.root = r;
        this.queue = new TennisPlayerQueue();
    }

    /**
     * Returns if queue has next
     * @return if queue has next
     */
    public boolean hasNext() {
        // Check if internal queue is not empty
        return !this.queue.isEmpty();
    }

    /**
     * Returns next player in queue and dequeue it
     * @return next player in queue
     */
    public TennisPlayer next() {
        // Dequeue a player from internal queue, and return it
        try {
            return this.queue.dequeue();
        } catch (TennisDatabaseException e) {
            throw new NoSuchElementException( "No other elements in iterator");
        }
    }

    /**
     * Prepare inorder traversal
     * @throws TennisDatabaseException if there is an error in the traversal
     */
    public void setInorder() throws TennisDatabaseException {
        // Clean the internal queue, ready to be filled up
        this.queue = new TennisPlayerQueue();
        // Call recursive implementation to traverse the BST using inorder strat
        setInorderRec(this.root);
    }

    /**
     *  Recursive implementation for the "setInorder method
     * @param currRoot current root of the traversal
     * @throws TennisDatabaseException if there is an error in the traversal
     */
    private void setInorderRec(TennisPlayerContainerNode currRoot) throws TennisDatabaseException {
        // Check if BST leaf is empty
        if( currRoot == null ) {}
        else {
            setInorderRec(currRoot.getLeftChild()); // Queue Left leaf
            this.queue.enqueue(currRoot.getPlayer()); // Queue Player
            setInorderRec(currRoot.getRightChild()); // Queue Right Leaf
        }
    }

    /**
     * Prepare preorder traversal
     * @throws TennisDatabaseException if there is an error in the traversal
     */
    public void setPreorder() throws TennisDatabaseException {
        // Clean the internal queue, ready to be filled up
        this.queue = new TennisPlayerQueue();
        // Call recursive implementation to traverse the BST using inorder strat
        setPreorderRec(this.root);
    }

    /**
     *  Recursive implementation for the setPreorder method
     * @param currRoot current root of the traversal
     * @throws TennisDatabaseException if there is an error in the traversal
     */
    private void setPreorderRec(TennisPlayerContainerNode currRoot) throws TennisDatabaseException {
        // Check if BST is empty
        if(currRoot == null) {
            // Do nothing
        } else {
            this.queue.enqueue(currRoot.getPlayer()); // Queue player
            setPreorderRec(currRoot.getLeftChild()); // Process left of currRoot
            setPreorderRec(currRoot.getRightChild()); // Process right of currRoot
        }
    }

    /**
     * Prepare postorder traversal
     * @throws TennisDatabaseException if there is an error in the traversal
     */
    public void setPostorder() throws TennisDatabaseException {
        // Clean the internal queue, ready to be filled up
        this.queue = new TennisPlayerQueue();
        // Call recursive implementation to traverse the BST using inorder strat
        setPostorderRec(this.root);
    }

    /**
     *  Recursive implementation for the setPostorder method
     * @param currRoot current root of the traversal
     * @throws TennisDatabaseException if there is an error in the traversal
     */
    private void setPostorderRec(TennisPlayerContainerNode currRoot) throws TennisDatabaseException {
        // Check if BST is empty
        if(currRoot == null) {
            // Do nothing
        } else {
            setPostorderRec(currRoot.getLeftChild()); // Process left of currRoot
            setPostorderRec(currRoot.getRightChild()); // Process right of currRoot
            this.queue.enqueue(currRoot.getPlayer()); // Queue player
        }
    }
}
