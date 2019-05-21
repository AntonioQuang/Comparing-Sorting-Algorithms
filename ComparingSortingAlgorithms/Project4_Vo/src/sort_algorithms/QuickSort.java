
package sort_algorithms;

import java.util.Arrays;


public class QuickSort implements Runnable {
    
    private final Merge merger;
    private final int[] list;

        /**
     * This is an constructor that takes in the list that is to be sorted, and 
     * the Merge class where the sorted list will be taken in to be merged with 
     * other lists
     * Precondition:The sorting and all the correct inputs are chosen
     * Postcondition:Takes in the list and merger
     * @param list The list to be sorted
     * @param merger Where the sorted list will go after it is sorted to be merged
     */
    public QuickSort(int[] list, Merge merger) {
        this.list = list;
        this.merger = merger;
    }
    /**
     * This is a recursive method that will sort the list by quicksort.
     * Precondition: An int array is passed in.
     * Postcondition: An array is sorted by quicksort.
     * @param list The list to be sorted
     */
    public static void quickSort(int[] list){
        quickSort(list, 0, list.length-1);
    }
    /**
     * This method sorts the list by quicksort and calls the method itself. It 
     * calls the partition method to switch elements in the array.
     * Precondition:The quickSort method is called recursively.
     * Postcondition:An array is sorted by quicksort.
     * @param list The list to be sorted.
     * @param first Index of the beginning of the array.
     * @param last Index of the end of the array.
     */
    public static void quickSort(int[] list, int first, int last){
        if(last > first){
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }
    /**
     * This method switches elements in the array and pivots are switched.
     * Precondition: The list, first and last is passed in and the method is called.
     * Postcondition: Indexes are returned and elements in the array are switched around
     * @param list The list to be sorted.
     * @param first The beginning of the index of the sorted part of the array
     * @param last The end index of the part being sorted
     * @return The pivot and switches pivots
     */
    public static int partition(int[] list, int first, int last){
        int pivot = list[first];
        int low = first + 1;
        int high = last;
        
        while(high > low){
            while(low <= high && list[low] <= pivot){
                low++;
            }
            while(low <= high && list[high] > pivot){
                high--;
            }
            
            if(high > low){
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        
        while(high > first && list[high] >= pivot){
            high--;
        }
        
        if(pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }else{
            return first;
        }
    }
    /**
     * This is the method that will be run in the thread. It will sort the 
     * list and then put the sorted list in the merge object.
     * Precondition:The start is called on this thread.
     * Postcondition: The list is then sorted and added to the Merge class to 
     * be merged with other sorted array.
     */
    @Override
    public void run() {
        quickSort(list);
        merger.addList(list);
    }
    
    
}
