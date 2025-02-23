class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
        for(int a[]:edges){
            map.putIfAbsent(a[0],new HashMap<>());
            map.putIfAbsent(a[1],new HashMap<>());
            map.get(a[0]).put(a[1],a[2]);
            map.get(a[1]).put(a[0],a[2]);
        }
        int ans=0;
        int maxReachable=n+1;
        for(int i=0;i<n;i++){
            int reachable=bfs(map,i,distanceThreshold);
            if(reachable<=maxReachable){
                maxReachable=reachable;
                ans=i;
            }
        }
       return ans;
    }
    int bfs(HashMap<Integer,HashMap<Integer,Integer>> map,int node,int t){
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        HashSet<Integer> vis=new HashSet<>();
        //set.add(node);
        int cnt=0;
        pq.add(new int[]{node,0});
        while(!pq.isEmpty()){
            int poll[]=pq.poll();
            int nd=poll[0];
            int th=poll[1];
            vis.add(nd);
            if(!map.containsKey(nd)) continue;
            for(Map.Entry<Integer,Integer> entry:map.get(nd).entrySet()){
                int adjNode=entry.getKey();
                int nth=entry.getValue();
                if(th+nth<=t && !vis.contains(adjNode)){
                    cnt++;
                    pq.add(new int[]{adjNode,th+nth});
                }
            }
        }
        return vis.size()-1;
    }

}