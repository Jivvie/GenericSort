package edu.lonestar.gjvon.genericSort;

import javax.swing.*;
import java.util.*;

import static java.util.Collections.swap;

/**
 * Created by Gjvon on 9/27/16.
 */
public class GenericSortObject {
    private ArrayList unsortedList;
    private ArrayList sortedList;
    //TODO: These objects will not be used until our methods are designed. See Class:DataDesigner
    private ArrayList<String> stringValues;
    private ArrayList<Double> doubleValues;
    private ArrayList<Integer> intValues;
    private double doubleValue;
    private int integerValue;

    //This is our customized default constructor that has one parameter "ArrayList" that has different values.
    private GenericSortObject(ArrayList list) {
        //We have created a new ArrayList that will not share the memory address of the previous list.
        unsortedList = new ArrayList(list);
        manageAndSort(list);
        viewContents();
    }

    private void manageAndSort(ArrayList list) {
        doQuickSort(list, 0, list.size() - 1);
        //We create a new arraylist that will not share the same address of the previous list.
        sortedList = new ArrayList(list);
    }

    public <T extends Comparable<T>> void doQuickSort(ArrayList<T> arrayList, int start, int end) {

        if (start < end) {
            int pivot = partitionArray(arrayList, start, end);
            doQuickSort(arrayList, start, pivot);
            doQuickSort(arrayList, pivot + 1, end);

        }
        System.out.println(arrayList);
    }

    public <T extends Comparable<T>> int partitionArray(ArrayList<T> arrayList, int start, int end) {
        int midPoint = (start + end) / 2;
        swap(arrayList, start, midPoint);
        T pivotValue = arrayList.get(start);
        int endOfList = start;
        for (int i = start + 1; i <= end; i++) {
            /*The value 0 if the argument is a string lexicographically equal to this string; a value less than 0
            if the argument is a string lexicographically greater than this string; and a value greater than 0 if the
            argument is a string lexicographically less than this string. */
            if (arrayList.get(i).compareTo(pivotValue) < 0) {
                endOfList++;
                swap(arrayList, endOfList, i);
            }
        }
        //swap ArrayList values
        swap(arrayList, start, endOfList);
        return endOfList;
    }

    //This method allows the user to enter as many values as needed
    public static <T> void getValuesFromUser() {
        final String STRING_PROMPT = "Would you like to add more?";
        final String ENTER_VALUE = "What would you like to add to the list?";
        ArrayList listOfValues = new ArrayList();
        T message;
        boolean hasMoreValuesToAdd = true;
        while (hasMoreValuesToAdd) {
            message = (T) JOptionPane.showInputDialog(null, ENTER_VALUE);
            listOfValues.add(message);
            hasMoreValuesToAdd = willEnterMoreValues(STRING_PROMPT);
        }
        //We create a an instance of our class that allows access to our private data.
        new GenericSortObject(listOfValues);
    }
    //This method returns true if the user would like to add values to the list.
    private static boolean willEnterMoreValues(String prompt) {
        System.out.println(prompt);
        int reply = JOptionPane.showConfirmDialog(null, prompt, "More to add? ", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    //This method allows the user to see the values entered and the sorted values
    //TODO: After building the static methods for verifying input, show separate lists.
    public void viewContents() {
        JOptionPane.showMessageDialog(null,  "Values you entered/UnSorted List: " + unsortedList);
        JOptionPane.showMessageDialog(null,  "Sorted List: " + sortedList);

    }

}
