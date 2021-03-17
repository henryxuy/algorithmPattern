import java.util.*;

class Job {
    int start;
    int end;
    int cpuLoad;

    public Job(int start, int end, int cpuLoad) {
        this.start = start;
        this.end = end;
        this.cpuLoad = cpuLoad;
    }
};


public class MaximumCPULoad {
    public static int findMaxCPULoad(List<Job> jobs) {
        int maxSumLoad = 0;
        Collections.sort(jobs, new Comparator<Job>(){
            @Override
            public int compare(Job o1, Job o2){
                if(o1.start == o2.start){
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Job> minHeap = new PriorityQueue<>((o1, o2) -> (o1.end - o2.end));
        int sumLoad = 0;
        for(Job currentJob: jobs){
            int currentStart = currentJob.start;
            while(!minHeap.isEmpty() && minHeap.peek().end <= currentStart){
                sumLoad -= minHeap.poll().cpuLoad;
            }
            minHeap.offer(currentJob);
            sumLoad += currentJob.cpuLoad;
            maxSumLoad = Math.max(maxSumLoad, sumLoad);
        }


        return maxSumLoad;
    }

    public static void main(String[] args) {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));
  }
}
