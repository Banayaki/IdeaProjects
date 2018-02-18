package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AllForGui {

    public static void makeButton(Container c, String title, ActionListener listener){
        JButton button = new JButton(title);
        button.addActionListener(listener);
        c.add(button);
    }

    public static void makeButton(Container c, String title, char mnemonic, ActionListener listener){
        JButton button = new JButton(title);
        button.addActionListener(listener);
        button.setMnemonic(mnemonic);
        c.add(button);
    }
}
