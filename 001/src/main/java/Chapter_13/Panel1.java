package Chapter_13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel1 implements ActionListener {

    JTextArea textArea;

    public static void main(String[] args) {
        Panel1 gui = new Panel1();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField field = new JTextField("Ваше имя",20);
        System.out.println(field.getText());
        textArea = new JTextArea(10,20);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.requestFocus();

        JButton button = new JButton("FUCK THE POLICE");
        JButton button1 = new JButton( "Run Forest, run!");
        JButton button2 = new JButton( "lol");
        button.addActionListener(this);


        panel.add(button2);
        panel.add(button1);
        panel.add(button);
        panel.add(field);
        panel.add(scrollPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        textArea.append("Facked! \n");
    }
}
