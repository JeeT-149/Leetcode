import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i; 
        }
        
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        long[][] dpScore = new long[n + 1][5];
        int[][][] dpIndices = new int[n + 1][5][4];
        int[][] dpSize = new int[n + 1][5];
        
        int[] tempIndices = new int[4];
        
        for (int i = n - 1; i >= 0; i--) {
            int next_j = findNext(arr, arr[i][1]);
            
            for (int k = 1; k <= 4; k++) {
                long skipScore = dpScore[i + 1][k];
                long takeScore = arr[i][2] + dpScore[next_j][k - 1];
                
                if (takeScore > skipScore) {
                    dpScore[i][k] = takeScore;
                    buildTake(arr[i][3], dpIndices[next_j][k - 1], dpSize[next_j][k - 1], dpIndices[i][k]);
                    dpSize[i][k] = dpSize[next_j][k - 1] + 1;
                    
                } else if (skipScore > takeScore) {
                    dpScore[i][k] = skipScore;
                    copy(dpIndices[i + 1][k], dpSize[i + 1][k], dpIndices[i][k]);
                    dpSize[i][k] = dpSize[i + 1][k];
                    
                } else {
                    dpScore[i][k] = takeScore;
                    buildTake(arr[i][3], dpIndices[next_j][k - 1], dpSize[next_j][k - 1], tempIndices);
                    int tempSize = dpSize[next_j][k - 1] + 1;
                    
                    if (compareIndices(tempIndices, tempSize, dpIndices[i + 1][k], dpSize[i + 1][k]) < 0) {
                        copy(tempIndices, tempSize, dpIndices[i][k]);
                        dpSize[i][k] = tempSize;
                    } else {
                        copy(dpIndices[i + 1][k], dpSize[i + 1][k], dpIndices[i][k]);
                        dpSize[i][k] = dpSize[i + 1][k];
                    }
                }
            }
        }
        
        int[] result = new int[dpSize[0][4]];
        copy(dpIndices[0][4], dpSize[0][4], result);
        return result;
    }
    
    private int findNext(int[][] arr, int targetEnd) {
        int left = 0, right = arr.length - 1;
        int ans = arr.length;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid][0] > targetEnd) {
                ans = mid;
                right = mid - 1; 
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    private void buildTake(int newIdx, int[] prevIndices, int prevSize, int[] out) {
        int i = 0;
        boolean inserted = false;
        for (int ptr = 0; ptr < prevSize; ptr++) {
            if (!inserted && newIdx < prevIndices[ptr]) {
                out[i++] = newIdx;
                inserted = true;
            }
            out[i++] = prevIndices[ptr];
        }
        if (!inserted) out[i++] = newIdx;
    }
    
    private int compareIndices(int[] a, int sizeA, int[] b, int sizeB) {
        int min = Math.min(sizeA, sizeB);
        for (int i = 0; i < min; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }
        return Integer.compare(sizeA, sizeB);
    }
    
    private void copy(int[] src, int size, int[] dest) {
        for (int i = 0; i < size; i++) {
            dest[i] = src[i];
        }
    }
}