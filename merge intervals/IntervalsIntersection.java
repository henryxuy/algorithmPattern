import java.util.*;

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

class IntervalsIntersection {

    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        // find the intersection of two lists
        List<Interval> intervalsIntersection = new ArrayList<Interval>();
        int i = 0, j = 0;
        int lengthFirst = arr1.length, lengthSecond = arr2.length;
        while(i < lengthFirst && j < lengthSecond){
            Interval intervalFirst = arr1[i];
            Interval intervalSecond = arr2[j];
            if(intervalFirst.end < intervalSecond.start){
                // not intersect, first interval is on the left
                i++;
            }
            else if(intervalSecond.end < intervalFirst.start){
                // not intersect, second interval is on the right
                j++;
            }
            else{
                // intersect
                int startOverlap = Math.max(intervalFirst.start, intervalSecond.start);
                int endOverlap = Math.min(intervalFirst.end, intervalSecond.end);
                Interval overlapInterval = new Interval(startOverlap, endOverlap);
                intervalsIntersection.add(overlapInterval);
                if(intervalFirst.end <= intervalSecond.end){
                    i++;
                }
                else{
                    j++;
                }
            }
        }

        return intervalsIntersection.toArray(new Interval[intervalsIntersection.size()]);
    }

  public static void main(String[] args) {
    Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), new Interval(7, 9) };
    Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
    Interval[] result = IntervalsIntersection.merge(input1, input2);
    System.out.print("Intervals Intersection: ");
    for (Interval interval : result)
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7), new Interval(9, 12) };
    input2 = new Interval[] { new Interval(5, 10) };
    result = IntervalsIntersection.merge(input1, input2);
    System.out.print("Intervals Intersection: ");
    for (Interval interval : result)
      System.out.print("[" + interval.start + "," + interval.end + "] ");
  }
}