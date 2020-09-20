/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import java.util.Arrays;

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
        return getPlayerRec(root, id).getPlayer();
    }


    /**
     * Recursive implementation to get a player with a specific ID from a BST
     * @param currRoot Current Root that we are searching
     * @param id The ID of the player we're searching for
     * @return Player's object, if it exists
     * @throws TennisDatabaseRuntimeException if there is an error during the search, most likely due to player not existing
     */
    private TennisPlayerContainerNode getPlayerRec(TennisPlayerContainerNode currRoot, String id) throws TennisDatabaseRuntimeException {
        //System.out.println("Searching for " + id + ", comparing to " + ((currRoot != null ) ? currRoot.getPlayer().getId() : "null"));
        // Base Case, Check if empty
        if(currRoot == null) {
            throw new TennisDatabaseRuntimeException("Error looking for player: Does not exist in database. ");
        } else { // Not First node, forward insert left or right subtree
            int compare = currRoot.getPlayer().getId().compareTo(id);
            if(compare > 0) {
                // Search in Right Subtree
                return getPlayerRec(currRoot.getLeftChild(), id);
            } else if (compare < 0) {
                // Search in Left Subtree
                return getPlayerRec(currRoot.getRightChild(), id);
            } else {
                return currRoot; // Found!
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
    private TennisPlayerContainerNode insertPlayerRec( TennisPlayerContainerNode currRoot, TennisPlayer p ) throws TennisDatabaseException{
        // BASE CASE: check if input BST is empty.
        if( currRoot == null ) {
            // Input BST is empty, create new node to store input item, and return it.
            TennisPlayerContainerNode newNode = new TennisPlayerContainerNode(p);
            return newNode;
        }
        else {
            // Input BST NOT empty, forward insert in left OR right subtree (depending on comparison).
            int resultCompare = currRoot.getPlayer().compareTo(p);
            // 3-way comparison.
            if( resultCompare < 0 ) {
                // Insert in right subtree of currRoot.
                TennisPlayerContainerNode newRightChildOfCurrRoot = insertPlayerRec( currRoot.getRightChild(), p );
                currRoot.setRightChild(newRightChildOfCurrRoot);
                return currRoot;
            }
            else if( resultCompare > 0 ) {
                // Insert in left subtree of currRoot.
                TennisPlayerContainerNode newLeftChildOfCurrRoot = insertPlayerRec( currRoot.getLeftChild(), p );
                currRoot.setLeftChild(newLeftChildOfCurrRoot);
                return currRoot;
            }
            else {
                throw new TennisDatabaseException("Error Inserting Player.");
            }
        }
    }

    /**
     * Deletes a player
     * @param playerId id of player to be deleted
     * @throws TennisDatabaseRuntimeException if there is an error deleting player
     */
    public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {
        root = deletePlayerRec(root, playerId);
        numPlayers--;
    }

    /**
     * Recursive implementation of delete
     * @param currRoot current root to be searched
     * @param id id to be deleted
     * @return finalized node
     */
    private TennisPlayerContainerNode deletePlayerRec(TennisPlayerContainerNode currRoot, String id) {
        // Base Case: if tree empty
        if (currRoot == null)  return currRoot;

        // Go down tree
        int compare = currRoot.getPlayer().getId().compareTo(id);
        if (compare < 0) {
            currRoot.setLeftChild(deletePlayerRec(currRoot.getLeftChild(), id));
        } else if (compare > 0) {
            currRoot.setRightChild(deletePlayerRec(currRoot.getRightChild(), id));
        }


        // If key is same as root, root node needs to be deleted
        else {
            // node with one or no child
            if (currRoot.getRightChild() == null) {
                return currRoot.getLeftChild();
            } else if (currRoot.getLeftChild() == null) {
                return currRoot.getRightChild();
            }

            // if we have two children, get next via inorder
            String min = currRoot.getPlayer().getId();
            while (currRoot.getLeftChild() != null)
            {
                min = currRoot.getLeftChild().getPlayer().getId();
                currRoot = currRoot.getLeftChild();
            }

            // Delete next via inorder
            currRoot.setRightChild(deletePlayerRec(currRoot.getRightChild(), currRoot.getPlayer().getId()));
        }

        return root;
    }

    /**
     * Insert a match into the respective players containers
     * @param m Match to be inserted
     * @throws TennisDatabaseException if there is an error during insertion
     */
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        getPlayerRec(root, m.getIdPlayer1()).insertMatch(m); // Get and Insert match for player 1
        getPlayerRec(root, m.getIdPlayer2()).insertMatch(m); // Get and Insert match for player 2
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
        // Then, get an iterator instance
        TennisPlayerContainerIterator iter = this.iterator();
        // Set Inorder
        try {
            iter.setInorder();
        } catch (TennisDatabaseException e) {
            throw new TennisDatabaseRuntimeException("Error getting all players: " + e.getMessage());
        }
        // Move the queue to an array
        int i = 0;
        while(iter.hasNext() && i < numPlayers) {
            arr[i] = iter.next();
            i++;
        }
        // Finally, Sort it, in case it isn't in alphabetical order
        Arrays.sort(arr);
        // Return final product
        return arr;
    }

    /**
     * Gets the matches of a player
     * @param playerId id of player whose matches you want
     * @return Array of matches that player is in
     * @throws TennisDatabaseException If there is an error in the database
     * @throws TennisDatabaseRuntimeException If there is an error calculating
     */
    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        return getPlayerRec(root, playerId).getMatches();
    }

    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {

    }
}
