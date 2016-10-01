package edu.lonestar.gjvon.genericSort;

import java.util.InputMismatchException;

/**
 * Created by Gjvon on 9/29/16.
 */

/* This class will have static methods that manages data in our different objects. IT is hopeful that our
 * class name is not ambiguous nor abstract. The class has methods that checks for types of data in an array
  * and looks for numbers in string values. */

public class DataDesigner {

    public static <T extends Number> String convertToString(T number) {
        String s = String.valueOf(number);
        return s;
    }

    public static boolean hasInvalidName(String s) {
        char[] array = s.toCharArray();
        int length = array.length;
        /* Loop through the entire array checking if there is an invalid character. If an invalid character is
         * ever found, we do not care about the rest of the name, the name is false. Return true  */
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(array[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDoubleValue(Object aDouble) {
        try {
            Double.parseDouble(String.valueOf(aDouble));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isIntegerValue(Object aInteger) {
        try {
            Integer.parseInt(String.valueOf(aInteger));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
