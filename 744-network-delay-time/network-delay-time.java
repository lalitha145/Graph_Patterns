class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
        HashSet<Integer> vis=new HashSet<>();
        int maxTime=0;
       // PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        for(int a[]:times){
            map.putIfAbsent(a[0],new HashMap<>());
            map.get(a[0]).put(a[1],a[2]);
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        pq.add(new int[]{k,0});
        while(!pq.isEmpty()){
            int poll[]=pq.poll();
            int node =poll[0];
            int time=poll[1];
            if(vis.contains(node))continue;
            maxTime=Math.max(maxTime,time);
            vis.add(node);
            if(!map.containsKey(node)) continue;
            for(Map.Entry<Integer,Integer> entry:map.get(node).entrySet()){
                int adjNode=entry.getKey();
                int weight=entry.getValue();
                if(!vis.contains(adjNode)) pq.add(new int[]{adjNode,weight+time});
            }
        }
        if(vis.size()==n){
            return maxTime;
        }

        return -1;
    }
}