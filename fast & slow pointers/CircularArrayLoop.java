public class CircularArrayLoop {
    public static boolean loopExistsAnswer(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isForward = arr[i] >= 0; // if we are moving forward or not
            int slow = i, fast = i;
    
          // if slow or fast becomes '-1' this means we can't find cycle for this number
            do {
                    slow = findNextIndex(arr, isForward, slow); // move one step for slow pointer
                    fast = findNextIndex(arr, isForward, fast); // move one step for fast pointer
                if (fast != -1)
                    fast = findNextIndex(arr, isForward, fast); // move another step for fast pointer
            } while (slow != -1 && fast != -1 && slow != fast);
    
            if (slow != -1 && slow == fast)
                return true;
        }
    
        return false;
      }
    
      private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        boolean direction = arr[currentIndex] >= 0;
        if (isForward != direction)
            return -1; // change in direction, return -1
    
        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if (nextIndex < 0)
            nextIndex += arr.length; // wrap around for negative numbers
    
        // one element cycle, return -1 
        if (nextIndex == currentIndex)
            nextIndex = -1;
    
        return nextIndex;
    }



    public static boolean loopExists(int[] arr) {
        if(arr.length < 2){
            return false;
        }
        // method 1: fast & slow pointers
        boolean lastSlowForward = true;
        int N = arr.length;
        for(int startIndex = 0; startIndex < N; startIndex++){
            int fastIndex = startIndex, slowIndex = startIndex;
            lastSlowForward = arr[startIndex] > 0;
            int cycleCount = 0;
            while(true){
                // if the direction is changed, this is not a circle
                boolean thisSlowForward = arr[slowIndex] > 0;
                if(thisSlowForward != lastSlowForward){
                    break;
                }
                lastSlowForward = thisSlowForward;
                // move forward for fast and slow
                // invariant: all indices have a valid range
                slowIndex = (slowIndex + arr[slowIndex] + N) % N;

                fastIndex = (fastIndex + arr[fastIndex] + N) % N;
                fastIndex = (fastIndex + arr[fastIndex] + N) % N;

                if(fastIndex == slowIndex){
                    // check it's not a one element cycle
                    int checkIndex = slowIndex;
                    checkIndex = (checkIndex + arr[checkIndex] + N) % N;
                    if(checkIndex != slowIndex){
                        return true;
                    }
                    else{
                        break;
                    }
                }
                cycleCount++;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(CircularArrayLoop.loopExists(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println(CircularArrayLoop.loopExists(new int[] { 2, 2, -1, 2 }));
        System.out.println(CircularArrayLoop.loopExists(new int[] { 2, 1, -1, -2 }));
    }
    
}
