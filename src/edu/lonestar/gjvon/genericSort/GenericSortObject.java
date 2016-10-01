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
    private ArrayList<String> stringValues = new ArrayList();
    private ArrayList<Double> doubleValues = new ArrayList();
    private ArrayList<Integer> intValues = new ArrayList();
    private double doubleValue;
    private int integerValue;

    //This is our customized default constructor that has one parameter "ArrayList" that has different values.
    private GenericSortObject(ArrayList list) {
        //We have created a new ArrayList that will not share the memory address of the previous list.
        unsortedList = new ArrayList(list);
        manageAndSort(list);
        viewContents();
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
        return reply == JOptionPane.YES_OPTION;
    }

    private void manageAndSort(ArrayList list) {
        Double d = new Double(0);
        Integer newInt = new Integer(8);
        String string = new String("");

        for (int i = 0; i < list.size(); i++) {
            if (DataDesigner.isIntegerValue(list.get(i))) {
                newInt = Integer.parseInt(String.valueOf(list.get(i)));
                intValues.add(newInt);
            } else if (DataDesigner.isDoubleValue(list.get(i))) {
                d = Double.parseDouble(String.valueOf(list.get(i)));
                doubleValues.add(d);
            } else {
                string = list.get(i).toString();
                stringValues.add(string);
            }
        }
        System.out.println(intValues + " " + doubleValues + " " + stringValues);
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

    //This method allows the user to see the values entered and the sorted values
    //TODO: After building the static methods for verifying input, show separate lists.
    public void viewContents() {
        JOptionPane.showMessageDialog(null, "Values you entered/UnSorted List: " + unsortedList);
        JOptionPane.showMessageDialog(null, "Sorted List: " + sortedList);

    }

}
