package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class TennisPlayerContainer implements TennisPlayerContainerInterface {

    private TennisPlayerContainerNode root;
    private int numPlayers = 0;

    /**
     * Get number of players in our database
     * @return Number of players in database
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Get an iterator of our BST
     * @return Returns an iterator object for our BST
     */
    public TennisPlayerContainerIterator iterator() {
        return new TennisPlayerContainerIterator(this.root);
    }


    /**
     * Get a player's information from a given ID
     * @param id A Player's ID
     * @return Player's object, which includes all data we have on them
     * @throws TennisDatabaseRuntimeException if there is an error during the search, most likely due to player not existing
     */
    public TennisPlayer getPlayer(String id) throws TennisDatabaseRuntimeException {
        return getPlayerRec(root, id);
    }


    /**
     * Recursive implementation to get a player with a specific ID from a BST
     * @param currRoot Current Root that we are searching
     * @param id The ID of the player we're searching for
     * @return Player's object, if it exists
     * @throws TennisDatabaseRuntimeException if there is an error during the search, most likely due to player not existing
     */
    private TennisPlayer getPlayerRec(TennisPlayerContainerNode currRoot, String id) throws TennisDatabaseRuntimeException {
        // Base Case, Check if empty
        if(currRoot == null) { // Throw an error if we're at the end of the road, and haven't found anything
            throw new TennisDatabaseRuntimeException("Error looking for player: Does not exist in database. ");
        } else { // Not First node, forward insert left or right subtree
            int compare = currRoot.getPlayer().getId().compareTo(id);
            if(compare > 0) {
                // Search in Right Subtree
                return getPlayerRec(currRoot.getRightChild(), id);
            } else if (compare < 0) {
                // Search in Left Subtree
                return getPlayerRec(currRoot.getLeftChild(), id);
            } else {
                return currRoot.getPlayer(); // Found!
            }
        }
    }

    /**
     * Insert into our BST db
     * @param p Player into our BST
     * @throws TennisDatabaseException if there is an error during the insertion, most likely to be a duplicate entry
     */
    public void insertPlayer(TennisPlayer p) throws TennisDatabaseException {
        // Call recursive implimentation
        this.root = insertPlayerRec(this.root, p);
        numPlayers++;
    }

    /**
     * Recursive implimentation of insertPlayer
     * @param currRoot a BST (currRoot)
     * @param p Player to be inserted
     * @return the updated input BST (represented by it's root node)
     * @throws TennisDatabaseException if there is an error during the insertion, most likely to be a duplicate entry
     */
    private TennisPlayerContainerNode insertPlayerRec(TennisPlayerContainerNode currRoot, TennisPlayer p) throws TennisDatabaseException {
        // Check if empty
        if(currRoot == null) {
            // Input BST empty, create new node to store input player, return it as new root of input BST
            return new TennisPlayerContainerNode(p);
        } else {
            // BST not empty, proceed recursive insert in left/right subtree depending on comparison
            int compartRes = p.compareTo(currRoot.getPlayer());
            // 3-way comparison
            if(compartRes < 0) {
                // Input player is less than player in currRoot, insert input player in left subtree of currRoot
                TennisPlayerContainerNode newLeftChildOfCurrRoot = insertPlayerRec(currRoot.getLeftChild(), p);
                currRoot.setLeftChild(newLeftChildOfCurrRoot);
                return currRoot;
            } else if(compartRes > 0) {
                // Input player is greater than player in currRoot, insert input player in right subtree or currRoot
                TennisPlayerContainerNode newRightChildOfCurrRoot = insertPlayerRec(currRoot.getRightChild(), p);
                currRoot.setRightChild(newRightChildOfCurrRoot);
                return currRoot;
            } else {
                // Error, duplicate ID (Already in BST)
                throw new TennisDatabaseException("Insert failed, ID already stored in database");
            }
        }
    }

    public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {

    }

    public void insertMatch(TennisMatch m) throws TennisDatabaseException {

    }

    /**
     * Returns all players in our BST DB
     * @return A primitive array of all players in our BST database
     * @throws TennisDatabaseRuntimeException If no players are in the database
     */
    public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
        // Throw error is our BST is empty
        if(root == null) throw new TennisDatabaseRuntimeException("Error listing all players: No players in database");
        // If our BST isn't empty, perform inorder Traversal.
        // First, Create an array
        TennisPlayer[] arr = new TennisPlayer[numPlayers];
        // Then, Recursively fill the array
        getAllPlayersRec(root, arr, 0);
        // Finally, Sort it, in case it isn't in alphabetical order
        Arrays.sort(arr, new TennisPlayerIdSort());
        // Return final product
        return arr;
    }

    /**
     * Perform an inOrder traversal of a BST to fill an array
     * @param currRoot Current node to be searching
     * @param arr Array to fill with the contents of the BST
     * @param index index to insert the current node's contents at
     */
    private void getAllPlayersRec(TennisPlayerContainerNode currRoot, TennisPlayer[] arr, int index) {
        if(currRoot == null) { } // We've reached the end of a leaf, do nothing
        else {
            getAllPlayersRec(currRoot.getLeftChild(), arr, index); // Go down left leaf
            while(arr[index] != null) index++; // Find next open place in array
            arr[index]= currRoot.getPlayer(); // Insert into array
            getAllPlayersRec(currRoot.getRightChild(), arr, index); // Go down right leaf
        }
    }

    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        return new TennisMatch[0];
    }

    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {

    }

    /* *********** Sort Class for Array Sorter ************ */
    class TennisPlayerIdSort implements Comparator<TennisPlayer> {
        // Sort in ascending order by ID
        public int compare(TennisPlayer a, TennisPlayer b) {
            // System.out.println(a.getId() + " " + b.getId());
            return a.compareTo(b);
        }
    }
}
