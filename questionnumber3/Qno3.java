package questionnumber3;

public class Qno3 {
     public static int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0) return 0;

        int[][] dp = new int[k + 1][prices.length];

        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0];

            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }

        return dp[k][prices.length - 1];
    }

    public static void main(String[] args) {
        int[] prices = {3,2,6,5,0,3};
        int k = 2;

        System.out.println("Maximum Profit: " + maxProfit(k, prices));
}
}

