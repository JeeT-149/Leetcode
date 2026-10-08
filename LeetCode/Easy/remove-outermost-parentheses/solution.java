class Solution {
    public String removeOuterParentheses(String s) {
        char[] arr = s.toCharArray();
        char[] res = new char[arr.length];
        int ptr = 0;
        int depth = 0;
        for (int i = 0; i < arr.length; i++){
            if(arr[i]=='('){
                if(depth>0) res[ptr++]=arr[i];
                depth++;
            }
            else{
                depth--;
                if(depth>0) res[ptr++]=arr[i];
            }
        }
        return new String(res, 0, ptr);
    }
}