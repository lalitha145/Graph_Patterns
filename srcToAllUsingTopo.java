
// User function Template for Java
class Pair{
    int node,weight;
    public Pair(int node,int weight){
        this.node=node;
        this.weight=weight;
    }
}
class Solution {

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        List<List<Pair>> adj=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        boolean vis[]=new boolean[V];
       
        
        int dis[]=new int[V];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[0]=0;
        for(int i=0;i<V;i++){
           List<Pair> temp=new ArrayList<>();
            adj.add(temp);
        }
        
        
        for(int i=0;i<edges.length;i++){
           int a=edges[i][0], b=edges[i][1], w=edges[i][2];
           adj.get(a).add(new Pair(b,w));
        }
        
         for(int i=0;i<V;i++){
            if(vis[i]==false){
                topo(vis,stack,adj,i);
            }
        }
        while(!stack.isEmpty()){
            int node=stack.pop();
            for(Pair p:adj.get(node)){
                int d=p.weight;
                int n=p.node;
                if(dis[n]>dis[node]+d){
                    dis[n]=dis[node]+d;
                }
            }
            
        }
        for(int i=0;i<dis.length;i++){
            if(dis[i]==Integer.MAX_VALUE) dis[i]=-1;
        }
        return dis;
        
    }
    static void topo(boolean vis[],Stack<Integer> stack,List<List<Pair>> adj,int N){
        vis[N]=true;
        for(Pair p:adj.get(N)){
            int n=p.node;
            if(!vis[n]){
                topo(vis,stack,adj,n);
            }
        }
        stack.push(N);
    }
}
