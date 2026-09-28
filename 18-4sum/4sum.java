import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n-3; i++) {

            // Duplicate i skip
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n; j++) {

                // Duplicate j skip
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = j + 1;
                int l = n - 1;

                while (k<l) {
                   long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum<target){
                        k++;
                    }
                    else if(sum>target){
                        l--;
                    }
                   else {
                       List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);

                       result.add(list);

                        k++;
                         l--;

                           while (k < l && nums[k] == nums[k - 1]) {
                                 k++;
                              }

                      while (k < l && nums[l] == nums[l + 1]) {
                       l--;
                   }
                   }
            
               }
                
            }
        }

        return result;
    }
    }
    
