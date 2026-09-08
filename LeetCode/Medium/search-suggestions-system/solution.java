class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        int left = 0;
        int right = products.length-1;
        for (int i = 0; i<searchWord.length(); i++){
            char c = searchWord.charAt(i);
            while(left<=right && (products[left].length() <= i || products[left].charAt(i) != c)){
                left++;
            }
            while(left<=right && (products[right].length() <= i || products[right].charAt(i) != c)){
                right--;
            }
            List<String> validmatches = new ArrayList<>();
            int windowsize = right-left+1;
            for (int j = 0; j<Math.min(3, windowsize) ;j++){
                validmatches.add(products[left+j]);
            }
            result.add(validmatches);
        }
        return result;
    }
}