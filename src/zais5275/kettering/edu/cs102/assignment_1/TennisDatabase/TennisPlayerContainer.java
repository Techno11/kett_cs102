package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

// Somehow, this circularly doublely linked list works.
public class TennisPlayerContainer implements TennisPlayerContainerInterface {

    private TennisPlayerContainerNode head;
    private int size = 0;

    // If problems arise, set this variable to true to enable debug prints
    private boolean enDebug = false;

    @Override
    public TennisPlayer getPlayer(String id) throws TennisDatabaseRuntimeException {
        // Check if given ID is null, if so, throw error
        if(id == null) throw new TennisDatabaseRuntimeException("Invalid Player ID!");
        // Go through our linked list and find the player
        for(TennisPlayerContainerNode curr = head; curr != null; curr = curr.getNext()) {
            if(curr.getPlayer().getId().equals(id)) return curr.getPlayer();
        }
        // If we get here, the player with that ID doesnt exist.
        throw new TennisDatabaseRuntimeException("No player with that ID found!");
    }

    // Todo: Consider rewriting this to utilize recursive
    @Override // Add Player, in alphabetical order by ID
    public void insertPlayer(TennisPlayer p) throws TennisDatabaseException {
        // 1st item in list case
        if(head == null) {
            TennisPlayerContainerNode newPlayer = new TennisPlayerContainerNode(p);
            newPlayer.setNext(newPlayer);
            newPlayer.setPrev(newPlayer);
            head = newPlayer;
        } else { // This isn't the first item in our list, lets add it in alphabetical
            // Iterate through our linked list
            int i = 0;
            for(TennisPlayerContainerNode curr = head; curr != null; curr = curr.getNext()) { // Technically, this loop will never exit on its own
                if (enDebug) System.out.println("Comparing " + curr.getPlayer().getId() + " and " + p.getId() + ": " + curr.getPlayer().compareTo(p));
                if(curr.getPlayer().compareTo(p) > 0) {
                    // If we get here, the new player should Be placed before this node, but AFTER the previous
                    if (enDebug) System.out.println("Inserting " + p.getId() + " before " + curr.getPlayer().getId());
                    // Store "Previous" of currently selected player
                    TennisPlayerContainerNode prev = curr.getPrev();
                    // Create New Player
                    TennisPlayerContainerNode newPlayer = new TennisPlayerContainerNode(p); // New Object
                    newPlayer.setNext(curr); // Set Next of new player
                    newPlayer.setPrev(prev); // Set Previous of New Player
                    // Insert Player into List
                    curr.setPrev(newPlayer); // Update "Previous" List Element of Current Element
                    prev.setNext(newPlayer); // Update "Next" item of the previous element
                    if(i == 0) {
                        head = newPlayer; // If Current player is head, update head
                    }
                    break; // We've inserted the player, break.
                } else if(curr.getNext() == head) { // we didnt insert the new player, but we're at the end of the list
                    if (enDebug) System.out.println("End of list. Inserting " + p.getId() + " after " + curr.getPlayer().getId());
                    TennisPlayerContainerNode newPlayer = new TennisPlayerContainerNode(p); // Create new player
                    newPlayer.setPrev(curr); // Set the previous
                    curr.setNext(newPlayer); // Add to list
                    head.setPrev(newPlayer); // Since we added this to the end of the list, we need to update the head
                    break; // We've inserted the player, break.
                }
                i++;
            }
        }
        size++;
        if (enDebug) System.out.println(size);
    }

    @Override
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        getPlayer(m.getIdPlayer1()).insertMatch(m); // Insert match player 1
        getPlayer(m.getIdPlayer2()).insertMatch(m); // Insert match player 2
    }

    @Override
    public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
        TennisPlayer[] players = new TennisPlayer[size];
        TennisPlayerContainerNode tempNode = head;
        for(int i = 0; i < size; i++) {
            players[i] = tempNode.getPlayer();
            tempNode = tempNode.getNext();
        }
        return players;
    }

    @Override
    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        return new TennisMatch[0];
    }
}
