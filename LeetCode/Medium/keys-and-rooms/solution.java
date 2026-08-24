class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms,0,visited);
        for (boolean v : visited){
            if(!v) return false;
        }
        return true;
    }
    private void dfs (List<List<Integer>> rooms, int currentroom, boolean[] visited){
        visited[currentroom]=true;
        for (int key : rooms.get(currentroom)){
            if(!visited[key]){
                dfs(rooms,key,visited);
            }
        }
    }
}