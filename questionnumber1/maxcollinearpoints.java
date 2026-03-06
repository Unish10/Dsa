package questionnumber1;

import java.util.*;

public class maxcollinearpoints {

    public static int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int max = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicates = 1;

            for (int j = i + 1; j < points.length; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                
                if (dy < 0 || (dy == 0 && dx < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                String slope = dx + "/" + dy;
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
            }

            int currentMax = 0;
            for (int count : slopeMap.values()) {
                currentMax = Math.max(currentMax, count);
            }

            max = Math.max(max, currentMax + duplicates);
        }

        return max;
    }

    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[][] points = {{1,1},{2,2},{3,3},{4,5}};
        System.out.println("Maximum Collinear Points: " + maxPoints(points));
    }
}

