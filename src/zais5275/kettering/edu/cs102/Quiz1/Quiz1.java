package Quiz1;

public class Quiz1 {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int arr[] = {0,5000,2,7,1,6,4,2,5,8,9,3,99,55,66,4,8,2,1,99,1002,6,5};
        System.out.println(findMaxInArray_it(arr, 3, arr.length - 1));
        System.out.println("Computation for iterative definition took " + (System.nanoTime() - startTime) + " nanoseconds");

        startTime = System.nanoTime();
        System.out.println(findMaxInArray(arr, 3, arr.length - 1));
        System.out.println("Computation for recursive definition took " + (System.nanoTime() - startTime) + " nanoseconds");
    }

    /** Iterative **/
    public static int findMaxInArray_it(int[] values, int first, int last) {
        int max = values[first];
        for(int i = first; i <= last; i++) {
            if(values[i] > max) max = values[i];
        }
        return max;
    }

    /** Recursive **/
    public static int findMaxInArray(int[] values, int first, int last) {
        // Catch Invalid array partition parameters
        if(last > values.length - 1 || first < 0) return -1;

        // We're out of array to search, find max of our current array indices
        if (last - first <= 1) {
            return Integer.max(values[first], values[last]);
        } else { // Still have more array to search

            // Divide Array in half, and search each half for max, recursively
            int mid = (first + last) / 2;
            int firstHalfMax =  findMaxInArray(values, first, mid);
            int secondHalftMax = findMaxInArray(values, mid + 1, last);

            // Find Max
            return Integer.max(firstHalfMax, secondHalftMax);

        }
    }
}
