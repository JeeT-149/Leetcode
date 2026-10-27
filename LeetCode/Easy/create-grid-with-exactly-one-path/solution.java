class Solution {
    public String[] createGrid(int m, int n) {
       String[] res = new String[m];
       char[] row = new char[n];
       for (int i = 0; i<n; i++){
        row[i]='.';
       }
       res[0]=new String(row);
       if (m>1){
        for (int i = 0; i<n-1; i++){
            row[i]='#';
        }
        row[n-1]='.';
        String cachedrow = new String(row);
        for (int i = 1; i<m; i++){
            res[i]=cachedrow;
        }
       }
       return res;
    }
}