class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean issorted = true;
        char min = s.charAt(0);
        char max = min;
        char prev = min;
        for (int i =1; i<n; i++ ){
            char curr = s.charAt(i);
            if (curr<prev){
                issorted = false;
            }
            if (curr<min){
                min = curr;
            }
            else if (curr>max){
                max = curr;
            }
            prev = curr;
        }
        if (issorted) return 0;
        if (n==2) return -1;
        char first = s.charAt(0);
        char last = s.charAt(n-1);
        if (first == min || last == max) {
            return 1;
        }
        if (s.indexOf(min) == n - 1 && s.lastIndexOf(max) == 0) {            
            return 3;
        }
        return 2;
    }
}