import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class exercise001 {
    JFrame frame;
    JButton b;

    public static void main(String[] args) {
        exercise001 gui = new exercise001();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b = new JButton("A");
        b.addActionListener(new BListener());

        frame.getContentPane().add(BorderLayout.SOUTH, b);
        frame.setSize(200,100);
        frame.setVisible(true);
    }

    class BListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (b.getText().equals("A")) {
                b.setText("B");
            } else {
                b.setText("A");
            }
        }
    }
}
