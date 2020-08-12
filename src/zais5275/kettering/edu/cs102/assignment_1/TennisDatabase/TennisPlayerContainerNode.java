package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

public class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface {

    private TennisPlayer player;
    private TennisPlayerContainerNode next, prev;

    public TennisPlayerContainerNode(TennisPlayer tp) {
        player = tp;
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

    }

    @Override
    public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException {
        return new TennisMatch[0];
    }
}
