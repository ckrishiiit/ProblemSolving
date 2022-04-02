package PriorityQueue;

public class ShortestSubArraywithSumAtleastK {

    public static int shortestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int windowStart = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            sum += nums[windowEnd];
            while (sum >= k) {
                int len = windowEnd - windowStart + 1;
                minLen = Math.min(minLen, len);
                sum -= nums[windowStart];
                windowStart += 1;
            }
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    public static void main(String[] a){
        int[] nums = {48,99,37,4,-31};
        int k = 3;
       int len = ShortestSubArraywithSumAtleastK.shortestSubarray(nums,140);
       System.out.println(len);
    }
}
