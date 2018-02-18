package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;

public class ImageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ImageFrame();
                frame.setTitle("Image Test");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class ImageFrame extends JFrame {
    public ImageFrame() {
        add(new ImageComponent());
        pack();
    }
}

class ImageComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private Image image;

    public ImageComponent() {
        image = new ImageIcon("C:\\Users\\Banayaki\\Downloads\\icons8-Деньги-50.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (image == null) return;

        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        //воспроизвести изображение в левом верхнем углу
        g.drawImage(image, 0, 0, null);

        for(int i = 0; i * imageWidth <= getWidth(); i++){
            for (int j = 0; j * imageHeight < getHeight(); j++) {
                if (i + j > 0) {
                 g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}