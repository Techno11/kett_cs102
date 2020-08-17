package TennisDatabase;

class TennisPlayerContainer implements TennisPlayerContainerInterface {

    private TennisPlayerContainerNode head;
    private int size = 0;

    private boolean enDebug = false;

    @Override // Get a player
    public TennisPlayer getPlayer(String id) throws TennisDatabaseRuntimeException {
        // Get the container
        return getPlayerContainer(id).getPlayer();
    }

    // Insert a player to our list
    @Override // Add Player, in alphabetical order by ID
    public void insertPlayer(TennisPlayer p) throws TennisDatabaseException {
        // 1st item in list case
        if(head == null) {
            TennisPlayerContainerNode newPlayer = new TennisPlayerContainerNode(p);
            newPlayer.setNext(newPlayer);
            newPlayer.setPrev(newPlayer);
            size++;
            head = newPlayer;
        } else { // This isn't the first item in our list, lets add it in alphabetical
            // Iterate through our linked list
            int i = 0;
            for(TennisPlayerContainerNode curr = head; curr != null; curr = curr.getNext()) { // Technically, this loop will never exit on its own
                if(curr.getPlayer().compareTo(p) > 0) {
                    // If we get here, the new player should Be placed before this node, but AFTER the previous
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
                    size++; // Increase size
                    break; // We've inserted the player, break.
                } else if(curr.getNext() == head) { // we didnt insert the new player, but we're at the end of the list
                    TennisPlayerContainerNode newPlayer = new TennisPlayerContainerNode(p); // Create new player
                    newPlayer.setPrev(curr); // Set the previous
                    newPlayer.setNext(head); // Set Next
                    curr.setNext(newPlayer); // Add to list
                    head.setPrev(newPlayer); // Since we added this to the end of the list, we need to update the head
                    size++; // Incease list size
                    break; // We've inserted the player, break.
                }
                i++;
            }
        }
    }

    @Override // Insert a match
    public void insertMatch(TennisMatch m) throws TennisDatabaseException {
        getPlayerContainer(m.getIdPlayer1()).insertMatch(m); // Insert Match Player 1
        getPlayerContainer(m.getIdPlayer2()).insertMatch(m); // Insert Match Player 2
    }

    @Override // Return a basic array of all players
    public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException {
        TennisPlayer[] players = new TennisPlayer[size];
        TennisPlayerContainerNode tempNode = head;
        for(int i = 0; i < size; i++) {
            players[i] = tempNode.getPlayer();
            tempNode = tempNode.getNext();
        }
        return players;
    }

    @Override // Return all matches a player is in
    public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseException, TennisDatabaseRuntimeException {
        // Check if given ID is null, if so, throw error
        if(playerId == null) throw new TennisDatabaseException("Invalid Player ID!");
        // Go through our linked list and find the player
        for(TennisPlayerContainerNode curr = head; curr != null; curr = curr.getNext()) {
            if(curr.getPlayer().getId().equals(playerId)) {
                return curr.getMatches();
            }
        }
        // If we get here, the player with that ID doesnt exist.
        throw new TennisDatabaseRuntimeException("No player with that ID found!");
    }

    // Get Player Container
    private TennisPlayerContainerNode getPlayerContainer(String id) throws TennisDatabaseRuntimeException {
        // Check if given ID is null, if so, throw error
        if(id == null) throw new TennisDatabaseRuntimeException("Invalid Player ID!");
        // Go through our linked list and find the player
        TennisPlayerContainerNode curr = head;
        for(int i = 0; i < size; i++) {
            if(curr.getPlayer().getId().equals(id)) return curr;
            curr = curr.getNext();
        }
        // If we get here, the player with that ID doesnt exist.
        throw new TennisDatabaseRuntimeException("No player with ID " + id + " found!");
    }
}
