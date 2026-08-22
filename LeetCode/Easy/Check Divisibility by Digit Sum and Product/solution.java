class Solution {
    public boolean checkDivisibility(int n) {
        int temp = 0;
        int digitsum = 0;
        int digitproduct = 1;
        while (temp>0){
            int digit = temp%10;
            digit+=digit;
            digitproduct*=digit;
            temp/=10;
        }
        int total = digitsum+digitproduct;
        r
    }
}