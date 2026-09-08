class Trie {
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isendofword= false;
    }
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index]==null){
                curr.children[index]=new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isendofword = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index]==null){
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isendofword;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()){
            int index = c-'a';
            if(curr.children[index]==null){
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */