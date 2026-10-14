class Solution {
    public int canMakeMBouq(int bloomDay[],int mid,int k){
        int bouqCount=0;
        int consecutive_count=0;
        for(int i=0;i<bloomDay.length;i++){
            if(bloomDay[i]<=mid){
                consecutive_count++;
                
                if(consecutive_count==k){
                    bouqCount++;
                    consecutive_count=0;
                }
            }
            else{
                consecutive_count=0;
            }
        }
        return bouqCount;
    }
    public int minDays(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        if((long)m*k>n){
            return -1;
        }
        int low=0;
        int high=0;
        for(int i=0;i<n;i++){
            if(bloomDay[i]>high){
                high=bloomDay[i];
            }
        }
        int mindays=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(canMakeMBouq(bloomDay,mid,k)>=m){
                mindays=mid;
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return mindays;
        
    }
}