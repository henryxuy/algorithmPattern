import java.util.*;

class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class MinimumMeetingRooms {
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        // sort by the end, then track the max number of overlapping
        int maxCountOverlapping = 1;
        Collections.sort(meetings, new Comparator<Meeting>(){
            @Override
            public int compare(Meeting o1, Meeting o2){
                if(o1.start == o2.start){
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });
        // track the earliest ended meetings
        PriorityQueue<Meeting> minHeap = new PriorityQueue<>((o1, o2) -> (o1.end - o2.end));
        for(Meeting currentMeeting: meetings){
            int currentStart = currentMeeting.start;
            while(!minHeap.isEmpty() && minHeap.peek().end <= currentStart){
                // remove already ended meetings
                minHeap.poll();
            }
            minHeap.offer(currentMeeting);
            maxCountOverlapping = Math.max(maxCountOverlapping, minHeap.size());
        }

        return maxCountOverlapping;
    }


    public static int findMinimumMeetingRoomsSlow(List<Meeting> meetings) {
        // sort by the end, then track the max number of overlapping
        int N = meetings.size();
        int maxCountOverlapping = 1;
        Collections.sort(meetings, new Comparator<Meeting>(){
            @Override
            public int compare(Meeting o1, Meeting o2){
                // sort by the end of the interval
                if(o1.end == o2.end){
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });
        for(int i = 0; i < N - 1; i++){
            // find all overlappings
            int j = i + 1;
            int currentOverlappingCount = 1;
            int currentEnd = meetings.get(i).end;
            while(j < N && meetings.get(j).start < currentEnd){
                // overlapped
                currentOverlappingCount++;
                j++;
            }
            maxCountOverlapping = Math.max(maxCountOverlapping, currentOverlappingCount);
        }


        return maxCountOverlapping;
    }
    
    public static void main(String[] args) {
    List<Meeting> input = new ArrayList<Meeting>() {
        {
        add(new Meeting(4, 5));
        add(new Meeting(2, 3));
        add(new Meeting(2, 4));
        add(new Meeting(3, 5));
        }
    };
    int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
    System.out.println("Minimum meeting rooms required: " + result);

    input = new ArrayList<Meeting>() {
        {
        add(new Meeting(1, 4));
        add(new Meeting(2, 5));
        add(new Meeting(7, 9));
        }
    };
    result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
    System.out.println("Minimum meeting rooms required: " + result);

    input = new ArrayList<Meeting>() {
        {
        add(new Meeting(6, 7));
        add(new Meeting(2, 4));
        add(new Meeting(8, 12));
        }
    };
    result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
    System.out.println("Minimum meeting rooms required: " + result);

    input = new ArrayList<Meeting>() {
        {
        add(new Meeting(1, 4));
        add(new Meeting(2, 3));
        add(new Meeting(3, 6));
        }
    };
    result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
    System.out.println("Minimum meeting rooms required: " + result);

    input = new ArrayList<Meeting>() {
        {
        add(new Meeting(4, 5));
        add(new Meeting(2, 3));
        add(new Meeting(2, 4));
        add(new Meeting(3, 5));
        }
    };
    result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
    System.out.println("Minimum meeting rooms required: " + result);
    }
}



