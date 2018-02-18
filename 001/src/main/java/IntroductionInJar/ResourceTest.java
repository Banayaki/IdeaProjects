package main.java.IntroductionInJar;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class ResourceTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ResourceTestFrame();
                frame.setTitle("Resource Test");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class ResourceTestFrame extends JFrame{
    public ResourceTestFrame(){
        setSize(300,300);
        URL aboutURL = getClass().getResource("icon.jpg");
        Image img = new ImageIcon(aboutURL).getImage();
        setIconImage(img);

        JTextArea textArea = new JTextArea();
        InputStream stream = getClass().getResourceAsStream("about.txt");
        Scanner in = new Scanner(stream);
        while (in.hasNext()){
            textArea.append(in.nextLine() + "\n");
        }
        add(textArea);
    }
}
