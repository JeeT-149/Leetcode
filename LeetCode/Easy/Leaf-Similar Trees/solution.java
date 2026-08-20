/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        getleaves (root1, leaves1);
        getleaves (root2, leaves2);
        return leaves1.equals(leaves2);
    }
    public void getleaves (TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        if(node.left==null && node.right==null){
            list.add(node.val);
            return;
        }
        getleaves(node.left,list);
        getleaves(node.right,list);
    }
}