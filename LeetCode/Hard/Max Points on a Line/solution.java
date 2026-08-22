class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n<=2){
            return n;
        }
        int maxpoints = 0;
        for (int i = 0;i<n;i++){
            Map<String,Integer> slopeMap = new HashMap<>();
            int currentmax = 0;
            for (int j = i+1;j<n;j++){
                int dx = points[j][0]-points[i][0];
                int dy = points[j][1]-points[i][1];
                int gcd = generateGCD(dx,dy);
                dx /= gcd;
                String normalizedSlope = (dy/gcd)+","+dx;
                slopeMap.put(normalizedSlope, slopeMap.getOrDefault(normalizedSlope,0)+1);
                currentmax = Math.max(currentmax, slopeMap.get(normalizedSlope));
            }
            maxpoints = Math.max(maxpoints, currentmax+1);
        }
        return maxpoints;
    }
    private int generateGCD(int a,int b){
        if (b==0){
            return a;
        }
        return generateGCD(b, a%b);
    }
}