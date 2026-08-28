class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i<n;i++){
            freq[s.charAt(i)-'a']++;
        }
        int oddcount = 0;
        int midchar = 0;
        for (int i = 0; i<26;i++){
            if (freq[i] % 2 != 0){
                oddcount++;
                midchar = i;
            }
        }
        if (oddcount>1){
            return "";
        }
        int [] halffreq = new int[26];
        for (int i = 0; i<26;i++){
            halffreq[i]=freq[i]/2;
        }
        int halfn = n/2;
        int i = 0;
        while (i < halfn && halffreq[target.charAt(i)-'a']>0){
            halffreq[target.charAt(i)-'a']--;
            i++;
        }
        if (i==halfn){
            StringBuilder forced = new StringBuilder();
            forced.append(target.substring(0,halfn));
            if (n%2 != 0) forced.append((char)(midchar+'a'));
            for (int k = halfn-1; k>=0;k--) forced.append(target.charAt(k));
            String forcedpal = forced.toString();
            if(forcedpal.compareTo(target)>0) return forcedpal;
        }
        for (int j = (i==halfn ? halfn-1 : i) ;j>= 0 ;j--){
            if (j<i) halffreq[target.charAt(j)-'a']++;
            int targetchar = target.charAt(j)-'a';
            for (int c =targetchar+1; c<26; c++){
                if (halffreq[c]>0){
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0,j));
                    sb.append ((char)(c+'a'));
                    halffreq[c]--;
                    for (int k = 0 ; k<26;k++){
                        while(halffreq[k]>0){
                            sb.append((char)(k+'a'));
                            halffreq[k]--;
                        }
                    }
                    if(n%2 != 0){
                        sb.append((char)(midchar+'a'));
                    }
                    for (int k = halfn-1; k>=0;k--){
                        sb.append(sb.charAt(k));
                    }
                    return sb.toString();
                }
            }
        }
        return "";
    }
}