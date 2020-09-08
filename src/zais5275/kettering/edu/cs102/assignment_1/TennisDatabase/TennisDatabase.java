/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TennisDatabase implements TennisDatabaseInterface {

    // Create our containers
    TennisPlayerContainer tpc;
    TennisMatchContainer tmc;

    /**
     * Create s new TennisDatabase
     */
    public TennisDatabase() {
        tpc = new TennisPlayerContainer();
        tmc = new TennisMatchContainer();
    }

    /**
     * Load data into the database from a file
     * @param fileName path of the file to load
     * @throws TennisDatabaseException if there is an issue in the algorithms or the like
     * @throws TennisDatabaseRuntimeException if there is an issue in the database
     */
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
                    int birthYear = -1;
                    try {
                        birthYear = Integer.parseInt(split[4]);
                    } catch (NumberFormatException e) {
                        throw new TennisDatabaseException("Couldn't import data: Invalid birth year for " + split[1]);
                    }
                    // Add Player to our list. 1: ID, 2: FirstName, 3: LastName, 4: BirthYear, 5: Country
                    insertPlayer(split[1], split[2], split[3], birthYear, split[5]);
                } else if(split[0].equals("MATCH"))  { // If it's not player, check for match
                        // Parse Date from String
                    int year = -1;
                    int month = -1;
                    int day = -1;
                    try {
                        year = Integer.parseInt(split[3].substring(0,4));
                        month = Integer.parseInt(split[3].substring(4,6));
                        day = Integer.parseInt(split[3].substring(6,8));
                    } catch (NumberFormatException e) {
                        throw new TennisDatabaseException("Couldn't import data: Invalid date for " + split[4]);
                    }
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

    /**
     * Get a player's object from an id
     * @param id Id of player to get
     * @return requested player's object
     * @throws TennisDatabaseRuntimeException if player doesnt exist
     */
    public TennisPlayer getPlayer(String id) throws TennisDatabaseRuntimeException {
        return tpc.getPlayer(id);
    }

    /**
     * Get all players
     * @return array of all players
     * @throws TennisDatabaseRuntimeException if there is some errir whilst getting players
     */
    public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
        return tpc.getAllPlayers();
    }

    /**
     * Get matches of a player
     * @param playerId Player ID
     * @return Array of player's matches
     * @throws TennisDatabaseException if there is an error in the database
     * @throws TennisDatabaseRuntimeException if there is an error in the algorithms used to calculate these things
     */
    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        return tpc.getMatchesOfPlayer(playerId);
    }

    /**
     * Get all matches
     * @return an array of all matches
     * @throws TennisDatabaseRuntimeException if there is an error in the database
     */
    public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
        return tmc.getAllMatches();
    }

    /**
     * Insert player into the database
     * @param id Player's ID
     * @param firstName player's first name
     * @param lastName player's last name
     * @param year player's birth year
     * @param country player's country
     * @throws TennisDatabaseException if there is an error in the calculations needed to create player
     */
    public void insertPlayer(String id, String firstName, String lastName, int year, String country) throws TennisDatabaseException {
        // Create Tennis Player Object
        TennisPlayer newPlayer = new TennisPlayer(id, firstName, lastName, country, year);
        // Insert player into container
        tpc.insertPlayer(newPlayer);
    }

    /**
     * Delete a player and all of their matches
     * @param playerId Player to be deleted
     * @throws TennisDatabaseRuntimeException if there is an error whilst deleting player
     */
    public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {
        tpc.deletePlayer(playerId);
        tmc.deleteMatchesOfPlayer(playerId);
    }

    /**
     * Insert Match into database
     * @param idPlayer1 player 1's id
     * @param idPlayer2 player 2's id
     * @param year match year
     * @param month match month
     * @param day match day
     * @param tournament match name
     * @param score match scores (formatted like: 1-1,2-2,3-3,etc)
     * @throws TennisDatabaseException if there is an error in the calculations needed to insert match
     */
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

    /**
     * Resets the tennis database by reconstructing both containers
     */
    public void resetDatabase() {
        tmc = new TennisMatchContainer();
        tpc = new TennisPlayerContainer();
    }
}
