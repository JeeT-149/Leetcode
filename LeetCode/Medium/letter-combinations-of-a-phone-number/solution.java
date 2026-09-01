class Solution {
    private static final char[][] KEYPAD = {
        {}, {},
        {'a', 'b', 'c'}, {'d', 'e', 'f'}, 
        {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length()==0) return result;
        char[] path = new char [digits.length()];
        dfs (digits.toCharArray(), 0, path, result);
        return result;
    }
    private void dfs (char[] digits, int index, char[] path, List<String> result){
        if(index == digits.length){
            result.add(new String(path));
            return;
        }
        char[] letters = KEYPAD[digits[index]-'0'];
        for (char c : letters){
            path[index]=c;
            dfs(digits, index+1, path, result);
        }
    }
}