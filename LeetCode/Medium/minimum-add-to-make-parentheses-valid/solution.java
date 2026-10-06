class Solution {
    public int minAddToMakeValid(String s) {
        char[] arr = s.toCharArray();
        int openneed = 0;
        int closeneed = 0;
        for (int i = 0; i<arr.length; i++){
            if (arr[i]=='('){
                closeneed++;
            }
            else{
                if(closeneed>0){
                    closeneed--;
                }
                else{
                    openneed++;
                }
            }
        }
        return openneed+closeneed;
    }
}