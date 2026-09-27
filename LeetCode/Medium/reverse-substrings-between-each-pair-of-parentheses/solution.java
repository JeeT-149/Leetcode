class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] pair = new int[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i<n;i++){
            if (arr[i]=='('){
                stack[++top]=i;
            }
            else if (arr[i]==')'){
                int j = stack[top--];
                pair[i]=j;
                pair[j]=i;
            }
        }
        char[] res = new char[n];
        int reslen = 0;
        int i = 0;
        int direction = 1;
        while(i>=0 && i<n){
            if (arr[i]=='(' || arr[i]==')'){
                i = pair[i];
                direction = -direction;
            }
            else {
                res[reslen++]=arr[i];
            }
            i+=direction;
        }
        return new String(res, 0, reslen);
    }
}