
package sort_algorithms;



public class BubbleSort implements Runnable {

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
    public BubbleSort(int[] list, Merge merger) {
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
                boolean pass = true;
        for (int k = 1; k < list.length && pass; k++) {
            pass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] > list[i + 1]) {
                    int number = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = number;
                    pass = true;
                }
                
            }

        }
        merger.addList(list);
    }
    
}
