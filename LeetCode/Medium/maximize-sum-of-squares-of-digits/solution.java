class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9*num) return "";
        char[] arr = new char[num];
        int ptr = 0;
        while (sum >= 9){
            arr[ptr++]='9';
            sum -=9;
        }
        if (ptr<sum){
            arr[ptr++]=(char) ('0'+sum);
        }
        while(ptr<num){
            arr[ptr++]='0';
        }
        return new String(arr);
    }
}