class Solution {
    public boolean checkValidString(String s) {
        char[] arr = s.toCharArray();
        int cmin = 0;
        int cmax = 0;
        for (int i = 0; i<arr.length; i++){
            char c = arr[i];
            if (c == '('){
                cmin++;
                cmax++;
            }
            else if (c == ')'){
                cmin--;
                cmax--;
            }
            else if (c == '*'){
                cmax++;
                cmin--;
            }
            if (cmax<0) return false;
            if (cmin<0){
                cmin = 0;
            }
        }
        return cmin == 0;
    }
}