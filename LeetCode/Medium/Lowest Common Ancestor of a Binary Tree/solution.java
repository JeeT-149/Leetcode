class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base cases: if we hit the bottom, or find one of our target nodes
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Search the left and right subtrees
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        
        // If both sides found a target, this current node is the LCA bridge
        if (leftResult != null && rightResult != null) {
            return root;
        }
        
        // Otherwise, pass up whichever side actually found a target
        return leftResult != null ? leftResult : rightResult;
    }
}