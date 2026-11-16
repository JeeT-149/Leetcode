class Solution {
    public String digitSum(String s, int k) {
        int len = s.length();
        if (len <= k) return s;
        
        char[] arr = s.toCharArray();
        char[] next = new char[len + 10]; 
        
        while (len > k) {
            int nextLen = 0;
            
            for (int i = 0; i < len; i += k) {
                int sum = 0;
                int end = i + k;
                if (end > len) end = len;
                
                for (int j = i; j < end; j++) {
                    sum += arr[j] - '0';
                }
                
                if (sum == 0) {
                    next[nextLen++] = '0';
                } else {
                    int temp = sum;
                    int digits = 0;
                    while (temp > 0) {
                        digits++;
                        temp /= 10;
                    }
                    
                    int writeIdx = nextLen + digits - 1;
                    temp = sum;
                    while (temp > 0) {
                        next[writeIdx--] = (char) ((temp % 10) + '0');
                        temp /= 10;
                    }
                    nextLen += digits;
                }
            }
            
            char[] tempRef = arr;
            arr = next;
            next = tempRef;
            len = nextLen;
        }
        
        return new String(arr, 0, len);
    }
}