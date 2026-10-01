# Valid Parentheses

**Platform:** LeetCode
**Difficulty:** Easy
**Language:** Java
**Status:** ACCEPTED
**Problem:** [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
**Topics:** String, Stack, Bracket Sequences
**Patterns (inferred):** Monotonic Stack
**Runtime:** Accepted Runtime: 0 ms

---

## Problem Statement

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

	- Open brackets must be closed by the same type of brackets.

	- Open brackets must be closed in the correct order.

	- Every close bracket has a corresponding open bracket of the same type.

 

**Example 1:**

**Input:** s = "()"

**Output:** true

**Example 2:**

**Input:** s = "()[]{}"

**Output:** true

**Example 3:**

**Input:** s = "(]"

**Output:** false

**Example 4:**

**Input:** s = "([])"

**Output:** true

**Example 5:**

**Input:** s = "([)]"

**Output:** false

 

**Constraints:**

	- `1 <= s.length <= 10^4`

	- `s` consists of parentheses only `'()[]{}'`.

---

**Solution:** [`solution.java`](./solution.java)
