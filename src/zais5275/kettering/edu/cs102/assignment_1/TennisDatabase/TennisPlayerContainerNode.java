package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface {

    private TennisPlayer player;
    private TennisPlayerContainerNode childLeft, childRight;
    private SortedLinkedList<TennisMatch> listMatches;


    public TennisPlayerContainerNode(TennisPlayer tp) {
        player = tp;
        this.listMatches = new SortedLinkedList<TennisMatch>();
        this.childLeft = null;
        this.childRight = null;
    }

    public TennisPlayer getPlayer() {
        return player;
    }

    public SortedLinkedList<TennisMatch> getMatchList() {
        return listMatches;
    }

    public TennisPlayerContainerNode getLeftChild() {
        return childLeft;
    }

    public TennisPlayerContainerNode getRightChild() {
        return childRight;
    }

    public void setPlayer(TennisPlayer p) {
        this.player = p;
    }

    public void setMatchList(SortedLinkedList<TennisMatch> ml) {
        this.listMatches = ml;
    }

    public void setLeftChild(TennisPlayerContainerNode lc) {
        this.childLeft = lc;
    }

    public void setRightChild(TennisPlayerContainerNode rc) {
        this.childRight = rc;
    }

    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        try {
            player.insertMatch(m); // For Win Loss
            listMatches.insert(m); // Add to list
        } catch (Exception e) {
            throw new TennisDatabaseException("Unable to add match to player's list! " + e.getMessage());
        }
    }

    public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException {
        TennisMatch[] matchesArr = new TennisMatch[listMatches.size()];
        for (int i = 0; i < listMatches.size(); i++) {
            matchesArr[i] = listMatches.get(i);
        }
        return matchesArr;
    }

    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {

    }
}
