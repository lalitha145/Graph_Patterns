class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m=heights.length, n=heights[0].length;
        int dis[][]=new int[m][n];
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int a[]:dis){
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        dis[0][0]=0;
        int dir[][]={{-1,0},{1,0},{0,-1},{0,1}};
        pq.add(new int[]{0,0,0});
        while(!pq.isEmpty()){
            int poll[]=pq.poll();
            int effort=poll[0];
            int row=poll[1];
            int col=poll[2];
            if(row==m-1 && col==n-1) return effort;
            for(int d[]:dir){
                int nr=d[0]+row;
                int nc=d[1]+col;
                if(nr>=0 && nc>=0 && nr<m && nc<n){
                    int currEffort=Math.max(effort,Math.abs(heights[nr][nc]-heights[row][col]));
                    if(dis[nr][nc]>currEffort){
                        dis[nr][nc]=currEffort;
                        pq.add(new int[]{dis[nr][nc],nr,nc});
                    }
                }
            }
        }


        return 0;
    }
}