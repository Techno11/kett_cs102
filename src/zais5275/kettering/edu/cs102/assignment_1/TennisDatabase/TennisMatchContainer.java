/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package TennisDatabase;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;


class TennisMatchContainer implements  TennisMatchContainerInterface {

    private LinkedList<TennisMatch> matches = new LinkedList<>();

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

    /**
     * Delete all matches with a specific player in it
     * @param playerId player's matches to be deleted
     * @throws TennisDatabaseRuntimeException
     */
    public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
        LinkedList<Integer> toBeDeleted = new LinkedList<>();
        for(int i = 0; i < matches.size(); i++) { // Get all matches to be deleted
            if(matches.get(i).getIdPlayer1().equals(playerId) || matches.get(i).getIdPlayer2().equals(playerId)) toBeDeleted.add(i);
        }
        for(int i = 0; i < toBeDeleted.size(); i++) { // Delete matches
            matches.remove(toBeDeleted.get(i) - i);
        }
    }
}
