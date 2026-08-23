class Solution {
    public String convert(String s, int numRows) {
        if (numRows ==1 || numRows>=s.length()){
            return s;
        }
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i<rows.length;i++){
            rows[i] = new StringBuilder();
        }
        int currentrow=0;
        boolean isgoingdown = false;
        for (char c:s.toCharArray()){
            rows[currentrow].append(c);
            if (currentrow == 0 || currentrow == numRows - 1) {
                isgoingdown = !isgoingdown;
            }
            currentrow += isgoingdown ?1 :-1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows){
            result.append(row);
        }
        return result.toString();
    }
}