package TennisDatabase;
/*
 * @author Soren Zaiser (zais5275)
 * 7Sept2020
 */

public class TennisPlayerQueue implements TennisPlayerQueueInterface {
    private int numPlayersMax;
    private int front;
    private int back;
    private int numPlayers;
    private TennisPlayer[] players;

    /**
     * Creates a new Tennis Player Queue
     */
    public TennisPlayerQueue() {
        numPlayersMax = 4; // Init max players
        players = new TennisPlayer[numPlayersMax];
        front = 0;
        back = this.numPlayersMax -1;
        numPlayers = 0;
    }

    /**
     * Get number of players
     * @return number of players in queue
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Get if queue is empty
     * @return if the queue is empty
     */
    public boolean isEmpty() {
        return numPlayers == 0;
    }

    /**
     * Enqueue a new player
     * @param p player to enqueue
     * @throws TennisDatabaseException if there is an error queueing the player
     */
    public void enqueue(TennisPlayer p) throws TennisDatabaseException {
        if(this.numPlayers == this.numPlayersMax) {
            // Queue Full, resize it
            TennisPlayer[] newArray = new TennisPlayer[numPlayersMax * 2]; // Create new bigger array

            for(int i = 0; i < numPlayers; i++) { // Transfer elements
                // Get Current player
                int currIndex = (this.front + i) % this.numPlayersMax;
                TennisPlayer currPlayer = this.players[currIndex];
                // Copy player to new array
                newArray[i] = currPlayer;
            }
            this.players = newArray; // Set global array to local
            this.numPlayersMax = numPlayersMax * 2; // Increase max players
            this.front = 0; // New Front is at 0
            this.back = numPlayers - 1; // New Back
        }
        // Array not full, perform enqueue
        this.back = (this.back + 1) % this.numPlayersMax; // Back now points to free cell
        this.players[this.back] = p; // Input player at back
        this.numPlayers++;
    }

    /**
     * Dequeues a player
     * @return dequeued player
     * @throws TennisDatabaseException if there is an error dequeueing the player
     */
    public TennisPlayer dequeue() throws TennisDatabaseException {
        if(!isEmpty()) {
            // Circular Array not empty, perform dequeue
            TennisPlayer playerAtFront = this.players[this.front]; // Get front player
            this.players[this.front] = null; // nullify it
            this.front = (this.front + 1) % this.numPlayersMax; // Update front
            this.numPlayers--; // Update current num of players
            return playerAtFront; // Return player at front
        } else {
            throw new TennisDatabaseException("Cannot dequeue player: Queue is empty!");
        }
    }

    /**
     * Peek at front of queue
     * @return first element of queue
     * @throws TennisDatabaseException if there is an empty queue
     */
    public TennisPlayer peek() throws TennisDatabaseException {
        if(isEmpty()) throw new TennisDatabaseException("Cannot Peek on Queue: Queue empty!");
        return players[front]; // If isnt empty, return player at front
    }

}