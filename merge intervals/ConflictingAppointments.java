import java.util.*;

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

class ConflictingAppointments {

    public static boolean canAttendAllAppointments(Interval[] intervals) {
        // intervals: appointments
        // find out whether a person can attend all appointments (all intervals donot overlap)
        Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);
        Interval prevInterval = null;
        for(Interval currentInterval : intervals) {
            if(prevInterval == null) {
                prevInterval = currentInterval;
                continue;
            }
            if(prevInterval.end >= currentInterval.start){
                return false;
            }
        }

        return true;
    }

  public static void main(String[] args) {
    Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
    boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
    System.out.println("Can attend all appointments: " + result);

    Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
    result = ConflictingAppointments.canAttendAllAppointments(intervals1);
    System.out.println("Can attend all appointments: " + result);

    Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
    result = ConflictingAppointments.canAttendAllAppointments(intervals2);
    System.out.println("Can attend all appointments: " + result);
  }
}