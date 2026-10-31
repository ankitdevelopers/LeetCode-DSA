
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // Destination after entering a cell from:
        // Right direction or Down direction
        int[][] fromRight = new int[m][n];
        int[][] fromDown = new int[m][n];

        // -1 means invalid path
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(fromRight[i], -1);
            java.util.Arrays.fill(fromDown[i], -1);
        }

        // Precompute mirror destinations
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (grid[i][j] == 0) {
                    fromRight[i][j] = i * n + j;
                    fromDown[i][j] = i * n + j;
                } else {

                    // Entered mirror while moving right:
                    // Turn down
                    if (i + 1 < m) {
                        fromRight[i][j] = fromDown[i + 1][j];
                    }

                    // Entered mirror while moving down:
                    // Turn right
                    if (j + 1 < n) {
                        fromDown[i][j] = fromRight[i][j + 1];
                    }
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        // Count paths
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                // Move right
                if (j + 1 < n) {
                    int dest = fromRight[i][j + 1];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] =
                            (dp[r][c] + dp[i][j]) % MOD;
                    }
                }

                // Move down
                if (i + 1 < m) {
                    int dest = fromDown[i + 1][j];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] =
                            (dp[r][c] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
