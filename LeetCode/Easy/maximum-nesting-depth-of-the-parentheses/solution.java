class Solution {
    public int maxDepth(String s) {
        char[] arr = s.toCharArray();
        int maxdepth = 0;
        int currentdepth = 0;
        for (int i = 0; i< arr.length;i++){
            if(arr[i]=='('){
                currentdepth++;
                if (currentdepth > maxdepth){
                    maxdepth = currentdepth;
                }
                
            }
            else if(arr[i]==')'){
                currentdepth--;
            }
        }
        return maxdepth;
    }
}