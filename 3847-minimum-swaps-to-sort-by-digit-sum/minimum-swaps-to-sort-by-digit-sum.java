
import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Store original numbers with their digit sums
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = digitSum(nums[i]);
        }

        // Sort by digit sum, then by number
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        // Map each number to its original index
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            indexMap.put(nums[i], i);
        }

        int swaps = 0;

        // Move each number to its correct position
        for (int i = 0; i < n; i++) {
            int correctIndex = indexMap.get(arr[i][0]);

            if (correctIndex != i) {
                int currentValue = nums[i];

                // Swap nums[i] with the required number
                nums[i] = nums[correctIndex];
                nums[correctIndex] = currentValue;

                // Update original-position mapping
                indexMap.put(nums[correctIndex], correctIndex);
                indexMap.put(nums[i], i);

                swaps++;
            }
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
