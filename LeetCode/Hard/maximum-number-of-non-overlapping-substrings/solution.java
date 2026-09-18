class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        char[] arr = s.toCharArray();
        int n = arr.length;
        for (int i = 0; i<n;i++){
            int c = arr[i]-'a';
            if (first[c] == -1) first[c]=i;
            last[c]=i;
        }
        List<String> res = new ArrayList<>();
        int right = -1;
        for (int i = 0; i<n;i++){
            if (i==first[arr[i]-'a']){
                int newright = getrightlimit (arr,i,first,last);
                if (newright != -1){
                    if(i>right){
                        res.add(s.substring(i,newright+1));
                    }
                    else{
                        res.set(res.size()-1,s.substring(i, newright+1));
                    }
                    right = newright;
                }
            }
        }
        return res;
    }
    private int getrightlimit(char[] arr, int start, int[] first, int[] last){
        int right = last[arr[start] - 'a'];
        for (int i = start; i<= right; i++){
            if (first[arr[i] - 'a'] < start){
                return -1;
            }
            right = Math.max(right, last[arr[i] - 'a']);
        }
        return right;
    }
}