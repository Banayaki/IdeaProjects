package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;

public class ToolBarTest {

    ToolBarTest(){
        JToolBar bar = new JToolBar("Tool bar", SwingConstants.VERTICAL);
        bar.add(new JButton(new ImageIcon("C:\\Users\\Banayaki\\Downloads\\icons8-Деньги-50.png")));
        bar.addSeparator();
        bar.add(new JButton(new ImageIcon("C:\\Users\\Banayaki\\Downloads\\icons8-Деньги-50.png")));
        JFrame frame = new JFrame();
        frame.add(bar, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ToolBarTest test = new ToolBarTest();
    }
}
