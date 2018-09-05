package com.zemoso.assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Formats the phone number to a more readable form. A string
 * of numbers riddled with spaces and dashes is taken as input.
 * All spaces and dashes are removed and the digits are divided
 * into sets of 2 or 3 depending on the total number of digits.
 * Each set is separated from each other using new dashes.
 *
 * Assumes that the length of the unformatted string is at least 2
 * and at most 100. It also assumes that the unformatted string
 * contains at least 2 digits in total and that the string contains
 * nothing but digits, dashes and spaces.
 */
public class FormatPhoneNumber {

    /**
     * Prompts the user to input the unformatted string. Accepts
     * the input and prints out the formatted phone number.
     *
     * @param args not used.
     * @throws IOException may be thrown during the user prompt.
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Enter the phone number to be formatted");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer unformatted = new StringBuffer(br.readLine());

        System.out.println("The formatted phone number is: " + finalFormatter(dashRemover(spaceRemover(unformatted))));
    }

    /**
     * Removes all spaces(' ') from the string passed to it.
     *
     * Takes a StringBuffer object as the argument,
     * iterates over it until the last instance of a space
     * character and removes all instances of it from the
     * StringBuffer object and then returns it.
     *
     * @param str the StringBuffer object to remove the spaces from.
     * @return the same StringBuffer object with the spaces removed.
     */
    private static StringBuffer spaceRemover(StringBuffer str) {

        for (short i = 0; i <= str.lastIndexOf(" "); i++)
            if (str.charAt(i) == ' ') {
                str.deleteCharAt(i);
                i--;                    //Deleting would cause skipping of an element without this line.
            }
        return str;
    }

    /**
     * Removes all dashes('-') from the string passed to it.
     *
     * Takes a StringBuffer object as the argument,
     * iterates over it until the last instance of a dash
     * character and removes all instances of it from the
     * StringBuffer object and then returns it.
     *
     * @param str the StringBuffer object to remove the dashes from.
     * @return the same StringBuffer object with the dashes removed.
     */
    private static StringBuffer dashRemover(StringBuffer str) {

        for (short i = 0; i <= str.lastIndexOf("-"); i++)
            if(str.charAt(i) == '-') {
                str.deleteCharAt(i);
                i--;                    //Deleting would cause skipping of an element without this line.
            }
        return str;
    }

    /**
     * Formats the phone number string into a readable form.
     *
     * Takes the StringBuffer object cleaned of dashes and spaces
     * and checks the total number of digits to determine the final
     * format of the output.
     *
     * If the number of digits is less than or equal to 3, the
     * format will be "xx" or "xxx".
     * For any number of digits above 3,
     * and of the form (3n + 1), the format will be "xxx-...-xxx-xx-xx".
     * As for the form (3n + 2), the format will be "xxx-...-xxx-xx".
     *
     * @param str the digit-only StringBuffer object to be formatted.
     * @return the StringBuffer object appropriately formatted.
     */
    private static StringBuffer finalFormatter(StringBuffer str) {

        if (str.length() == 2)
            return str;

        else if (str.length() % 3 == 1) {
            for (short i = 3; i <= str.length() - 4; i += 4)
                str.insert(i, '-');
            str.insert(str.length() - 2, '-');
            return str;
        }

        else if (str.length() % 3 == 2) {
            for (short i =3; i <= str.length() - 3; i += 4)
                str.insert(i, '-');
            str.insert(str.length() - 2, '-');
            return str;
        }

        else {
            for (short i = 3; i < str.length(); i += 4)
                str.insert(i, '-');
            return str;
        }

    }
}
