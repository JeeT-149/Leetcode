# Lowest Common Ancestor of a Binary Tree

**Platform:** LeetCode
**Difficulty:** Medium
**Language:** Java
**Status:** ACCEPTED
**Problem:** [Open problem](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/?envType=study-plan-v2&envId=leetcode-75)
**Topics:** Tree
**Runtime:** Accepted 33 / 33 testcases passed JeeT149 submitted at Aug 22, 2026 00:13 Analysis Solution 👑 Unlock the Full LeetCode Experience Company problems, Ask Leet, and expert editorials — all in one plan. Runtime 17 ms Beats 23.30% Memory 69.90 MB Beats 61.13% 11ms 16ms 21ms 26ms 31ms 0% 20% 40% 60% 11ms 16ms 21ms 26ms 31ms Code Java 1class Solution { 2 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { 3 // Base cases: if we hit the bottom, or find one of our target nodes 4 if (root == null || root == p || root == q) { 5 return root; 6 } 7 8 // Search the left and right subtrees 9 TreeNode leftResult = lowestCommonAncestor(root.left, p, q); 10 TreeNode rightResult = lowestCommonAncestor(root.right, p, q); 11 12 // If both sides found a target, this current node is the LCA bridge 13 if (leftResult != null && rightResult != null) { 14 return root; 15 } 16 17 // Otherwise, pass up whichever side actually found a target 18 return leftResult != null ? leftResult : rightResult; 19 } 20} View more More challenges 2225. Find Players With Zero or One Losses 1676. Lowest Common Ancestor of a Binary Tree IV 2096. Step-By-Step Directions From a Binary Tree Node to Another 0/5
**Memory:** Runtime 17 ms Beats 23.30% Memory 69.90 MB Beats 61.13%

## Problem

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 
Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.


Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


 
Constraints:


	The number of nodes in the tree is in the range [2, 105].
	-109 <= Node.val <= 109
	All Node.val are unique.
	p != q
	p and q will exist in the tree.

## Solution

Source file: [solution.java](./solution.java)

## Complexity

Add your final time and space complexity here if desired.

Synced by LeetSync Pro on 8/22/2026, 12:15:13 AM
