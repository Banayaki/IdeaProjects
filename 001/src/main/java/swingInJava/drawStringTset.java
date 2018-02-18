package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;

public class drawStringTset {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new NotHelloWorldFrame();
                frame.setTitle("NotHelloWorld");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame(){
        add(new NotHelloWorldComponent());
        pack();
    }
}

class NotHelloWorldComponent extends JComponent{
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @Override
    public void paintComponent(Graphics g){
        g.drawString("Not a Hello, world program", MESSAGE_X, MESSAGE_Y);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
