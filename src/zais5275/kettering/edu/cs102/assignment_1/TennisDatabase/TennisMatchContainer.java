/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


class TennisMatchContainer implements  TennisMatchContainerInterface {

    private ArrayList<TennisMatch> matches = new ArrayList<>();

    /**
     * Get number of matches
     * @return number of matches
     */
    public int getNumMatches() {
        return matches.size();
    }

    /**
     * Get Match iterator
     * @return iterator for matches
     */
    public Iterator<TennisMatch> iterator() {
        return null;
    }

    /**
     * Insert match and sort
     * @param newMatch match to be inserted
     * @throws TennisDatabaseException if there is an error inserting match
     */
    public void insertMatch(TennisMatch newMatch) throws TennisDatabaseException {
        matches.add(newMatch); // Add to array
        Collections.sort(matches); // Sort Array
    }

    /**
     * Get all matches
     * @return array of all matches in DB
     * @throws TennisDatabaseRuntimeException if there is an error converting arrays
     */
    public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
        return matches.toArray(new TennisMatch[0]);
    }

    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
        return new TennisMatch[0];
    }

    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {

    }
}
