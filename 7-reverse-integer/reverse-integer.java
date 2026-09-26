class Solution {
    public int reverse(int x) {

        int a = 0;

        while (x != 0) {

            int lastdigit = x % 10;

            // Check overflow before multiplying by 10
            if (a > Integer.MAX_VALUE / 10 ||
                (a == Integer.MAX_VALUE / 10 && lastdigit > 7)) {
                return 0;
            }

            if (a < Integer.MIN_VALUE / 10 ||
                (a == Integer.MIN_VALUE / 10 && lastdigit < -8)) {
                return 0;
            }

            a = a * 10 + lastdigit;

            x = x / 10;
        }

        return a;
    }
}