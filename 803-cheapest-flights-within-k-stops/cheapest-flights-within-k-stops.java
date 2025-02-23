class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        HashMap<Integer, HashMap<Integer, Integer>> map = buildGraph(flights);
        
        pq.add(new int[]{src, 0, 0}); 
        Map<Integer, Integer> minStops = new HashMap<>(); 
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int node = poll[0];
            int cost = poll[1];
            int stops = poll[2];
            if (node == dst) return cost;
            
            if (stops > k) continue;
            if (minStops.containsKey(node) && minStops.get(node) < stops) continue;
            minStops.put(node, stops);

            if (!map.containsKey(node)) continue;
            for (Map.Entry<Integer, Integer> entry : map.get(node).entrySet()) {
                int adjNode = entry.getKey();
                int adjCost = entry.getValue();
                
                pq.add(new int[]{adjNode, cost + adjCost, stops + 1});
            }
        }
        return -1;
    }

    private HashMap<Integer, HashMap<Integer, Integer>> buildGraph(int[][] flights) {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            map.putIfAbsent(flight[0], new HashMap<>());
            map.get(flight[0]).put(flight[1], flight[2]);
        }
        return map;
    }
}
