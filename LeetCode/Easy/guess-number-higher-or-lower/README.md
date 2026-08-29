# Guess Number Higher or Lower

**Platform:** LeetCode
**Difficulty:** Easy
**Language:** Java
**Status:** ACCEPTED
**Problem:** [Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower/)
**Topics:** Binary Search, Interactive
**Patterns (inferred):** Binary Search
**Runtime:** Sakhawat Hossain Mridul Nov 16, 2022 i did not understand the question properly,can any one help to understand this question? Ask Question 311 Show 11 Replies Reply sopheary Jul 19, 2023 This question is difficult to understand than solving. 😂 248 Show 8 Replies Reply Abdullah Nov 16, 2022 I believer the phrasing of the problem description could be better. It feels exactly the opposite of what the problem intends actually. Feedback 158 Show 2 Replies Reply Ashutosh Rattan Nov 16, 2022 In case its confusing, as it was for me intially :- You are given a function guessNumber() that you are to complete. Given a upper bound n this function should guess the correct number picked between 1 and n. To help you with the guessing you are provided with an API guess(). You pass in the value that you have guessed and this function will return 3 values depending upon whether the guess is right or wrong : -1 if the number guessed is higher than the picked number 1 if the number guessed is lower than the picked number otherwise return 0 when the number guessed is equal to picked number. Based upon this you're to formulate a strategy so that you can zero in on the picked number. Happy leetcoding :) . . . . . . . . . Hint :- try binary search maybe? Read more Tip 117 Show 6 Replies Reply RuntimeTerror Apr 01, 2023 what the hell is this question... 75 Show 1 Replies Reply Nakanu Jul 13, 2016 -1 : My number is lower 1 : My number is higher 0 : Congrats! You got it! Here "My" means the number which is given for you to guess not the number you put into guess(int num). 315 Show 1 Replies Reply Aavash Kuikel Jun 19, 2023 Misleading problem statement. Needs to be fixed asap! Feedback 50 Reply Heng Harry Zhou Jan 20, 2021 Any body feel the guess() function logic is reversed? 35 Show 1 Replies Reply It's a secret Jul 22, 2016 It must be said , there is a trap in the question. A description used is difficult to understand. It said: 'return -1 if my number is lower, 1 if my number is higher, otherwise return 0' At frist ,I thought it means if the target num is 10 ,guess(3) would return -1 ，but it got wrong result ,then I found it return 1 actually. So the description is said by the dealer ,we are players. It almost wasted half of this night.I hope I can help somebody to save a little time 32 Show 4 Replies Reply Vivek Kumar Agrawal Nov 16, 2022 I was calling guessNumber intead of guess function so please do not repeate same mistake again 27 Show 1 Replies Reply

---

## Problem Statement

We are playing the Guess Game. The game is as follows:

I pick a number from `1` to `n`. You have to guess which number I picked (the number I picked stays the same throughout the game).

Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

You call a pre-defined API `int guess(int num)`, which returns three possible results:

	- `-1`: Your guess is higher than the number I picked (i.e. `num > pick`).

	- `1`: Your guess is lower than the number I picked (i.e. `num < pick`).

	- `0`: your guess is equal to the number I picked (i.e. `num == pick`).

Return *the number that I picked*.

 

**Example 1:**

```
**Input:** n = 10, pick = 6
**Output:** 6
```

**Example 2:**

```
**Input:** n = 1, pick = 1
**Output:** 1
```

**Example 3:**

```
**Input:** n = 2, pick = 1
**Output:** 1
```

 

**Constraints:**

	- `1 <= n <= 2^31 - 1`

	- `1 <= pick <= n`

---

**Solution:** [`solution.java`](./solution.java)
