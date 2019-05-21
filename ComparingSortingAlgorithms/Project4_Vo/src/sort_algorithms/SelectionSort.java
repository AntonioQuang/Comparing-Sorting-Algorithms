package sort_algorithms;

public class SelectionSort implements Runnable {

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
    public SelectionSort(int[] list, Merge merger) {
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
        for (int i = 0; i < list.length - 1; i++) {
            int number = i;
            for (int i2 = i + 1; i2 < list.length; i2++) {
                if (list[i2] < list[number]) {
                    number = i2;
                }
            }
            int smallerNumber = list[number];
            list[number] = list[i];
            list[i] = smallerNumber;
        }
        merger.addList(list);
    }

}
