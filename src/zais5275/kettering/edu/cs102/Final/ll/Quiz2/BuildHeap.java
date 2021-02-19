package zais5275.kettering.edu.cs102.Final.ll.Quiz2;

public class BuildHeap {

    private static int[] array = {0, 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
    private static int numItems;
    // Driver Code
    public static void main(String args[])
    {
        numItems = array.length;
        // Binary Tree Representation
        // of input array
        //              1
        //           /     \
        //        3         5
        //      /    \     /  \
        //     4      6   13  10
        //    / \    / \
        //   9   8  15 17

        System.out.println(numOfNodesAtLevel(3));
    }

    public static int numOfNodesAtLevel( int level ) {
        return numNodesAtLevelRec(array.length - 1,0, level);
    }

    public static int numNodesAtLevelRec(int currSize, int currLevel, int finalLevel) {
        // How many nodes will be in this level if it's full
        int currLevelIfFull = (int) Math.pow(2, currLevel);
        // Base Case: We've reached the level
        if(currLevel == finalLevel) {
            // If this is greater than 0, we have a full row of values
            if((currSize - currLevelIfFull) > 0) {
                return currLevelIfFull;
            } else { // Otherwise, the row is only partially filled, and thus is the last row.
                return currSize;
            }
        } else { // Recursively Measure next level
            return numNodesAtLevelRec(currSize - currLevelIfFull, currLevel + 1, finalLevel);
        }
    }
}
