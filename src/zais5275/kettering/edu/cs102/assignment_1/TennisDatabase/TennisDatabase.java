package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TennisDatabase implements TennisDatabaseInterface {

    TennisPlayerContainer tpc;
    TennisMatchContainer tmc;

    public TennisDatabase() {
        tpc = new TennisPlayerContainer();
        tmc = new TennisMatchContainer();
    }

    public void loadFromFile(String fileName) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        // Create new Scanner
        try (Scanner scan = new Scanner(new File(fileName))) {
            // Loop through the lines of our input file
            while (scan.hasNextLine()){
                // Split the line on our delimiter, "/"
                String[] split = scan.nextLine().split("/");
                // Check if line is player
                if(split[0].equals("PLAYER")) {
                    // Parse our birth year as an int
                    int birthYear = Integer.parseInt(split[4]);
                    // Add Player to our list. 1: ID, 2: FirstName, 3: LastName, 4: BirthYear, 5: Country
                    insertPlayer(split[1], split[2], split[3], birthYear, split[5]);
                } else if(split[0].equals("MATCH"))  { // If it's not player, check for match
                    // Parse Date from String
                    int year = Integer.parseInt(split[3].substring(0,4));
                    int month = Integer.parseInt(split[3].substring(4,6));
                    int day = Integer.parseInt(split[3].substring(6,8));
                    // Add Match to our list
                    insertMatch(split[1], split[2], year, month, day, split[4], split[5]);
                } else { // If it's neither player nor match, something is wrong with the line.
                    throw new TennisDatabaseException("Couldn't identify player or match from line beginning with \"" + split[0] + "\"");
                }
            }
        }
        catch (IOException e) { // IO Exception thrown from the scanner either having an error finding or reading the file
            throw new TennisDatabaseException("Couldn't read file given through command line!");
        }
    }

    public void saveToFile(String fileName) throws TennisDatabaseException {

    }

    public void reset() {

    }

    public TennisPlayer getPlayer(String id) throws TennisDatabaseRuntimeException {
        return tpc.getPlayer(id);
    }

    public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
        return tpc.getAllPlayers();
    }

    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        return tpc.getMatchesOfPlayer(playerId);
    }

    public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
        return tmc.getAllMatches();
    }

    public void insertPlayer(String id, String firstName, String lastName, int year, String country) throws TennisDatabaseException {
        // Create Tennis Player Object
        TennisPlayer newPlayer = new TennisPlayer(id, firstName, lastName, country, year);
        // Insert player into container
        tpc.insertPlayer(newPlayer);
    }

    public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {

    }

    public void insertMatch(String idPlayer1, String idPlayer2, int year, int month, int day, String tournament, String score) throws TennisDatabaseException {
        // Data Validation Requirements:
        // -Player 1 and 2 HAVE to be in DB. If not, Discard Match and error
        // -Have to be able to process score

        try {
            // Create Tennis Match Object
            TennisMatch newMatch = new TennisMatch(idPlayer1, idPlayer2, tournament, score, year, month, day);
            // Insert Match into Containers
            tmc.insertMatch(newMatch);
            tpc.insertMatch(newMatch);
        } catch(TennisDatabaseRuntimeException e) {
            throw new TennisDatabaseException(e.getMessage());
        }
    }
}
