class Solution {
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Total length must be even
        if ((m + n - 1) % 2 == 1)
            return false;

        dp = new Boolean[m][n][m + n];

        return dfs(grid, 0, 0, 0);
    }

    boolean dfs(char[][] grid, int i, int j, int bal) {

        if (grid[i][j] == '(')
            bal++;
        else
            bal--;

        // Invalid prefix
        if (bal < 0)
            return false;

        // End cell
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return bal == 0;

        // Already calculated
        if (dp[i][j][bal] != null)
            return dp[i][j][bal];

        boolean ans = false;

        // Down
        if (i + 1 < grid.length)
            ans = dfs(grid, i + 1, j, bal);

        // Right
        if (!ans && j + 1 < grid[0].length)
            ans = dfs(grid, i, j + 1, bal);

        return dp[i][j][bal] = ans;
    }
}