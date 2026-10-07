import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        int left = 0;
        int right = 0;

        // Find minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                left++;
            } 
            else if (ch == ')') {

                if (left > 0) {
                    left--;
                } 
                else {
                    right++;
                }
            }
        }

        List<String> ans = new ArrayList<>();

        dfs(s, 0, left, right, ans, new HashSet<>());

        return ans;
    }

    private void dfs(String s, int index,
                     int leftRemove, int rightRemove,
                     List<String> ans,
                     Set<String> visited) {

        // If we already processed this string
        if (!visited.add(s)) {
            return;
        }

        // Check whether string is valid
        if (leftRemove == 0 && rightRemove == 0 && isValid(s)) {
            ans.add(s);
            return;
        }

        // Try removing one parenthesis
        for (int i = index; i < s.length(); i++) {

            // Skip duplicate parentheses
            if (i > index && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            char ch = s.charAt(i);

            // Remove '('
            if (ch == '(' && leftRemove > 0) {

                String next =
                    s.substring(0, i) + s.substring(i + 1);

                dfs(next, i, leftRemove - 1,
                    rightRemove, ans, visited);
            }

            // Remove ')'
            if (ch == ')' && rightRemove > 0) {

                String next =
                    s.substring(0, i) + s.substring(i + 1);

                dfs(next, i, leftRemove,
                    rightRemove - 1, ans, visited);
            }
        }
    }

    // Checks whether parentheses are valid
    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            } 
            else if (ch == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}