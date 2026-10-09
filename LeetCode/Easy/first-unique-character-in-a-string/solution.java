class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()){
            freq.put(c, freq.getOrDefault(c,0) +1);
        }
        int answer = -1;
        for (int i = 0 ; i<s.length() ;i++){
            if(freq.get(s.charAt(i))==1){
                answer = i;
                break;
            }
        }
        return answer;
    }
}