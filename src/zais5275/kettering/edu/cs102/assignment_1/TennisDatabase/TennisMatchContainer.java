package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

/**
 This container will represent a SORTED, Resizable, Array.
 We Won't sort every time. We INSERT new entry at correct, sorted position.
 **/

public class TennisMatchContainer implements  TennisMatchContainerInterface {

    private TennisMatch[] matches = new TennisMatch[0];

    @Override
    public void insertMatch(TennisMatch newMatch) throws TennisDatabaseException {
        TennisMatch[] newArray = new TennisMatch[matches.length + 1];

        // If original array has no entries
        if(matches.length == 0) {
            // Set First Entry
            newArray[0] = newMatch;
        } else { // Every other entry to our array
            int i = 0;
            boolean insertedNewMatch = false;
            for(TennisMatch match: matches) {
                // If the new match hasn't been inserted yet, and the current `match` is more recent
                if(!insertedNewMatch && match.compareTo(newMatch) > 0) {
                    newArray[i] = match;
                } else if(!insertedNewMatch && match.compareTo(newMatch) < 0) { // If new match hasn't been inserted yet, and the current `match` is less recent
                    newArray[i] = newMatch; // Insert new Match
                    i++; // Increase array
                    newArray[i] = match;
                    insertedNewMatch = true;
                } else if(insertedNewMatch) { // Rest of the list is in order, because we built it
                    newArray[i] = match;
                }
                i++;
            }
            // Check if last element is null, if so, we populate it with our new match
            if(newArray[newArray.length - 1] == null) {
                newArray[newArray.length - 1] = newMatch;
            }
        }
        matches = newArray;
    }

    @Override
    public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
        return matches;
    }

    @Override
    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
        return new TennisMatch[0];
    }
}
