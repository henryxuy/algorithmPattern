class QuickSort{

    public <T extends Comparable<T>> void quicksort(T[] list, int lo, int hi){
        if(lo < hi){
            int partition = partition(list, lo, hi);
            // we do not include pivot in the sorting process
            // so we use pivot - 1 and pivot + 1
            quicksort(list, lo, partition - 1);
            quicksort(list, partition + 1, hi);
        }
    }
    
    private <T extends Comparable<T>> int partitionLomuto(T[] list, int left, int right, int pivotIndex){
        // store pivot in the end
        swap(list, pivotIndex, right);
        int storeIndex = left;
        T pivot = list[pivotIndex];
        for(int i = left; i < right; i++){
            if(list[i].compareTo(pivot) < 0){
                swap(list, i, storeIndex);
                storeIndex++;
            }
        }
        // put the piovt in the storeIndex
        swap(list, storeIndex, right);
        return storeIndex;
    }

    private <T extends Comparable<T>> int partitionHoare(T[] list, int left, int right){
        T pivot = list[(left + right) / 2];
        int i = left - 1, j = right + 1;
        while(true){
            do{
                i++;
            } while(list[i].compareTo(pivot) < 0);
            do{
                j--;
            } while(list[j].compareTo(pivot) > 0);

            if(i >= j){
                return j;
            }
            swap(list, i, j);
        }
    }


    private <T extends Comparable<T>> int partition(T[] list, int left, int right){
        // group the list[left...right] into two groups: smaller than list[pivotIndex], 
        // and larger than list[pivotIndex]. Finally determine the location of pivot.
        // return the new index of partitioned pivot
        T pivot = list[left];                    // use the first as pivot. Other case, just swap the chosen pivot and the first
        // // move pivot to the start
        // swap(list, pivotIndex, left);

        int low = left + 1, high = right;
        while(low < high){
            // search the small elements in right and large ones in left, and swap them
            while(list[low].compareTo(pivot) <= 0 && low < high){
                low++;
            }
            while(list[high].compareTo(pivot) > 0 && low < high){
                high--;
            }
            if(low < high){
                swap(list, low, high);
            }
        }
        // move high to find the first element smaller than pivot, and swap
        while(high > left && list[high].compareTo(pivot) >=0){
            high--;
        }
        // sub in the pivot if necessary
        if(pivot.compareTo(list[high]) > 0){
            list[left] = list[high];
            list[high] = pivot;
            return high;
        }
        else{
            return left;
        }
        
    }

    private <T> void swap(T[] list, int i, int j){
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }




}