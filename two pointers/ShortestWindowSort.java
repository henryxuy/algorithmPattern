public class ShortestWindowSort {
    public static int sort(int[] arr) {
        // find the start and end elements that violate the sequence
        int N = arr.length;
        int start = 0, end = N - 1;
        while(start < N - 1){
            if(arr[start] > arr[start + 1]){
                // find the start element
                break;
            }
            start++;
        }
        if(start == N - 1){
            // all sorted
            return 0;
        }
        while(end > 0){
            if(arr[end - 1] > arr[end]){
                // find the end element
                break;
            }
            end--;
        }
        // expand the [start, end] interval based on its min and max
        int minElement = Integer.MAX_VALUE;
        int maxElement = Integer.MIN_VALUE;
        for(int i = start; i <= end; i++){
            minElement = Math.min(minElement, arr[i]);
            maxElement = Math.max(maxElement, arr[i]);
        }

        while(start > 0 && arr[start - 1] > minElement){
            start--;
        }
        while(end < N - 1 && arr[end + 1] < maxElement){
            end++;
        }


        return end - start + 1;
    }

    public static void main(String[] args) {
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 2, 3 }));
        System.out.println(ShortestWindowSort.sort(new int[] { 3, 2, 1 }));
    }

}
