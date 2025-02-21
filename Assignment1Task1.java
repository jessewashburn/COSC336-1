import java.util.Random;

public class Assignment1Task1 {
    public static void main(String[] args) {
        // Define the sequences
        int[][] sequences = {
                {2, 5, 5, 1, 11, 12, 13, 3, 52, 53, 53, 54, 4, 7},
                {1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 9, 7, 7, 7, 7, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2},
                generateRandomSequence(4000) // Generate a pseudo-random sequence of 4000 bits
        };

        // Process each sequence
        for (int i = 0; i < sequences.length; i++) {
            int maxLength = findLongestSubsequence(sequences[i]);
            System.out.println("Sequence " + (i + 1) + ":");
            System.out.println("    Longest subsequence of numbers that increase by at most 1 is: " + maxLength);
            System.out.println();
        }
    }

    /**
     * Finds the length of the longest subsequence where each element increases by at most 1.
     * Uses dynamic programming with an array d to store subproblem results.
     */
    public static int findLongestSubsequence(int[] nums) {
        int n = nums.length;
        int[] d = new int[n]; // Array to store the lengths of subsequences
        d[0] = 1; // The first element is at least a subsequence of length 1

        int maxLength = 1; // Initialize the maximum length to 1

        for (int i = 1; i < n; i++) {
            // Check if the current element increases by between 0 and 1
            if (0 <= nums[i] - nums[i - 1] && nums[i] - nums[i - 1] <= 1) {
                d[i] = d[i - 1] + 1; // Extend the subsequence
            } else {
                d[i] = 1; // Start a new subsequence
            }

            // Update maxLength if the current subsequence is longer
            if (d[i] > maxLength) {
                maxLength = d[i];
            }
        }

        return maxLength;
    }

     /**
        Helper method to generate a pseudo-random sequence of 0s and 1s
        @param length the length of the desired sequence
     */
    public static int[] generateRandomSequence(int length) {
        Random random = new Random();
        int[] sequence = new int[length];
        for (int i = 0; i < length; i++) {
            sequence[i] = random.nextInt(2); // Generates 0 or 1
        }
        return sequence;
    }
}