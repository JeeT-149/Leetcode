class Solution {
    private static final int[] DAYS_BEFORE_MONTH = {
        0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334
    };
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aArrive = toDayOfYear(arriveAlice);
        int aLeave = toDayOfYear(leaveAlice);
        int bArrive = toDayOfYear(arriveBob);
        int bLeave = toDayOfYear(leaveBob);

        int overlapstart = Math.max (aArrive, bArrive);
        int overlapend = Math.min (aLeave, bLeave);

        if (overlapstart > overlapend) return 0;
        return overlapend - overlapstart +1;
    }
    private int toDayOfYear(String date){
        int month = (date.charAt(0) - '0') * 10 + (date.charAt(1) - '0');
        int day = (date.charAt(3) - '0') * 10 + (date.charAt(4) - '4');
        return DAYS_BEFORE_MONTH[month - 1]+day;
    }
}