/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head==null || head.next==null || head.next.next==null){
            return new int[]{-1,-1};
        }
        int mindistance = Integer.MAX_VALUE;
        int firstcrit = -1;
        int prevcrit = -1;
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;
        while (curr.next != null){
            ListNode next = curr.next;
            if ((curr.val>prev.val && curr.val >next.val) || (curr.val<prev.val && curr.val < next.val)){
                if (firstcrit == -1){
                    firstcrit = index;
                }
                else{
                    mindistance = Math.min(mindistance, index-prevcrit);
                }
                prevcrit = index;
            }
            prev = curr;
            curr = next;
            index++;
        }
        if(mindistance == Integer.MAX_VALUE){
            return new int[]{-1,-1};
        }
        return new int[]{mindistance, prevcrit - firstcrit};
    }
}