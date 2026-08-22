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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int maxsum = Integer.MIN_VALUE;
        int maxlevel = 1;
        int currentlevel = 1;
        while(!queue.isEmpty()){
            int levelsize = queue.size();
            int currentsum = 0;
            for(int i = 0; i<levelsize; i++){
                TreeNode curr = queue.poll();
                currentsum += curr.val;

                if(curr.left!=null) queue.offer(curr.left);
                if(curr.right!=null) queue.offer(curr.right);
            }
            if(currentsum>maxsum){
                maxsum = currentsum;
                maxlevel = currentlevel;
            }
            currentlevel++;
        }
        return maxlevel;
    }
}