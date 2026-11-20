
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();

        int ans = 1;

        for (int x : nums) {

            int prev = dp.getOrDefault(x - 1, 0);
            int same = dp.getOrDefault(x, 0);

            int withoutChange = prev + 1;
            int withChange = same + 1;

            dp.put(x, Math.max(same, withoutChange));

            dp.put(x + 1, Math.max(
                dp.getOrDefault(x + 1, 0),
                withChange
            ));

            ans = Math.max(ans, Math.max(
                dp.get(x), dp.get(x + 1)
            ));
        }

        return ans;
    }
}
