class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        char[] arr = seq.toCharArray();
        int[] answer = new int[arr.length];
        int depth = 0;
        for (int i = 0; i<arr.length;i++){
            if(arr[i]=='('){
                depth++;
                answer[i]=depth&1;
            }
            else{
                answer[i]=depth&1;
                depth--;
            }
        }
        return answer;
    }
}