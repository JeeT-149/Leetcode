class Solution {
    public int longestValidParentheses(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int maxlen = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i<n;i++){
            if(arr[i]=='('){
                left++;
            }else{
                right++;
            }
            if (left==right){
                if(left*2 > maxlen){
                    maxlen = left*2;
                }
            }
            else if (right>left){
                left = 0;
                right = 0;
            }
        }
        left=0;
        right=0;
        for (int i = n-1; i>=0;i--){
            if(arr[i]==')'){
                right++;
            }else{
                left++;
            }
            if (left==right){
                if(left*2 > maxlen){
                    maxlen = left*2;
                }
            }
            else if (left>right){
                left = 0;
                right = 0;
            }
        }
        return maxlen;
    }
}