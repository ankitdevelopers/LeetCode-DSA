class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = firstRange(nums, target);
        int last = lastRange(nums, target);

        return new int[]{first, last};
    }
    public int firstRange(int[] nums, int target) {
        int low=0;
        int high=nums.length-1;
        int ans=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]==target){
                ans=mid;
                high=mid-1; // search in left 
            }
            else if(nums[mid]<target){
                low=mid+1;
            }
            else if (nums[mid]>target){
                high=mid-1;
            }
           
        }
        return ans;
        
    }
    public int lastRange(int nums[], int target){
        int low=0;
        int high=nums.length-1;
        int ans=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]==target){
                ans=mid;
                low=mid+1; // search in right 
            }
            else if(nums[mid]<target){
                low=mid+1;
            }
            else if(nums[mid]>target){
                high=mid-1;
            }
            
        }
        return ans;
    }
    
}