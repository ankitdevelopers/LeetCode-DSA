import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int[][] arr = new int[n][3];

        // Calculate reward for each item
        for (int i = 0; i < n; i++) {
            int free = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free++;
                }
            }

            arr[i][0] = items[i][0];
            arr[i][1] = items[i][1];
            arr[i][2] = free + 1;
        }

        // Sort by price descending
        Arrays.sort(arr, (a, b) -> Integer.compare(b[1], a[1]));

        int NEG = -1000000000;

        int[] dpHigher = new int[budget + 1];
        Arrays.fill(dpHigher, NEG);
        dpHigher[0] = 0;

        int ans = 0;
        int i = 0;

        while (i < n) {

            int price = arr[i][1];

            int[] dpMin = new int[budget + 1];
            Arrays.fill(dpMin, NEG);

            int j = i;

            while (j < n && arr[j][1] == price) {

                int cost = arr[j][1];
                int reward = arr[j][2];

                for (int b = budget; b >= cost; b--) {

                    if (dpHigher[b - cost] != NEG) {
                        dpMin[b] = Math.max(
                            dpMin[b],
                            dpHigher[b - cost] + reward
                        );
                    }

                    if (dpMin[b - cost] != NEG) {
                        dpMin[b] = Math.max(
                            dpMin[b],
                            dpMin[b - cost] + reward
                        );
                    }
                }

                j++;
            }

            for (int b = 0; b <= budget; b++) {
                if (dpMin[b] != NEG) {
                    int total = dpMin[b] + (budget - b) / price;
                    ans = Math.max(ans, total);
                }
            }

            for (int k = i; k < j; k++) {

                int cost = arr[k][1];
                int reward = arr[k][2];

                for (int b = budget; b >= cost; b--) {
                    if (dpHigher[b - cost] != NEG) {
                        dpHigher[b] = Math.max(
                            dpHigher[b],
                            dpHigher[b - cost] + reward
                        );
                    }
                }
            }

            i = j;
        }

        return ans;
    }
}