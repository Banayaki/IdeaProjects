package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner){
        super(owner, "About DialogTest", true);

        add(new JLabel(
                "<html><h1><i>Core Java</i></h1><hr>" +
                        "By Artem Banayaki Mukhin</html>"), BorderLayout.CENTER);

        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);

        pack();
    }
}
