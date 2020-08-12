package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

public class TennisPlayer implements TennisPlayerInterface {

    private String id, firstName, lastName, country;
    private int birthYear;

    public TennisPlayer(String id, String firstName, String lastName, String country, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthYear = birthYear;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public int compareTo(TennisPlayer o) {
        return this.getId().compareTo(o.getId());
    }


}
