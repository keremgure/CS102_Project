/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package utilities;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JTableSpinnerColumn {
    private JTable table;
    private int column;

    public JTableSpinnerColumn(JTable table, int column) {
        this.table = table;
        this.column = column;
        table.getColumnModel().getColumn(column).setCellEditor(new SpinnerEditor());
        table.getColumnModel().getColumn(column).setCellRenderer(new SpinnerRenderer());
    }


    private class SpinnerRenderer extends JSpinner implements TableCellRenderer {
        private SpinnerRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            SpinnerModel sm;
            if (value instanceof SpinnerModel) {
                sm = (SpinnerModel) value;
            } else {
                JSpinner sp = (JSpinner) value;
                sm = sp.getModel();
            }
            setModel(sm);
            return this;
        }
    }

    private class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private JSpinner spinner;

        private SpinnerEditor() {
            spinner = new JSpinner();
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            SpinnerModel sm;
            if (value instanceof SpinnerModel) {
                sm = (SpinnerModel) value;
            } else {
                JSpinner sp = (JSpinner) value;
                sm = sp.getModel();
            }
            spinner.setModel(sm);
            return spinner;
        }

        public Object getCellEditorValue() {
            return spinner.getModel();
        }
    }
}