package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 This container will represent a SORTED, Resizable, Array.
 We Won't sort every time. We INSERT new entry at correct, sorted position.
 **/

class TennisMatchContainer implements  TennisMatchContainerInterface {

    private ArrayList<TennisMatch> matches = new ArrayList<>();

    @Override
    public int getNumMatches() {
        return 0;
    }

    @Override
    public Iterator<TennisMatch> iterator() {
        return null;
    }

    @Override
    public void insertMatch(TennisMatch newMatch) throws TennisDatabaseException {
        matches.add(newMatch); // Add to array
        Collections.sort(matches); // Sort Array
    }

    @Override
    public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
        return (TennisMatch[]) matches.toArray();
    }

    @Override
    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
        return new TennisMatch[0];
    }

    @Override
    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {

    }
}
