class Solution {
        private int ptr = 0;
        private char[] arr;

    public List<String> braceExpansionII(String expression) {
        arr = expression.toCharArray();
        Set<String> resultset = parse();
        List<String> resultlist = new ArrayList<>(resultset);
        Collections.sort(resultlist);
        return resultlist;
    }
    private Set<String> parse(){
        Set<String> unionset = new HashSet<>();
        Set<String> concatset = new HashSet<>();
        concatset.add("");
        while (ptr<arr.length){
            char c = arr[ptr];
            if (c=='{'){
                ptr++;
                Set<String> innerset = parse();
                concatset = cartesianproduct(concatset, innerset);
            }
            else if(c=='}'){
                ptr++;
                break;
            }
            else if (c==','){
                unionset.addAll(concatset);
                concatset = new HashSet<>();
                concatset.add("");
                ptr++;  
            }
            else{
                StringBuilder sb = new StringBuilder();
                while(ptr<arr.length && arr[ptr]>= 'a' && arr[ptr] <= 'z'){
                    sb.append(arr[ptr]);
                    ptr++;
                }
                Set<String> innerset = new HashSet<>();
                innerset.add(sb.toString());
                concatset = cartesianproduct(concatset, innerset);
            }
        }
        unionset.addAll(concatset);
        return unionset;
    }
    private Set<String> cartesianproduct(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
}