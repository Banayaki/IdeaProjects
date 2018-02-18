package main.professionalSecond.XMLtest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GridBagTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFileChooser chooser = new JFileChooser("read");
                chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                JFrame frame = new FontFrame(file);
                frame.setTitle("GridBag Test");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class FontFrame extends JFrame {
    private GridBagPane gridBagPane;
    private JComboBox<String> face;
    private JComboBox<String> size;
    private JCheckBox bold;
    private JCheckBox italic;

    @SuppressWarnings("unchecked")
    public FontFrame(File file) {

        gridBagPane = new GridBagPane(file);
        add(gridBagPane);

        face = (JComboBox<String>) gridBagPane.get("face");
        size = (JComboBox<String>) gridBagPane.get("size");
        bold = (JCheckBox) gridBagPane.get("bold");
        italic = (JCheckBox) gridBagPane.get("italic");

        face.setModel(new DefaultComboBoxModel<String>(new String[]{"Serif", "SansSerif", "Monospaced",
                "Dialog", "DialogInput"}));

        size.setModel(new DefaultComboBoxModel<String>(new String[]{"8", "10", "12", "15", "18", "24", "36", "48"}));

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSample();
            }
        };

        face.addActionListener(listener);
        size.addActionListener(listener);
        bold.addActionListener(listener);
        italic.addActionListener(listener);

        setSample();
        pack();
    }

    public void setSample(){

        String fontFace = face.getItemAt(face.getSelectedIndex());
        int fontSize = Integer.parseInt(size.getItemAt(size.getSelectedIndex()));
        JTextArea sample = (JTextArea) gridBagPane.get("sample");
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (italic.isSelected() ? Font.ITALIC : 0);

        sample.setFont(new Font(fontFace, fontStyle, fontSize));
        sample.repaint();
    }
}