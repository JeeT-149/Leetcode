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
    private int matchingnodes = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingnodes;    
    }
    private long dfs(TreeNode node){
        if(node == null) return 0L;
        long left = dfs(node.left);
        long right = dfs(node.right);
        int leftsum = (int) (left>>32);
        int leftcount = (int) left;
        int rightsum = (int) (right>>32);
        int rightcount = (int) right;
        int currentsum = leftsum+rightsum+node.val;
        int currentcount = leftcount+rightcount+1;
        if (currentsum/currentcount == node.val) matchingnodes++;
        return ((long) currentsum<<32) | currentcount;
    }
}