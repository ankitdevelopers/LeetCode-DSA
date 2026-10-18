class Solution{
    public double findMedianSortedArrays(int nums1[],int nums2[]){
        int m=nums1.length;
        int n=nums2.length;
        int arr[]=new int[m+n];
        int i=0, j=0, k=0;
        
        // Merge both Sorted arrays
        while(i<m && j<n){
            if(nums1[i] < nums2[j]){
                arr[k++]=nums1[i++];
            }
            else{
                arr[k++]=nums2[j++];
            }
            
        }
        
        // Copy remaining elements of array nums1 && nums2
        while(i<m){
            arr[k++]=nums1[i++];
        }
        while(j<n){
            arr[k++]=nums2[j++];
        }
        int p=m+n;
        // Calculate the Median
        if(p%2==1){
            return arr[p/2];    //If median will be ODD 
        }
        else{
            int mid1=arr[p/2-1];
            int mid2=arr[p/2];
            return (mid1+mid2)/2.0;
        }
    }
}