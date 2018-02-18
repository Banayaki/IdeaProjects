package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;

public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SizedFrame();
                frame.setTitle("SizedFrame");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SizedFrame extends JFrame {
    public SizedFrame() {

        //получить размер экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        //задать ширину и высоту фрейма, предоставив платформе выбрать местоположение фрейма
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        //задать пиктограмму для фрейма

        setIconImage(new ImageIcon("C:\\Users\\Banayaki\\Downloads\\earth-globe-with-continents-maps.png").getImage());
    }
}