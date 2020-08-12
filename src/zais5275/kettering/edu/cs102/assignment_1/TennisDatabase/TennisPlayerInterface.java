


// Giuseppe Turini
// CS-102, Summer 2020
// Assignment 1

package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

// Interface (package-private) providing the specifications for the TennisPlayer class.
// See: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
interface TennisPlayerInterface extends Comparable<TennisPlayer> {
   
   // Accessors (getters).
   public String getId();
   public String getFirstName();
   public String getLastName();
   public int getBirthYear();
   public String getCountry();
      
}


