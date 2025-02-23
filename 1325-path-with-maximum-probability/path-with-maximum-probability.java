class Pair{
    int node;
    double prob;
    public Pair(int node,double prob){
        this.node=node;
        this.prob=prob;
    }
}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double max=Double.MIN_VALUE;
        HashMap<Integer,HashMap<Integer,Double>> map=new HashMap<>();
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->Double.compare(b.prob,a.prob));
       // PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        HashSet<Integer> vis=new HashSet<>();
        for(int i=0;i<edges.length;i++){
            map.putIfAbsent(edges[i][0],new HashMap<>());
            map.putIfAbsent(edges[i][1],new HashMap<>());
            map.get(edges[i][0]).put(edges[i][1],succProb[i]);
            map.get(edges[i][1]).put(edges[i][0],succProb[i]);
            
        }
        pq.add(new Pair(start_node,1.0));
        while(!pq.isEmpty()){
            
            Pair poll=pq.poll();
            int node=poll.node;
            double currProb=poll.prob;
            if(node==end_node) return currProb;
            vis.add(node);
            if(!map.containsKey(node)) continue;
            for(Map.Entry<Integer,Double> entry:map.get(node).entrySet()){
                int adjNode=entry.getKey();
                double pro=entry.getValue();
                if(!vis.contains(adjNode)){
                    pq.add(new Pair(adjNode,pro*currProb));
                }
            }
        }

        //System.out.println(map);
        return 0;

    }
}