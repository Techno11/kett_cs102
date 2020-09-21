/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package TennisDatabase;

public class TennisPlayer implements TennisPlayerInterface {

    private String id, firstName, lastName, country;
    private int birthYear, win, loss;


    /**
     * Create new TennisPlayer object
     * @param id Player's ID: First 3 digits of last name followed by last 2 digits of birth year
     * @param firstName Player's first name
     * @param lastName Player's last name
     * @param country Player's country
     * @param birthYear Player's birth year
     */
    public TennisPlayer(String id, String firstName, String lastName, String country, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthYear = birthYear;
    }

    /**
     * Get id of player
     * @return return player's id
     */
    public String getId() {
        return id;
    }

    /**
     * Get first name of player
     * @return return player's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name of player
     * @return return player's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get birth year of player
     * @return return player's birth year
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Get country of player
     * @return return player's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get # of matches won
     * @return # of matches won
     */
    public int getWin() {
        return win;
    }

    /**
     * Get # of matches loss
     * @return # of matches loss
     */
    public int getLoss() {
        return loss;
    }

    /**
     * Compare this TennisPlayer object to another
     * @param o player to compare to
     * @return compareTo value between the player IDs
     */
    public int compareTo(TennisPlayer o) {
        return this.getId().compareTo(o.getId());
    }

    /**
     * Add match data to this player's WLT data
     * @param m match to calculate WLT data from
     * @throws TennisDatabaseException if there is an error calculating the WLT from the match
     */
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        if(m.getIdPlayer1().equals(this.id) && m.getWinner() == 1) {  // Player is player 1 in match and they won
            win++;
        } else if (m.getIdPlayer2().equals(this.id) && m.getWinner() == 2) { // Player is player 2 in match and they won
            win++;
        } else if (!m.getIdPlayer1().equals(this.id) && !m.getIdPlayer2().equals(this.id)) { // We're not in this match!
            throw new TennisDatabaseException("Player not in match!");
        } else { // we lost
            loss++;
        }
    }


}
