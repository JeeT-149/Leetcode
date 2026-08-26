import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    int[] parent;
    double[] weight;
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int maxNodes = equations.size() * 2;
        parent = new int[maxNodes];
        weight = new double[maxNodes];
        
        for (int i = 0; i < maxNodes; i++) {
            parent[i] = i;
            weight[i] = 1.0;
        }        
        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            
            if (!map.containsKey(u)) map.put(u, id++);
            if (!map.containsKey(v)) map.put(v, id++);
            
            union(map.get(u), map.get(v), values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String u = queries.get(i).get(0);
            String v = queries.get(i).get(1);
            if (!map.containsKey(u) || !map.containsKey(v)) {
                result[i] = -1.0;
            } else {
                int idU = map.get(u);
                int idV = map.get(v);
                if (find(idU) != find(idV)) {
                    result[i] = -1.0;
                } else {
                    result[i] = weight[idU] / weight[idV];
                }
            }
        }   
        return result;
    }
    private int find(int i) {
        if (parent[i] != i) {
            int originalParent = parent[i];
            parent[i] = find(parent[i]);
            weight[i] = weight[i] * weight[originalParent]; 
        }
        return parent[i];
    }
    private void union(int i, int j, double val) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            weight[rootI] = val * weight[j] / weight[i];
        }
    }
}