import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class EmployeeInterval {
    Interval interval; // interval representing employee's working hours
    int employeeIndex; // index of the list containing working hours of this employee
    int intervalIndex; // index of the interval in the employee list

    public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
        this.interval = interval;
        this.employeeIndex = employeeIndex;
        this.intervalIndex = intervalIndex;
    }
};


public class EmployeeFreeTime {
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule){
        // use a minHeap to sort all lists (utilizing each employee list is individually sorted)
        List<Interval> result = new ArrayList<>();
        // PQ to store one interval for each employee
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.interval.start, b.interval.start));

        // insert the first interval of each employee to the queue
        for(int i = 0; i < schedule.size(); i++){
            minHeap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
        }

        Interval previousInterval = minHeap.peek().interval;
        while(!minHeap.isEmpty()){
            EmployeeInterval queueTop = minHeap.poll();
            // compare two intervals. If not overlapping, insert a free interval
            if(previousInterval.end < queueTop.interval.start){
                result.add(new Interval(previousInterval.end, queueTop.interval.start));
                previousInterval = queueTop.interval;
            }
            else {
                // overlapping, update the previousInterval (merge)
                if (previousInterval.end < queueTop.interval.end) {
                    previousInterval = queueTop.interval;
                }
            }
            // if there are more intervals available for the same employee, add their next interval
            List<Interval> employeeSchedule = schedule.get(queueTop.employeeIndex);
            if(employeeSchedule.size() > queueTop.intervalIndex + 1){
                minHeap.offer(new EmployeeInterval(employeeSchedule.get(queueTop.intervalIndex + 1),
                        queueTop.employeeIndex, queueTop.intervalIndex+1));
            }
        }

        return result;
    }


    public static List<Interval> findEmployeeFreeTimeSlow(List<List<Interval>> schedule) {
        // Note: schedule is sorted on the start time
        List<Interval> result = new ArrayList<>();
        List<Interval> currentList = new ArrayList<>();
        for(List<Interval> listNewIntervals: schedule){
            // use two pointers to merge the intervals
            List<Interval> tempMergedList = new ArrayList<>();
            int i = 0, j = 0;
            while(i < currentList.size() && j < listNewIntervals.size()){
                Interval currentInterval = currentList.get(i);
                Interval currentNewInterval = listNewIntervals.get(j);
                int currentStart = currentInterval.start, newStart = currentNewInterval.start;
                int currentEnd = currentInterval.end, newEnd = currentNewInterval.end;
                // no intersection
                if(newStart > currentEnd){
                    tempMergedList.add(currentInterval);
                    i++;
                }
                else if(currentStart > newEnd){
                    tempMergedList.add(currentNewInterval);
                    j++;
                }
                else{
                    // have intersection, need merging
                    // may have chained merging
                    // move forward
                    if(newEnd < currentEnd){
                        currentInterval.start = Math.min(currentStart, newStart);
                        currentInterval.end = Math.max(currentEnd, newEnd);
                        j++;
                    }
                    else{
                        currentNewInterval.start = Math.min(currentStart, newStart);
                        currentNewInterval.end = Math.max(currentEnd, newEnd);
                        i++;
                    }
                }
            }
            // merge the remaining
            while(i < currentList.size()){
                tempMergedList.add(currentList.get(i));
                i++;
            }
            while(j < listNewIntervals.size()){
                tempMergedList.add(listNewIntervals.get(j));
                j++;
            }

            currentList = tempMergedList;
        }
        // currentList: all intervals merged
        if(currentList.size() <= 1){
            // no free interval
            return result;
        }
        Interval parent = currentList.get(0);
        Interval current = currentList.get(1);
        for(int i = 0; i < currentList.size() - 1; i++){
            current = currentList.get(i + 1);
            Interval freeInterval = new Interval(parent.end, current.start);
            parent = current;
            result.add(freeInterval);
        }

        return result;
    }

    public static void main(String[] args) {

        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();
    
        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();
    
        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        
    }

    
}
