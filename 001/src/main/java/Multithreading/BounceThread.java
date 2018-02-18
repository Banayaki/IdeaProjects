package main.java.Multithreading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceThread {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrameThread();
                frame.setTitle("Bounce Thread");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class BallRunnable implements Runnable {
    private Ball ball;
    private Component component;
    public static final int STEPS = 5000;
    public static final int DEALY = 5;

    public BallRunnable(Ball aBall, Component aComponent) {
        ball = aBall;
        component = aComponent;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= STEPS; i++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DEALY);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class BounceFrameThread extends JFrame {
    private BallComponent comp;

    public BounceFrameThread() {
        setTitle("Bounce");

        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonPanel, "Close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
        pack();

    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        Ball ball = new Ball();
        comp.add(ball);

        Runnable r = new BallRunnable(ball, comp);
        Thread t = new Thread(r);
        t.start();
    }
}


