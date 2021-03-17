import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class MergeIntervals {

    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1){
            return intervals;
        }
        List<Interval> mergedIntervals = new LinkedList<Interval>();
        Interval prevInterval = null;
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for(Interval interval: intervals) {
            if(prevInterval == null){
                prevInterval = interval;
                continue;
            }
            // cases to deal with different interval relationships
            if(interval.start > prevInterval.end){
                // not intersect, just add the previous interval
                mergedIntervals.add(prevInterval);
                prevInterval = interval;
            }
            else{
                // merge
                prevInterval.end = Math.max(prevInterval.end, interval.end);
            }
            // else if(interval.end > prevInterval.end){
            //     // partially intersect, merge and not add
            //     prevInterval.end = interval.end;
            // }
            // else{
            //     // current is contained in the previous, still not add
            // }
        }
        // add the last remaining interval
        mergedIntervals.add(new Interval(prevInterval.start, prevInterval.end));

        return mergedIntervals;
    }

  public static void main(String[] args) {
    List<Interval> input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 5));
    input.add(new Interval(7, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(6, 7));
    input.add(new Interval(2, 4));
    input.add(new Interval(5, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 6));
    input.add(new Interval(3, 5));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();
  }
}


