/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package utilities;

import javax.swing.*;
import java.awt.*;

public class SpringLayoutUtilities {
    private static SpringLayout.Constraints getConstraintsForCell(int row, int col, Container parent, int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        // 2d to 1d index (i*w)+j
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    public static void createGrid(Container parent, int rows, int cols, int initialX, int initialY, int xPad, int yPad) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        //Align all cells in each column and make them the same width.
        //Push the items east by initialX
        Spring x = Spring.constant(initialX);
        for (int col = 0; col < cols; col++) {
            Spring width = Spring.constant(0);
            for (int row = 0; row < rows; row++) {
                width = Spring.max(width, getConstraintsForCell(row, col, parent, cols).getWidth());
            }
            for (int row = 0; row < rows; row++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(row, col, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        //Align all cells in each row and make them the same height.
        //Push the items south by initialY
        Spring y = Spring.constant(initialY);
        for (int row = 0; row < rows; row++) {
            Spring height = Spring.constant(0);
            for (int col = 0; col < cols; col++) {
                height = Spring.max(height, getConstraintsForCell(row, col, parent, cols).getHeight());
            }
            for (int col = 0; col < cols; col++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(row, col, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        //Set the size of the Container to have all items in it.
        SpringLayout.Constraints constraints = layout.getConstraints(parent);
        constraints.setConstraint(SpringLayout.SOUTH, y);
        constraints.setConstraint(SpringLayout.EAST, x);
    }
}
