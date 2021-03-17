import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class InsertInterval {

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval){
        // the given intervals are sorted and non-overlapping
        // skip the intervals not intersecting with newInterval, and merge all intervals intersecting with newInterval
        List<Interval> mergedIntervals = new ArrayList<>();
        if(intervals.size() == 0){
            mergedIntervals.add(newInterval);
            return mergedIntervals;
        }
        boolean isAdded = false;
    
        for(Interval currentInterval: intervals){
            if(currentInterval.end < newInterval.start){
                // skip non-overlapping intervals, directly add into the result list
                mergedIntervals.add(currentInterval);
            }
            else if(currentInterval.start > newInterval.end){
                if(!isAdded){
                    // after merge, add the newInterval to the result list
                    mergedIntervals.add(newInterval);
                    isAdded = true;
                }
                mergedIntervals.add(currentInterval);
            }
            else{
                // merge it with the newInterval
                newInterval.start = Math.min(newInterval.start, currentInterval.start);
                newInterval.end = Math.max(newInterval.end, currentInterval.end);
            }
        }

        return mergedIntervals;
    }


    // public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    //     // insert the newInterval into the intervals to produce all-mutally exclusive intervals
    //     // return the merged list of mutally exclusive intervals
    //     List<Interval> mergedIntervals = new ArrayList<>();
    //     // intervals.sort((o1, o2) -> o1.start - o2.start);
    //     Collections.sort(intervals, (o1, o2) -> o1.start - o2.start);
    //     Interval prevInterval = null;
    //     boolean isMerged = false;
    //     for(Interval currentInterval : intervals) {
    //         if(prevInterval == null){
    //             prevInterval = currentInterval;
    //             continue;
    //         }

    //         if(newInterval.start >= prevInterval.start && newInterval.start <= currentInterval.start && !isMerged){
    //             // merge the new Interval with the previous one
    //             if(newInterval.start > prevInterval.end){
    //                 // not intersect
    //                 mergedIntervals.add(prevInterval);
    //                 prevInterval = newInterval;
    //             }
    //             else{
    //                 prevInterval.end = Math.max(prevInterval.end, newInterval.end);
    //             }

    //         }


    //         if(currentInterval.start > prevInterval.end){
    //             // not intersect
    //             mergedIntervals.add(prevInterval);
    //             prevInterval = currentInterval;
    //         }
    //         else{
    //             // merge
    //             prevInterval.end = Math.max(prevInterval.end, currentInterval.end);
    //         }


    //     }
    //     mergedIntervals.add(prevInterval);

    //     return mergedIntervals;
    // }

  public static void main(String[] args) {
      List<Interval> input = new ArrayList<Interval>();
    input.add(new Interval(1, 3));
    input.add(new Interval(5, 7));
    input.add(new Interval(8, 12));
    System.out.print("Intervals after inserting the new interval: ");
    for (Interval interval : InsertInterval.insert(input, new Interval(4, 6)))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(1, 3));
    input.add(new Interval(5, 7));
    input.add(new Interval(8, 12));
    System.out.print("Intervals after inserting the new interval: ");
    for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(2, 3));
    input.add(new Interval(5, 7));
    System.out.print("Intervals after inserting the new interval: ");
    for (Interval interval : InsertInterval.insert(input, new Interval(1, 4)))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();
  }
}