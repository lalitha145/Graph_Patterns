class Solution {
    public int numOfSubarrays(int[] arr) {
       long odd=0;
       long sum=0;
       int mod=1000000007;
        for(int ele:arr){
            sum+=ele;
            sum=sum%mod;
            if(sum%2==1) odd++;
           // sum=sum%mod;
        }
        odd+=(arr.length-odd)*odd;
        return (int)(odd%mod);
                    
    }
}