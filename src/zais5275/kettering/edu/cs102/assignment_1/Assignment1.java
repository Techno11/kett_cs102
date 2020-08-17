import TennisDatabase.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment1 {
	private static TennisDatabase tDb;
	private static Scanner input;

	public static void main(String[] args) {
		// Create new scanner object to read console input
		input = new Scanner(System.in);
		// Create a new TennisDatabase
		tDb = new TennisDatabase();

		// No Arguments Given, don't worry about loading file
		if (args.length < 1) {
			System.out.println("No file path specified in command line!");
		} else {
			// argument available, let's load from file
			try {
				tDb.loadFromFile(args[0]);
			} catch (TennisDatabaseException e) {
				// Print Error
			   System.out.println("Error loading file: " + e.getMessage());
			}
		}
		// Create a loop to run and repeat
		while(true) {
			// Print Main Menu
			printMainMenu();
			// Read and switch on user input
			int fromConsole = -1;
			try {
				fromConsole = input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.print("Please enter a number, 1-6! ");
			}
			switch(fromConsole) {
				case 1: {
					printAllPlayers();
					break;
				}
				case 2: {
					printAllPlayersMatches();
					break;
				}
				case 3: {
					printAllMatches();
					break;
				}
				case 4: {
					insertNewPlayer();
					break;
				}
				case 5: {
					insertNewMatch();
					break;
				}
				case 6: {
					System.exit(0);
					break;
				}
				case -1 : { // No Input from console taken
					System.out.println(" Please try again!");
					break;
				}
				default: {
					System.out.println("Invalid option! Please enter an option on the list!");
					break;
				}
			}
		}

	}

	// Prints all of a player's stored matches
	private static void printAllPlayersMatches() {
		System.out.print("Please Enter Player ID: ");
		// Get Input
		String temp = input.next();
		// Check that Player exists
		try{
			tDb.getPlayer(temp);
			// If we get here, the player exists
			for (TennisMatch m : tDb.getMatchesOfPlayer(temp)) {
				printMatch(m);
			}
		} catch (TennisDatabaseRuntimeException e) { // Player doesn't exist
			System.out.print("Player doesn't exist! Try again. ");
			// Recursively Call the function again, to allow input again.
			printAllPlayersMatches();
		} catch (TennisDatabaseException e) {
			System.out.println("Unable to get matches: " + e.getMessage());
		}
	}

	private static void insertNewPlayer() {
		System.out.println("-------------CREATE NEW PLAYER-------------");
		// Array to Store String Inputs. Pre-Filled with Field name for printing
		String[] inputs = {"Player ID", "First Name", "Last Name", "Country"};
		// Variable to store birth year
		int birthYear = 0;

		// Collect String Inputs
		for(int i = 0; i < inputs.length;) {
		    System.out.print("Please Enter " + inputs[i] + ": ");
			// Get Input
            String temp = input.next();
			// Data Validation
			if(temp == null || temp.length() < 1) {
				// Empty or non-existant, try again. Don't advance step
				System.out.println("Did not recognise input \"" + temp + "\". Please try again!");
			} else {
				// Save Data, Go to next input
				inputs[i] = temp;
				i++;
			}
		}
		// Collect Birth Year (Number) input
		while (true) {
		    System.out.print("Please Enter Birth Year: ");
			// Get Input
			String data = input.next();
			// See if we can parse input to int. (Easy way to detect invalidity, rather than scanner's nextInt())
			try {
			    // Store data, break loop
				birthYear = Integer.parseInt(data);
				break;
			} catch (NumberFormatException e) {
			    // Year invalid, don't break loop and prompt again
				System.out.println("Please enter a valid year!");
			}
		}

		// Create new player
        try {
            tDb.insertPlayer(inputs[0], inputs[1], inputs[2], birthYear, inputs[3]);
            System.out.println("Successfully created player " + inputs[0]);
            printPlayer(tDb.getPlayer(inputs[0]));
        } catch (TennisDatabaseException | TennisDatabaseRuntimeException e) {
            System.out.println("Failed to create player: " + e.getMessage());
        }

	}

	private static void insertNewMatch() {
		System.out.println("-------------CREATE NEW MATCH-------------");

		// Variables to Store Inputs
		String[] ids = new String[2];
		StringBuilder scores = new StringBuilder();
		String tournamentName = null;
		int[] dates = new int[3];

		// Collect Player Ids
		for(int i = 1; i < 3; i++) {
			System.out.print("Please Enter Player " + i + " ID: ");
			// Store Data
			String temp = input.next();
			// Check if Player Exists
			if(playerExists(temp)) { // If Player exists, store it
				ids[i-1] = temp;
			} else { // Otherwise, decrease counter and we'll try again
				i--;
			}
		}
		// Collect Match Scores
		System.out.print("Enter scores, one per line. Leave a blank line when finished: ");
		boolean isFirst = true; // have to use, due to issues with Scanner.nextLine
		while(true) {
			// Store Data
			String temp = input.nextLine();
			// Remove any '\n' and '\r' caraige return
			temp = temp.replaceAll("\n", "");
			temp = temp.replaceAll("\r", "");
			// Check if line isn't empty
			if(temp.length() > 0 & !temp.isEmpty()) { // If line isn't empty, add it to string
				scores.append(temp).append(",");
			} else { // Otherwise, Remove last character and break loop
				if(scores.length() > 0) { // If list is not empty
					scores.delete(scores.length() - 1, scores.length());
					break;
				} else if (!isFirst) { // If list is empty, the user needs to put in at least one score
					System.out.println("Please enter at least one score!");
				}
				isFirst = false;
			}
		}
		// Collect Match Date
		String[] words = {"Year", "Month", "Day"}; // Words to use while collecting dates
		for(int i = 0; i < 3; i++) {
			// Start loop. If invalid input, we'll loop back without advancing to the next input
			while(true) {
				System.out.print("Please Enter " + words[i] +" of the Match: ");
				// Store Data
				String temp = input.next();
				// See if we can parse input to int. (Easy way to detect invalidity, rather than scanner's nextInt())
				try {
					dates[i] = Integer.parseInt(temp);
					break; // Break while loop
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid " + words[i] + "!"); // We won't break the while loop, so i won't increase and we'll query the user again
				}
			}
		}
		// Collect Tournament Name
		System.out.print("Please Tournament Name: ");
		// Store Data
		tournamentName = input.next();

		try {
			tDb.insertMatch(ids[0], ids[1], dates[0], dates[1], dates[2], tournamentName, scores.toString());
		} catch (TennisDatabaseException e) {
			System.out.println("Error Creating Match: " + e.getMessage() );
		}
	}

	// Check if player exists
	private static boolean playerExists(String playerId) {
		try {
			tDb.getPlayer(playerId);
			return true;
		} catch (TennisDatabaseRuntimeException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/* ******* Print Operations ********** */

	// Prints all players in Database
	private static void printAllPlayers() {
		// Get Players
		TennisPlayer[] players = tDb.getAllPlayers();
		// Iterate through players and print them
		for(TennisPlayer p : players) {
			printPlayer(p);
		}
	}

	// Prints all Matches in Database
	private static void printAllMatches() {
		// Get Players
		TennisMatch[] matches = tDb.getAllMatches();
		// Iterate through players and print them
		for(TennisMatch m : matches) {
			printMatch(m);
		}
	}

	// Print the Main Menu Options
	private static void printMainMenu() {
		System.out.println("CS-102 Tennis Manager - Available commands:");
		System.out.println("1 --> Print All Players");
		System.out.println("2 --> Print All Matches of a Player");
		System.out.println("3 --> Print All Matches");
		System.out.println("4 --> Insert New Player");
		System.out.println("5 --> Insert New Match");
		System.out.println("6 --> Exit");
		System.out.print("Your Choice? ");
	}

	// Format Player to be printed to console. Alternate should be a toString inside of the TennisPlayer Class
	private static void printPlayer(TennisPlayer p) {
		if(p != null) {
			// Print ID, First Name, Last Name
			System.out.print(p.getId() + ": " + p.getFirstName() + " " + p.getLastName() + ", ");
			// Print Birth Year, Country, W/L Record
			System.out.println(p.getBirthYear() + ", " + p.getCountry() + ", " + p.getWin() + "/" + p.getLoss());
		}
	}

	// Format Player to be printed to console. Alternate should be a toString inside of the TennisPlayer Class
	private static void printMatch(TennisMatch m) {
		// Print Date
		System.out.print(m.getDateYear() + "/" + m.getDateMonth() + "/" + m.getDateDay() + ", ");
		// Print Birth Year, Country, Scores
		System.out.println(formatName(m.getIdPlayer1()) + " - " + formatName(m.getIdPlayer2()) + ", " + m.getTournament() + ", " + m.getMatchScore());
	}

	// Format Player name for Printing
	private static String formatName(String playerId) {
		// Get Player
		TennisPlayer p = tDb.getPlayer(playerId);
		// First letter of first name, and make it uppercase
		char firstLetter = Character.toUpperCase(p.getFirstName().charAt(0));
		// Proper Format
		return firstLetter + "." +  p.getLastName().toUpperCase();
	}
}
