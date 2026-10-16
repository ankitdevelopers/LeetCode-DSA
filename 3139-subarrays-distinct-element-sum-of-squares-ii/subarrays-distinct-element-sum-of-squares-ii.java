
import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    long[] sum, sq, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = last.getOrDefault(x, -1);

            update(1, 0, n - 1, prev + 1, i);

            ans = (ans + sq[1]) % MOD;
            last.put(x, i);
        }

        return (int) ans;
    }

    void apply(int node, int start, int end) {
        long len = end - start + 1L;

        sq[node] = (sq[node] + 2L * sum[node] % MOD
                    + len) % MOD;

        sum[node] = (sum[node] + len) % MOD;
        lazy[node] = (lazy[node] + 1) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = (start + end) / 2;

        applyChild(node * 2, start, mid, lazy[node]);
        applyChild(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }

    void applyChild(int node, int start, int end, long add) {
        long len = end - start + 1L;

        sq[node] = (sq[node] + 2L * add % MOD * sum[node] % MOD
                    + len % MOD * add % MOD * add % MOD) % MOD;

        sum[node] = (sum[node] + len % MOD * add) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }

    void update(int node, int start, int end, int l, int r) {
        if (l > end || r < start) {
            return;
        }

        if (l <= start && end <= r) {
            apply(node, start, end);
            return;
        }

        push(node, start, end);

        int mid = (start + end) / 2;

        update(node * 2, start, mid, l, r);
        update(node * 2 + 1, mid + 1, end, l, r);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
