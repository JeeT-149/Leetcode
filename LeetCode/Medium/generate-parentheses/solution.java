class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] arr = new char[2*n];
        backtrack(res, arr, 0, 0, 0, n);
        return res;
    }
    private void backtrack(List<String> res, char[] arr, int index, int open, int close, int n){
        if (index == 2*n){
            res.add(new String(arr));
            return;
        }
        if(open < n){
            arr[index]='(';
            backtrack(res, arr, index+1, open+1, close, n);
        }
        if(close < open){
            arr[index]=')';
            backtrack(res, arr, index+1, open, close+1, n);
        }
    }
}