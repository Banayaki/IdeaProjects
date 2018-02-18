package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class FontTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new FontFrame();
                frame.setTitle("Font Test");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class FontFrame extends JFrame {
    public FontFrame() {
        add(new FontComponent());
        pack();
    }
}

class FontComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        String message = "I love you Kristina";

        Font font = new Font("Serif", Font.BOLD, 36);
        g2.setFont(font);

        //определить размеры текстового сообщения
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(message, context);

        //определить координаты верхнего левого угла текста
        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;

        //сложить подъем с координатой yб что бы достигнуть базовой линии
        double ascent = - bounds.getY();
        double baseY = y + ascent;

        //вывести текстовое сообщение
        g2.drawString(message, (int) x, (int) baseY);
        g2.setPaint(Color.LIGHT_GRAY);

        //нарисовать базовую линию
        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

        //нарисовать ограничивающий прямоугольник
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        g2.draw(rectangle2D);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}