class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[] ones1 = new int[n*n];
        int[] ones2 = new int[n*n];
        int p1 = 0, p2= 0;
        for (int r =0; r<n;r++){
            for (int c = 0; c<n;c++){
                if(img1[r][c]==1){
                    ones1[p1++]=(r<<6)|c;
                }
                if(img2[r][c]==1){
                    ones2[p2++]=(r<<6)|c;
                }
            }
        }
        int[][] count = new int[2*n][2*n];
        int maxoverlap = 0;
        for (int i = 0; i<p1;i++){
            int r1 = ones1[i]>>6;
            int c1 = ones1[i]&63;
            for (int j = 0; j<p2;j++){
                int r2 = ones2[j]>>6;
                int c2 = ones2[j]&63;
                int shiftr = r1-r2+n;
                int shiftc = c1-c2+n;
                count[shiftr][shiftc]++;
                if(count[shiftr][shiftc] > maxoverlap){
                    maxoverlap = count[shiftr][shiftc];
                }
            }
        }
        return maxoverlap;
    }
}