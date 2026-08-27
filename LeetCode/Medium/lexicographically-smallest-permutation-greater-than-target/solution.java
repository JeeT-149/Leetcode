class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for(int i = 0; i<n;i++){
            freq[s.charAt(i)-'a']++;
        }
        int i = 0;
        while(i<n && freq[target.charAt(i) - 'a']>0){
            freq[target.charAt(i)-'a']--;
            i++;
        }
        for (int j = (i==n ? n-1 :i) ;j>=0;j--){
            if(j<i){
                freq[target.charAt(j) -'a']++;
            }
            int targetchar = target.charAt(j)-'a';
            
            for (int c = targetchar+1; c<26;c++){
                if(freq[c]>0){
                StringBuilder sb = new StringBuilder(n);
                sb.append(target.substring(0,j));
                sb.append((char)(c+'a'));
                freq[c]--;
                for (int k = 0; k<26;k++){
                    while(freq[k]>0){
                        sb.append((char) (k+'a'));
                        freq[k]--;
                    }
                }
                return sb.toString();
            }
            }
        }
        return "";
    }
}