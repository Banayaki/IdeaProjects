package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

public class OptionDialogTest extends JFrame {
    private ButtonPanel typePanel;
    private ButtonPanel messagePanel;
    private ButtonPanel messageTypePanel;
    private ButtonPanel optionTypePanel;
    private ButtonPanel optionsPanel;
    private ButtonPanel inputPanel;
    private String messageString = "Message";
    private Icon messageIcon = new ImageIcon("C:\\Users\\Banayaki\\Downloads\\icons8-Деньги-50.png");
    private Object messageObject = new Date();
    private Component messageComponent = new SampleComponent();

    public static void main(String[] args) {
        OptionDialogTest ts = new OptionDialogTest();
    }

    public OptionDialogTest() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 3));

        typePanel = new ButtonPanel("Type", "Message", "Confirm", "Option", "Input");
        messageTypePanel = new ButtonPanel("Message Type", "ERROR_MESSAGE", "INFORMATION-MESSAGE", "WARNING_MESSAGE",
                "QUESTION_MESSAGE", "PLAIN_MESSAGE");
        messagePanel = new ButtonPanel("Message", "String", "Icon", "Component", "Other", "Object[]");
        optionTypePanel = new ButtonPanel("Confirm", "DEFAULT_OPTIONS", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION",
                "OK_CANCEL_OPTION");
        optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
        inputPanel = new ButtonPanel("Input", "Text field", "Combo box");

        gridPanel.add(typePanel);
        gridPanel.add(messageTypePanel);
        gridPanel.add(messagePanel);
        gridPanel.add(optionTypePanel);
        gridPanel.add(optionsPanel);
        gridPanel.add(inputPanel);

        JPanel showPanel = new JPanel();
        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ShowAction());
        showPanel.add(showButton);

        add(gridPanel, BorderLayout.CENTER);
        add(showPanel, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public Object getMessage() {
        String s = messagePanel.getSelection();
        if (s.equals("String")) return messageString;
        else if (s.equals("Icon")) return messageIcon;
        else if (s.equals("Component")) return messageComponent;
        else if (s.equals("Object[]")) return new Object[]{messageString, messageIcon, messageComponent};
        else if (s.equals("Other")) return messageObject;
        else return null;
    }

    public Object[] getOptions() {
        String s = optionsPanel.getSelection();
        if (s.equals("String[]")) return new String[]{"Yellow", "Red", "Green"};
        else if (s.equals("Icon[]")) return new Icon[]{null};
        else if (s.equals("Object[]")) return new Object[]{messageComponent, messageString, messageIcon};
        else return null;
    }

    public int getType(ButtonPanel panel) {
        String s = panel.getSelection();
        try {
            return JOptionPane.class.getField(s).getInt(null);
        } catch (Exception ex) {
            return -1;
        }
    }

    private class ShowAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (typePanel.getSelection().equals("Confirm")) {
                JOptionPane.showConfirmDialog(OptionDialogTest.this, getMessage(), "Title", getType(optionTypePanel),
                        getType(messageTypePanel));
            } else if (typePanel.getSelection().equals("Input")) {
                if (inputPanel.getSelection().equals("TextField")) {
                    JOptionPane.showInputDialog(OptionDialogTest.this, getMessage(), "Title", getType(messageTypePanel));
                } else {
                    JOptionPane.showInputDialog(OptionDialogTest.this, getMessage(), "Title",
                            getType(messageTypePanel), null, new String[]{"color", "folor"}, "color");
                }
            } else if (typePanel.getSelection().equals("Message")) {
                JOptionPane.showMessageDialog(OptionDialogTest.this, getMessage(), "Title",
                        getType(messageTypePanel));
            } else if (typePanel.getSelection().equals("Option")) {
                JOptionPane.showOptionDialog(OptionDialogTest.this, getMessage(), "Title",
                        getType(optionTypePanel), getType(messageTypePanel), null, getOptions(), getOptions()[0]);
            }
        }
    }

    class SampleComponent extends JComponent {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
            g2.setPaint(Color.YELLOW);
            g2.fill(rect);
            g2.setPaint(Color.BLACK);
            g2.draw(rect);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(10,10);
        }
    }
}
