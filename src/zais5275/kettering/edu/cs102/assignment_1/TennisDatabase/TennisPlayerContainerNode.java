/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;


class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface {

    private TennisPlayer player;
    private TennisPlayerContainerNode childLeft, childRight;
    private SortedLinkedList<TennisMatch> listMatches;


    /**
     * Creates a new player node
     * @param tp Player object
     */
    public TennisPlayerContainerNode(TennisPlayer tp) {
        player = tp;
        childLeft = null;
        childRight = null;
        this.listMatches = new SortedLinkedList<TennisMatch>();
    }

    /**
     * Get player data
     * @return player object of this node
     */
    public TennisPlayer getPlayer() {
        return player;
    }

    /**
     * Get All matches of this player
     * @return all matches of this player
     */
    public SortedLinkedList<TennisMatch> getMatchList() {
        return listMatches;
    }

    /**
     * Get left child of this node
     * @return left node of this child
     */
    public TennisPlayerContainerNode getLeftChild() {
        return childLeft;
    }

    /**
     * Get right child of this node
     * @return right node of this child
     */
    public TennisPlayerContainerNode getRightChild() {
        return childRight;
    }

    /**
     * Set Player
     * @param p set player object of this node
     */
    public void setPlayer(TennisPlayer p) {
        this.player = p;
    }

    /**
     * Set match list
     * @param ml set match list of this node
     */
    public void setMatchList(SortedLinkedList<TennisMatch> ml) {
        this.listMatches = ml;
    }

    /**
     * Set left child
     * @param lc set left child of this node
     */
    public void setLeftChild(TennisPlayerContainerNode lc) {
        this.childLeft = lc;
    }


    /**
     * Set right child
     * @param rc set right child of this node
     */
    public void setRightChild(TennisPlayerContainerNode rc) {
        this.childRight = rc;
    }

    /**
     * Add match to this player's match list
     * @param m Match to be added
     * @throws TennisDatabaseException if there is an error during insertion
     */
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        //System.out.println("Inserting match " + m.getTournament());
        try {
            player.insertMatch(m); // For Win Loss
            listMatches.insert(m); // Add to list
        } catch (Exception e) {
            throw new TennisDatabaseException("Unable to add match to player's list! " + e.getMessage());
        }
    }

    /**
     * Gets all matches of this player
     * @return All matches of this player
     * @throws TennisDatabaseRuntimeException if there is an error getting matches
     */
    public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException {
        TennisMatch[] matchesArr = new TennisMatch[listMatches.size()];
        for (int i = 0; i < listMatches.size(); i++) {
            matchesArr[i] = listMatches.get(i);
        }
        return matchesArr;
    }

    // TODO:
    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {

    }
}
