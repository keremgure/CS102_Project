/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package utilities;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public abstract class CommonUtilities {
    public static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException e) {
            System.err.println("formatter is bad: " + e.getMessage());
        }
        return formatter;
    }

    public static JSpinner createDecimalSpinner(String pat, double initial, double min, double max, double stepSize) {
        //Create a Spinner model with given values.
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(initial, min, max, stepSize);
        JSpinner spinner = new JSpinner(spinnerNumberModel);

        //Get the editor to access the field of spinner.
        JFormattedTextField field = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        field.setFont(new Font("Roboto", Font.PLAIN, 12));

        //Format the field.
        DecimalFormat decimalFormat = new DecimalFormat(pat);
        NumberFormatter formatter = (NumberFormatter) field.getFormatter();
        formatter.setFormat(decimalFormat);
        formatter.setAllowsInvalid(false);
        return spinner;
    }

    public static String getListAsString(List list){
        String res = "";
        for(Object obj : list){
            res += obj + "\n";
        }
        return res;
    }
}
