import java.util.*;

class QuickSelect{
    // use the partition (similar to quicksort) to find the kth smallest element
    // in an unordered list

    private <T extends Comparable<T>> T select(T[] list, int left, int right, int k){
        // exit condition: the list only contains one element
        if(left == right){
            return list[left];
        }
        // select a pivot index (in the range[left, right])
        int pivotIndex = (left + right) / 2;    
        pivotIndex = partition(list, left, right, pivotIndex);
        if(k == pivotIndex){
            return list[k];
        }
        else if(k < pivotIndex){
            // search in the left side list[left...pivotIndex - 1] recursively
            return select(list, left, pivotIndex - 1, k);
        }
        else{
            // search in the left side list[pivotIndex + 1...right] recursively
            return select(list, pivotIndex + 1, right, k);
        }
    }

    private <T extends Comparable<T>> int partition(T[] list, int left, int right, int pivotIndex){
        // Lomuto partition scheme
        T pivot = list[pivotIndex];
        swap(list, pivotIndex, right);      // move pivot to the end
        int storeIndex = left;
        for(int i = left; i < right; i++){
            if(list[i].compareTo(pivot) < 0){
                swap(list, i, storeIndex);
                storeIndex++;
            }
        }
        swap(list, storeIndex, right);
        return storeIndex;
    }



    private <T> void swap(T[] list, int i, int j){
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }




}