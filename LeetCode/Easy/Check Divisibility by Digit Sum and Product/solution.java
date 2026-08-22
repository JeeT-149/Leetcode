class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int digitsum = 0;
        int digitproduct = 1;
        while (temp>0){
            int digit = temp%10;
            digitsum+=digit;
            digitproduct*=digit;
            temp/=10;
        }
        int total = digitsum+digitproduct;
        return n%total==0;
    }
}