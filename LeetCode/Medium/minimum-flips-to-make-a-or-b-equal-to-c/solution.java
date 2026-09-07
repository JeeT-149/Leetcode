class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        while(a>0 || b>0 || c>0){
            int bita = a&1;
            int bitb = b&1;
            int bitc = c&1;
            if (bitc==1){
                if (bita == 0 && bitb == 0){
                    flips++;
                }
            }
                else{
                    flips += (bita+bitb);
                }
                a>>>=1;
                b>>>=1;
                c>>>=1;
        }
        return flips;
    }
}