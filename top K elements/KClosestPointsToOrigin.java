import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distFromOrigin() {
        // ignoring sqrt
        return (x * x) + (y * y);
    }
}

class KClosestPointsToOrigin {

    public static List<Point> findClosestPoints(Point[] points, int k) {
        // find the kth closes element
        // maxHeap, finally stores k smallest elements (similar to the maxHeap in two heap)
        ArrayList<Point> result = new ArrayList<>();
        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());

        for(int i = 0; i < k; i++){
            maxHeap.offer(points[i]);
        }

        for(int i = k; i < points.length; i++){
            // compare with peek(), only add smaller elements
            Point tempPoint = points[i];
            if(tempPoint.distFromOrigin() < maxHeap.peek().distFromOrigin()){
                maxHeap.poll();
                maxHeap.offer(tempPoint);
            }
        }

        // get the k results
        for(int i = 0; i < k; i++){
            result.add(maxHeap.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
        List<Point> result = KClosestPointsToOrigin.findClosestPoints(points, 2);
        System.out.print("Here are the k points closest the origin: ");
        for (Point p : result)
        System.out.print("[" + p.x + " , " + p.y + "] ");
    }
}