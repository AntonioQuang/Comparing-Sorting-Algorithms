
package sort_algorithms;

import java.util.Arrays;


public class InsertionSort implements Runnable{
    
    private final Merge merger;
    private final int[]list;
    
    /**
     * This is an constructor that takes in the list that is to be sorted, and 
     * the Merge class where the sorted list will be taken in to be merged with 
     * other lists
     * Precondition:The sorting and all the correct inputs are chosen
     * Postcondition:Takes in the list and merger
     * @param list The list to be sorted
     * @param merger Where the sorted list will go after it is sorted to be merged
     */
    public InsertionSort(int[] list, Merge merger) {
        this.list = list;
        this.merger = merger;
        
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
        for(int i = 1; i< list.length; i++){
            int currentPlace = list[i];
            int i2;
            for(i2 = i-1; i2 >= 0 && list[i2]>currentPlace; i2--){
                list[i2 + 1] = list[i2];
                
            }
            list[i2 + 1] = currentPlace;
        }
        merger.addList(list);
    }
    
    
}
