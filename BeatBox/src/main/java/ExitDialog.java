package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExitDialog extends JDialog {

    public ExitDialog(JFrame parent, ArrayList<JCheckBox> checkBoxList){
        super(parent, "Yes or no?", true);
        JLabel exitOrNo = new JLabel("Save the last track?");
        this.add(BorderLayout.CENTER, exitOrNo);
        JPanel buttonGroup = new JPanel();

        try {
            FileWriter writer = new FileWriter(new File("Options.txt"));
            writer.write(UIManager.getLookAndFeel().getClass().getName());
            writer.close();
        } catch (IOException ex) { ex.printStackTrace(); }

        JButton yesButton = new JButton("Yes");
        yesButton.setMnemonic('Y');
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("My new track.txt");
                    for (int i = 0; i < 256; i++) {
                        writer.write(checkBoxList.get(i).isSelected() + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton noButton = new JButton("No");
        noButton.setMnemonic('N');
        noButton.addActionListener(e12 -> System.exit(0));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('C');
        cancelButton.addActionListener(e13 -> this.setVisible(false));
        buttonGroup.setLayout(new FlowLayout());
        buttonGroup.add(yesButton);
        buttonGroup.add(noButton);
        buttonGroup.add(cancelButton);
        this.add(BorderLayout.SOUTH, buttonGroup);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
