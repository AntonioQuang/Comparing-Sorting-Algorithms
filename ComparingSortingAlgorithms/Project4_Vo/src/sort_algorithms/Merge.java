package sort_algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Merge implements Runnable {

    private final Queue<int[]> listsOfSorted;
    
    /**
     * The constructor for the merge.This creates a queue to hold all the sorted
     * arrays.
     * Preconditions:An instance of Merge is made.
     * Postconditions:A queue is made to hold a sorted arrays.
     */
    public Merge() {
        this.listsOfSorted = new LinkedList<>();

    }
    /**
     * This method adds a list that is sorted to be added to the queue.
     * Precondition: A list is sorted.
     * Postcondition: A sorted list is added to the queue to be merged with other 
     * arrays.
     * @param list The list that is to be added to the queue.
     */
    public synchronized void addList(int[] list) {
        listsOfSorted.offer(list);
    }
    
    /**
     * This is the thread that is to be run. This method will take two arrays from 
     * the queue and merge them together in order and then add them back into the queue.
     * This process will repeat until there is only one sorted array.
     * Precondition:The start is called on this thread. Sorted arrays are then 
     * added this to this queue to be merged together.
     * Postcondition:A single sorted array is made by merging all the sorted lists together
     * and the list is printed out
     */
    @Override
    public void run() {
        
        while (listsOfSorted.size() > 1) {
            
            int[] list1 = listsOfSorted.poll();
            int[] list2 = listsOfSorted.poll();
            int[] newSortedList = new int[list1.length + list2.length];

            int current1 = 0;
            int current2 = 0;
            int current3 = 0;

            while (current1 < list1.length && current2 < list2.length) {
                if (list1[current1] < list2[current2]) {
                    newSortedList[current3++] = list1[current1++];
                } else {
                    newSortedList[current3++] = list2[current2++];
                }
            }

            while (current1 < list1.length) {
                newSortedList[current3++] = list1[current1++];
            }

            while (current2 < list2.length) {
                newSortedList[current3++] = list2[current2++];
                
            }
            
            listsOfSorted.offer(newSortedList);
            
        }
        System.out.println(Arrays.toString(listsOfSorted.peek()));
        
    }

}
