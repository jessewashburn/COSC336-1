import java.util.Arrays;

public class Assignment2Task1 {
    public static void main(String[] args) {
        // Define the sequences
        int[][] sequences = {
                {10, 9, 2, 5, 3, 101, 7, 18},
                {
                        186, 359, 274, 927, 890, 520, 571, 310, 916, 798, 732, 23, 196, 579,
                        426, 188, 524, 991, 91, 150, 117, 565, 993, 615, 48, 811, 594, 303, 191,
                        505, 724, 818, 536, 416, 179, 485, 334, 74, 998, 100, 197, 768, 421,
                        114, 739, 636, 356, 908, 477, 656
                },
                {
                        318, 536, 390, 598, 602, 408, 254, 868, 379, 565, 206, 619, 936, 195,
                        123, 314, 729, 608, 148, 540, 256, 768, 404, 190, 559, 1000, 482, 141, 26,
                        230, 550, 881, 759, 122, 878, 350, 756, 82, 562, 897, 508, 853, 317,
                        380, 807, 23, 506, 98, 757, 247
                }
        };

        // Process each sequence
        for (int i = 0; i < sequences.length; i++) {
            int[] lis = longestIncreasingSubsequence(sequences[i]);
            System.out.println("Sequence " + (i + 1) + ":");
            System.out.println("    Length: " + lis.length);
            System.out.println("    Subsequence: " + Arrays.toString(lis));
            System.out.println();
        }
    }

    /**
     * Computes the longest increasing subsequence of the input array
     * @param nums the input array of integers
     * @return an array representing the longest increasing subsequence
     */
    public static int[] longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }

        // d[i] stores the length of the longest increasing subsequence ending at index i.
        int[] d = new int[n];
        // prev[i] stores the index of the previous element in the subsequence ending at index i.
        int[] prev = new int[n];

        // Initialize: each element is an increasing subsequence of length 1, and no predecessor.
        for (int i = 0; i < n; i++) {
            d[i] = 1;
            prev[i] = -1;
        }

        // Build the d and prev arrays.
        // Process each element nums[i] to build the longest increasing subsequence ending at that index
        for (int i = 0; i < n; i++) {
            // For each element nums[i], examine all elements before it
            for (int j = 0; j < i; j++) {
                // Check two conditions:
                // 1. nums[j] < nums[i]: Determines if adding nums[i] to the subsequence ending at j maintains increasing order.
                // 2. d[j] + 1 > d[i]: Determines if extending the subsequence ending at j by nums[i] results in a longer subsequence than the current known subsequence ending at i
                if (nums[j] < nums[i] && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1; // Update d[i] to be the length of the new longer subsequence ending at i
                    prev[i] = j;     // Set prev[i] to j to record that the best subsequence ending at i comes from extending the subsequence ending at j
                }
            }
        }

        // Find the index of the maximum value in d.
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] > d[maxIndex]) {
                maxIndex = i;
            }
        }

        // Reconstruct the longest increasing subsequence into an array: lis []
        int len = d[maxIndex];
        int[] lis = new int[len];
        int index = maxIndex;
        for (int i = len - 1; i >= 0; i--) {
            lis[i] = nums[index]; // Place the current element in the correct position in the lis array
            index = prev[index]; // Move to the predecessor element
        }
        return lis;
    }
}
