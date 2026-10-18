class Solution {
    public int countSubarrays(int[] nums, int maxSum) {
        int subarrays=1;
        long sum=0;
        for(int i=0;i<nums.length;i++){
            if(sum+nums[i]<=maxSum){
                sum+=nums[i];
            }
            else{
                subarrays++;
                sum=nums[i];
            }
        }
        return subarrays;
    }
    public int splitArray(int nums[],int k){
        int low=0;
        int high=0;
        for(int i=0;i<nums.length;i++){
            low=Math.max(low,nums[i]);
            high+=nums[i];
        }
        // Using Binary search 
        while(low<=high){
            int mid=(low+high)/2;
            int required =countSubarrays(nums,mid);
            if(required>k){
                low=mid+1;
            }
            else{
                high=mid-1;
            }
        }
        return low;
    }
}