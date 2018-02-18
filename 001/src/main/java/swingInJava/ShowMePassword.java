package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMePassword {

    private JLabel name = new JLabel("User name: ");
    private JLabel password = new JLabel("Password: ");
    private JTextField nameField;
    private JPasswordField passField;
    private JTextArea showMe;
    private JButton ins;

    public static void main(String[] args) {
        ShowMePassword gui = new ShowMePassword();
        gui.go();
    }

    private void go(){
        JFrame frame = new JFrame();
        frame.setTitle("Your username and your password");
        frame.setSize(500,500);

        JPanel panel = new JPanel();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        northPanel.add(name);
        nameField = new JTextField(20);
        northPanel.add(nameField);
        northPanel.add(password);
        passField = new JPasswordField(20);
        northPanel.add(passField);
        panel.add(northPanel, BorderLayout.NORTH);

        showMe = new JTextArea(10, 40);
        panel.add(showMe, BorderLayout.CENTER);

        ins = new JButton("SHOW ME");
        ins.addActionListener(new showListener());

        panel.add(ins, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class showListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showMe.setText("User name: " + nameField.getText() + " Password: " + new String(passField.getPassword()));
        }
    }
}
