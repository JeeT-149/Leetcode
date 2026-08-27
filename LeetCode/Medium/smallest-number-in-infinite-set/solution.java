class SmallestInfiniteSet {
    private final boolean[] addedBack = new boolean[1002];
    private int pendingCount;
    private int current;

    public SmallestInfiniteSet() {
        current = 1;
        pendingCount = 0;
    }

    public int popSmallest() {
        if (pendingCount > 0) {
            for (int i = 1; i < current; i++) {
                if (addedBack[i]) {
                    addedBack[i] = false;
                    pendingCount--;
                    return i;
                }
            }
        }
        return current++;
    }

    public void addBack(int num) {
        if (num < current && !addedBack[num]) {
            addedBack[num] = true;
            pendingCount++;
        }
    }
}