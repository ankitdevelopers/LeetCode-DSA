class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                low++;
                high++;
            } 
            else if (ch == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;
                high++;
            }

            // Even the maximum possibility has too many ')'
            if (high < 0) {
                return false;
            }

            // Minimum cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        // If 0 is within the possible range, valid
        return low == 0;
    }
}