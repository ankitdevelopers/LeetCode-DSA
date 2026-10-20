
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int maxPrev = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int drops = 0;
                int minBlock = Integer.MAX_VALUE;
                int maxBlock = Integer.MIN_VALUE;

                for (int j = start; j < start + k; j++) {
                    minBlock = Math.min(minBlock, nums[j]);
                    maxBlock = Math.max(maxBlock, nums[j]);

                    int next = (j + 1 == start + k)
                               ? start : j + 1;

                    if (nums[j] > nums[next]) {
                        drops++;
                    }
                }

                if (drops > 1 || maxPrev > minBlock) {
                    valid = false;
                    break;
                }

                maxPrev = Math.max(maxPrev, maxBlock);
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}
