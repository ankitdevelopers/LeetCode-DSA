
class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                open++;
            } else {
                // If the next character is also ')',
                // we have a pair of closing brackets.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to complete the pair.
                    insertions++;
                }

                // A pair of ')' needs one matching '('.
                if (open > 0) {
                    open--;
                } else {
                    // Insert a missing '('.
                    insertions++;
                }
            }
        }

        // Every unmatched '(' needs two closing brackets.
        insertions += open * 2;

        return insertions;
    }
}