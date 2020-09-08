/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */
package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

public class TennisMatch implements TennisMatchInterface {



    private String idPlayer1, idPlayer2, tournament, score;
    private int dateYear, dateMonth, dateDay, winner;

    /**
     * Create a new Tennis Match object
     * @param idPlayer1 player 1's id
     * @param idPlayer2 player 2's id
     * @param tournament match name
     * @param score match score
     * @param dateYear match year
     * @param dateMonth match month
     * @param dateDay match date
     * @throws TennisDatabaseRuntimeException if there is an error when creating match
     */
    public TennisMatch(String idPlayer1, String idPlayer2, String tournament, String score, int dateYear, int dateMonth, int dateDay) throws TennisDatabaseRuntimeException {
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.tournament = tournament;
        this.score = score;
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.dateDay = dateDay;
        winner = TennisMatchInterface.processMatchScore(score);
    }

    /**
     * Get player 1 id
     * @return player 1 id
     */
    public String getIdPlayer1() {
        return idPlayer1;
    }

    /**
     * get player 2 id
     * @return player 2 id
     */
    public String getIdPlayer2() {
        return idPlayer2;
    }

    /**
     * Get match year
     * @return match year
     */
    public int getDateYear() {
        return dateYear;
    }

    /**
     * Get match month
     * @return match month
     */
    public int getDateMonth() {
        return dateMonth;
    }

    /**
     * Get match day
     * @return match day
     */
    public int getDateDay() {
        return dateDay;
    }

    /**
     * Get Match Name
     * @return match name
     */
    public String getTournament() {
        return tournament;
    }

    /**
     * Get match scores
     * @return match scores
     */
    public String getMatchScore() {
        return score;
    }

    /**
     * Get match winner
     * @return match winner
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Compare matches by date
     * @param o tennis match to compare to
     * @return difference of dates
     */
    public int compareTo(TennisMatch o) {
        int thisDate = dateToInt(this);
        int otherDate = dateToInt(o);
        return thisDate - otherDate;
    }

    /**
     * Convert TennisMatch Year/Month/Day into a long in, Ex 4-23-2002 would become 2002423
     * @param m TennisMatch
     * @return Date, as a big number
     */
    private int dateToInt(TennisMatch m) {
        // Make dates into one long string. Add 0 for month and day if required
        String date = m.getDateYear() + ((m.getDateMonth() < 10)? "0" : "") + m.getDateMonth() + ((m.getDateDay() < 10)? "0" : "") + m.getDateDay();
        return Integer.parseInt(date);
    }
}
