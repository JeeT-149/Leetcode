class Solution {
    public int minInsertions(String s) {
        char[] arr = s.toCharArray();
        int insertions = 0;
        int rightneeded = 0;
        for (int i = 0; i<arr.length; i++){
            if(arr[i]=='('){
                if (rightneeded % 2 != 0){
                    insertions++;
                    rightneeded--;
                }
                rightneeded += 2;
            }
            else{
                rightneeded--;
                if(rightneeded<0){
                    insertions++;
                    rightneeded=1;
                }
            }
        }
        return insertions+rightneeded;
    }
}