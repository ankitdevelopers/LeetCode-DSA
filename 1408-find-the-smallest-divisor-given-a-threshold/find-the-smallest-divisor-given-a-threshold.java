
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {

        int low = 1;
        int high = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > high) {
                high = nums[i];
            }
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = 0;

            for (int i = 0; i < nums.length; i++) {
                int result = (nums[i] + mid - 1) / mid;
                sum = sum + result;
            }

            if (sum <= threshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
