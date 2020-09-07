package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

public class TennisMatch implements TennisMatchInterface {



    private String idPlayer1, idPlayer2, tournament, score;
    private int dateYear, dateMonth, dateDay, winner;

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

    public String getIdPlayer1() {
        return idPlayer1;
    }

    public String getIdPlayer2() {
        return idPlayer2;
    }

    public int getDateYear() {
        return dateYear;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public int getDateDay() {
        return dateDay;
    }

    public String getTournament() {
        return tournament;
    }

    public String getMatchScore() {
        return score;
    }

    public int getWinner() {
        return winner;
    }

    public int compareTo(TennisMatch o) {
        int thisDate = dateToInt(this);
        int otherDate = dateToInt(o);
        return thisDate - otherDate;
    }

    // Convert TennisMatch Year/Month/Day into a long in, Ex 4-23-2002 would become 2002423
    // Input: TennisMatch
    // Output: Date, as a big number
    private int dateToInt(TennisMatch m) {
        // Make dates into one long string. Add 0 for month and day if required
        String date = m.getDateYear() + ((m.getDateMonth() < 10)? "0" : "") + m.getDateMonth() + ((m.getDateDay() < 10)? "0" : "") + m.getDateDay();
        return Integer.parseInt(date);
    }
}
