class StockSpanner {
    private int[] prices;
    private int[] spans;
    private int top;

    public StockSpanner() {
        prices = new int [10000];    
        spans = new int [10000];  
        top = -1;  
    }
    
    public int next(int price) {
        int currentspan = 1;
        while(top>= 0 && prices[top]<=price){
            currentspan += spans[top];
            top--;
        }
        top++;
        prices[top]=price;
        spans[top]=currentspan;
        return currentspan;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */