class Solution {
    public int maxPalindromes(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int count = 0; 
        int i = 0;
        while(i<=n-k){
            if (ispallindrome(arr, i, i+k-1)){
                count++;
                i+=k;
            }
            else if (i+k<n && ispallindrome(arr,i, i+k)){
                count++;
                i+=k+1;
            }
            else{
                i++;
            }
        }
        return count;
    }
    private boolean ispallindrome(char[] arr, int left,int right){
        while(left<right){
            if(arr[left]!=arr[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}