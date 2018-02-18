package main.java.swingInJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxTest extends JFrame{
    private JComboBox<String> faceCombo;
    private JLabel label;
    private static final int DEFAULT_SIZE = 24;

    public ComboBoxTest(){
        JFrame frame = new JFrame();
        //добавить образец текстовой метки
        label = new JLabel("Some text for test");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        frame.add(label, BorderLayout.CENTER);

        //Составить комбинированный список и ввести в него названия начертаний шрифта
        faceCombo = new JComboBox<>();
        faceCombo.addItem("Serif");
        faceCombo.addItem("SansSerif");
        faceCombo.addItem("Monospaced");
        faceCombo.addItem("Dialog");
        faceCombo.addItem("DialogInput");

        //приемник событий от комбинированного списка
        //изменяет на выбранное начертание шрифта
        faceCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()), Font.PLAIN, DEFAULT_SIZE));
            }
        });

        //ввести список на панели в нижней части фрейма
        JPanel comboPanel = new JPanel();
        comboPanel.add(faceCombo);
        frame.add(comboPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ComboBoxTest test = new ComboBoxTest();
    }
}
