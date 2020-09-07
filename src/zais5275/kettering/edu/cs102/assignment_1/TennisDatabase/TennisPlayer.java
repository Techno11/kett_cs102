package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

public class TennisPlayer implements TennisPlayerInterface {

    private String id, firstName, lastName, country;
    private int birthYear, win, loss;


    public TennisPlayer(String id, String firstName, String lastName, String country, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthYear = birthYear;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getCountry() {
        return country;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public int compareTo(TennisPlayer o) {
        return this.getId().compareTo(o.getId());
    }

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
