class SmallestInfiniteSet {
    private boolean[] addedback;
    private int currentmin;

    public SmallestInfiniteSet() {
        addedback = new boolean[1001];
        currentmin = 1;
    }
    
    public int popSmallest() {
        for (int i = 1; i<currentmin;i++){
            if(addedback[i]){
                addedback[i]= false;
                return i;
            }
        }
        return currentmin++;
    }
    
    public void addBack(int num) {
        if (num<currentmin){
            addedback[num]= true;
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */