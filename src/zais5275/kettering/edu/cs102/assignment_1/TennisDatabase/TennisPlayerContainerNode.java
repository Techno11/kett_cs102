package TennisDatabase;

class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface {

    private TennisPlayer player;
    private TennisPlayerContainerNode next, prev;
    private SortedLinkedList<TennisMatch> matches;


    public TennisPlayerContainerNode(TennisPlayer tp) {
        player = tp;
        matches = new SortedLinkedList<TennisMatch>();
    }

    @Override
    public TennisPlayer getPlayer() {
        return player;
    }

    @Override
    public TennisPlayerContainerNode getPrev() {
        return prev;
    }

    @Override
    public TennisPlayerContainerNode getNext() {
        return next;
    }

    @Override
    public void setPrev(TennisPlayerContainerNode p) {
        prev = p;
    }

    @Override
    public void setNext(TennisPlayerContainerNode n) {
        next = n;
    }

    @Override
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        try {
            player.insertMatch(m); // For Win Loss
            matches.insert(m); // Add to list
        } catch (Exception e) {
            throw new TennisDatabaseException("Unable to add match to player's list! " + e.getMessage());
        }
    }

    @Override
    public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException {
        TennisMatch[] matchesArr = new TennisMatch[matches.size()];
        for (int i = 0; i < matches.size(); i++) {
            matchesArr[i] = matches.get(i);
        }
        return matchesArr;
    }
}
