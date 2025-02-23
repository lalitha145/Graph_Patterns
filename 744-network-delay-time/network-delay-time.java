import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build the adjacency list
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int[] a : times) {
            map.putIfAbsent(a[0], new HashMap<>());
            map.get(a[0]).put(a[1], a[2]);
        }

        // Step 2: Min-Heap (Priority Queue) sorted by time (ascending order)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{k, 0});

        // Step 3: Visited set & max delay time
        HashSet<Integer> vis = new HashSet<>();
        int maxTime = 0;

        // Step 4: Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int node = poll[0];
            int time = poll[1];

            // Skip if already visited
            if (vis.contains(node)) continue;
            vis.add(node);
            maxTime = Math.max(maxTime, time);

            // Process neighbors
            if (!map.containsKey(node)) continue;
            for (Map.Entry<Integer, Integer> entry : map.get(node).entrySet()) {
                int adjNode = entry.getKey();
                int weight = entry.getValue();
                if (!vis.contains(adjNode)) {
                    pq.add(new int[]{adjNode, weight + time});
                }
            }
        }

        // Step 5: If not all nodes were reached, return -1
        return vis.size() == n ? maxTime : -1;
    }
}
