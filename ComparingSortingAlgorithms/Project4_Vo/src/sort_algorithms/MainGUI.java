/**
 * Comparing Different Sorting Algorithms Program
 * This program lets the user sort various integer arrays with different sort 
 * methods. The user can choose between selection, bubble, insertion and quick sort.
 * The user can then choose the different inputs for the array. They can choose
 * ascending, descending, and random. And then choose the number of values in 
 * the array and the number of values sorted into each block. Then the user pushes
 * the "Go" button and the values are sorted and a window is shown how long it took.
 */
package sort_algorithms;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MainGUI extends Application {
    //These variables are used to check if the user has chosen the correct inputs
    //and what inputs were chosen.
    boolean choseSort = false;
    boolean sortedBySelection = false;
    boolean sortedByBubble = false;
    boolean sortedByInsertion = false;
    boolean sortedByQuick = false;
    boolean choseInputType = false;
    boolean inputAlready = false;
    boolean inputReverse = false;
    boolean inputRandom = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //This is the main plane which holds all the other planes, buttons, labels,
        //and textfields
        VBox mainPane = new VBox(10);
        //This plane holds all the radio buttons that show the choices for 
        //each type of sorting
        VBox sortingPane = new VBox(8);
        mainPane.getChildren().addAll(sortingPane);
        Label sortingLabel = new Label("Sorting Alogrithm");
        sortingPane.getChildren().addAll(sortingLabel);
        RadioButton selectionSort = new RadioButton("Selection");
        RadioButton bubbleSort = new RadioButton("Bubble");
        RadioButton insertionSort = new RadioButton("Insertion");
        RadioButton quickSort = new RadioButton("Quick");
        sortingPane.getChildren().addAll(selectionSort);
        sortingPane.getChildren().addAll(bubbleSort);
        sortingPane.getChildren().addAll(insertionSort);
        sortingPane.getChildren().addAll(quickSort);

        ToggleGroup group = new ToggleGroup();
        selectionSort.setToggleGroup(group);
        bubbleSort.setToggleGroup(group);
        insertionSort.setToggleGroup(group);
        quickSort.setToggleGroup(group);
        //If any of the sorting is chosen, then choseSort is then true showing that
        //the user has chosen a sorting method. And whatever choice was chosen then
        //its boolean variable is then true showing which sorting is chosen.
        selectionSort.setOnAction(e -> {
            choseSort = true;
            sortedBySelection = true;
            sortedByBubble = false;
            sortedByInsertion = false;
            sortedByQuick = false;
        });
        bubbleSort.setOnAction(e -> {
            choseSort = true;
            sortedBySelection = false;
            sortedByBubble = true;
            sortedByInsertion = false;
            sortedByQuick = false;
        });
        insertionSort.setOnAction(e -> {
            choseSort = true;
            sortedBySelection = false;
            sortedByBubble = false;
            sortedByInsertion = true;
            sortedByQuick = false;
        });
        quickSort.setOnAction(e -> {
            choseSort = true;
            sortedBySelection = false;
            sortedByBubble = false;
            sortedByInsertion = false;
            sortedByQuick = true;
        });
        //This plane holds the different input types an array can hold.
        VBox inputPane = new VBox(8);
        mainPane.getChildren().addAll(inputPane);
        Label inputType = new Label("Input Type");
        inputPane.getChildren().addAll(inputType);
        RadioButton alreadySorted = new RadioButton("Already sorted");
        RadioButton reverseOrder = new RadioButton("Reverse order");
        RadioButton random = new RadioButton("Random");
        inputPane.getChildren().addAll(alreadySorted);
        inputPane.getChildren().addAll(reverseOrder);
        inputPane.getChildren().addAll(random);

        ToggleGroup groupTwo = new ToggleGroup();
        alreadySorted.setToggleGroup(groupTwo);
        reverseOrder.setToggleGroup(groupTwo);
        random.setToggleGroup(groupTwo);
        //If an input choice is chosen choseInputType will be true showing an 
        //input was chosen. And whatever input was chosen its boolean variable will 
        //be true showing that input was chosen
        alreadySorted.setOnAction(e -> {
            choseInputType = true;
            inputAlready = true;
            inputReverse = false;
            inputRandom = false;

        });
        reverseOrder.setOnAction(e -> {
            choseInputType = true;
            inputAlready = false;
            inputReverse = true;
            inputRandom = false;

        });
        random.setOnAction(e -> {
            choseInputType = true;
            inputAlready = false;
            inputReverse = false;
            inputRandom = true;

        });
        //This plane holds the label and the text field taking in the number of 
        //values the array will hold
        HBox inputSizeLine = new HBox(8);
        mainPane.getChildren().addAll(inputSizeLine);
        Label inputSize = new Label("Input Size");
        inputSizeLine.getChildren().addAll(inputSize);
        TextField inputSizeField = new TextField();
        inputSizeLine.getChildren().addAll(inputSizeField);
        //This plane hold the label and the text field taking in the number of 
        //values each thread will hold to sort
        HBox blockSizeLine = new HBox(8);
        mainPane.getChildren().addAll(blockSizeLine);
        Label blockSize = new Label("Block Size");
        blockSizeLine.getChildren().addAll(blockSize);
        TextField blockSizeField = new TextField();
        blockSizeLine.getChildren().addAll(blockSizeField);
        //If the user has correctly chosen all the inputs, then the sorting process
        //will begin by pushing "Go"
        Button go = new Button("Go");
        go.setMaxSize(210, 0);
        mainPane.getChildren().addAll(go);
        go.setOnAction(e -> {
            //Creates the a new instance of a merge clas
            Merge merger = new Merge();
            //Creates a new thread for the merge
            Thread merging = new Thread(merger);
            //An arraylist that holds the threads
            ArrayList<Thread> threadList = new ArrayList<>();
            int sizeInput = 0;
            int sizeBlock = 0;
            boolean correctInputs = false;
            //This part checks if the inputs are correct,otherwise an error 
            //message will be shown
            try {
                sizeInput = Integer.parseInt(inputSizeField.getText());
                sizeBlock = Integer.parseInt(blockSizeField.getText());
                if (sizeInput <= 0 || sizeBlock <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid inputs", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    correctInputs = true;
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Invalid inputs", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //If all the inputs are correct the sorting can begin
            if (choseInputType && choseSort && correctInputs) {
                //this array will hold the values that will be sorted
                int[] typeOfSortedArray = new int[sizeInput];
                //If the user chose ascending, an ordered array will be made.
                if (inputAlready) {
                    for (int i = 0; i < sizeInput; i++) {
                        typeOfSortedArray[i] = i;
                    }
                }
                //If the user chose descending, an descending array will be made.
                if (inputReverse) {
                    int number = sizeInput - 1;
                    for (int i = 0; i < sizeInput; i++) {
                        typeOfSortedArray[i] = number;
                        number--;
                    }
                }
                //If the user choses random. a random array will be made.
                if (inputRandom) {
                    for (int i = 0; i < sizeInput; i++) {
                        double randomNum = Math.random() * 100;
                        typeOfSortedArray[i] = (int) randomNum;
                    }
                }
                //Measures the time in the begninng before sorting
                long startTime = System.currentTimeMillis();
                //Sorting method is dependent on what the user choes, the array 
                //will then be split into different threads depending on the block size. The arrays are 
                //sorted by whatever the user chose and then merged back together into one array.
                if (sortedByInsertion) {
                    for (int i = 0; i < typeOfSortedArray.length; i += sizeBlock) {
                        int[] addedArray = Arrays.copyOfRange(typeOfSortedArray, i, Math.min(typeOfSortedArray.length, i + sizeBlock));
                        threadList.add(new Thread(new InsertionSort(addedArray, merger)));
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        threadList.get(i).start();
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        try {
                            threadList.get(i).join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    merging.start();
                    try {
                        merging.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sortedBySelection) {
                    for (int i = 0; i < typeOfSortedArray.length; i += sizeBlock) {
                        int[] addedArray = Arrays.copyOfRange(typeOfSortedArray, i, Math.min(typeOfSortedArray.length, i + sizeBlock));
                        threadList.add(new Thread(new SelectionSort(addedArray, merger)));
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        threadList.get(i).start();
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        try {
                            threadList.get(i).join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    merging.start();
                    try {
                        merging.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sortedByBubble) {
                    for (int i = 0; i < typeOfSortedArray.length; i += sizeBlock) {
                        int[] addedArray = Arrays.copyOfRange(typeOfSortedArray, i, Math.min(typeOfSortedArray.length, i + sizeBlock));
                        threadList.add(new Thread(new BubbleSort(addedArray, merger)));
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        threadList.get(i).start();
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        try {
                            threadList.get(i).join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    merging.start();
                    try {
                        merging.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sortedByQuick) {
                    for (int i = 0; i < typeOfSortedArray.length; i += sizeBlock) {
                        int[] addedArray = Arrays.copyOfRange(typeOfSortedArray, i, Math.min(typeOfSortedArray.length, i + sizeBlock));
                        threadList.add(new Thread(new QuickSort(addedArray, merger)));
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        threadList.get(i).start();
                    }
                    for (int i = 0; i < threadList.size(); i++) {
                        try {
                            threadList.get(i).join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    merging.start();
                    try {
                        merging.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //The time after all sorting is stored
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                //After all the sorting the total time it took the sort is displayed
                JOptionPane.showMessageDialog(null, "Finished\nSort Completed in " + time + "m", "Threaded Sorting", JOptionPane.INFORMATION_MESSAGE);
                //if the user has put in incorrect inputs an error message is shown
            } else if ((!(choseInputType) || !(choseSort)) && correctInputs) {
                JOptionPane.showMessageDialog(null, "Invalid inputs", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        //launches the GUI
        Application.launch(args);
    }

}
