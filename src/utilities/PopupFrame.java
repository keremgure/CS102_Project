/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package utilities;

import javax.swing.*;

public abstract class PopupFrame extends JFrame {
    public PopupFrame() {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public PopupFrame(String title) {
        this();
        setTitle(title);
    }

    public JFrame getFrame() {
        return this;
    }
}
