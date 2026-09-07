class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        char[] chars = s.toCharArray();
        long[] endwith = new long[26];
        long total = 1;
        for (char c : chars){
            int idx = c-'a';
            long oldtotal = total;
            total = (total*2-endwith[idx]+MOD)%MOD;
            endwith[idx]=oldtotal;
        }
        return (int) ((total-1+MOD)%MOD);
    }
}