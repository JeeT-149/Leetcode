class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left=0;
        int ones = 0;
        String best = "";
        for (int right = 0; right<s.length();right++){
            if(s.charAt(right)=='1'){
                ones++;
            }
            while(left<=right && (ones>k || s.charAt(left)=='0')){
                if(s.charAt(left)=='1'){
                    ones--;
                }
                left++;
            }
            if(ones==k && s.charAt(right)=='1'){
                String current = s.substring(left,right+1);
                if(best.isEmpty() ||current.length()<best.length() || (current.length()==best.length() && current.compareTo(best)<0)){
                    best=current;
                }
            }
        }
        return best;
    }
}