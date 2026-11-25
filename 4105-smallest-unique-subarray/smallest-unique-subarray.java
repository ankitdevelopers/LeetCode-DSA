
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long mod1 = 1000000007L;
        long mod2 = 1000000009L;
        long base = 911382L;

        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];

        long[] hash1 = new long[n + 1];
        long[] hash2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        // Build prefix hashes
        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * base) % mod1;
            pow2[i + 1] = (pow2[i] * base) % mod2;

            hash1[i + 1] =
                (hash1[i] * base + nums[i]) % mod1;

            hash2[i + 1] =
                (hash2[i] * base + nums[i]) % mod2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        // Binary Search
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isUnique(mid, n, hash1, hash2,
                         pow1, pow2, mod1, mod2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean isUnique(
        int len,
        int n,
        long[] hash1,
        long[] hash2,
        long[] pow1,
        long[] pow2,
        long mod1,
        long mod2
    ) {
        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            long h1 = (hash1[i + len]
                    - hash1[i] * pow1[len] % mod1
                    + mod1) % mod1;

            long h2 = (hash2[i + len]
                    - hash2[i] * pow2[len] % mod2
                    + mod2) % mod2;

            long key = (h1 << 32) | h2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
