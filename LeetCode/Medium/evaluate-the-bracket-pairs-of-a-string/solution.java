class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge){
            map.put(pair.get(0), pair.get(1));
        }
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int n = arr.length;
        int i = 0;
        while(i<n){
            if(arr[i]=='('){
                int start = i+1;
                while(arr[i] != ')'){
                    i++;
                }
                String key = new String(arr, start, i-start);
                sb.append(map.getOrDefault(key, "?"));
            }
            else{
                sb.append(arr[i]);
            }
            i++;
        }
        return sb.toString();
    }
}