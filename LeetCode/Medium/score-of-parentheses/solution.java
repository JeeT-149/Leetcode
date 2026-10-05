class Solution {
    public int scoreOfParentheses(String s) {
        char[] arr = s.toCharArray();
        int score = 0;
        int depth = 0;
        for (int i = 0; i<arr.length; i++){
            if (arr[i]=='('){
                depth++;
            }else{
                depth--;
                if(arr[i-1]=='('){
                    score += (1<<depth);
                }
            }
        }
        return score;
    }
}