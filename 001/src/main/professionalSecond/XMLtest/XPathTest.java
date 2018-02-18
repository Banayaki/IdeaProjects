package main.professionalSecond.XMLtest;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class XPathTest {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new XPathFrame();
                frame.setTitle("Test");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class XPathFrame extends JFrame {
    private DocumentBuilder builder;
    private Document document;
    private XPath path;
    private JTextField expression;
    private JTextField result;
    private JTextArea docText;
    private JComboBox<String> typeCombo;

    public XPathFrame() {

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evaluate();
            }
        };

        expression = new JTextField(20);
        expression.addActionListener(listener);
        JButton evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(listener);

        typeCombo = new JComboBox<>(new String[]{"STRING", "NODE", "NODESET", "NUMBER", "BOOLEAN"});
        typeCombo.setSelectedItem("STRING");

        JPanel panel = new JPanel();
        panel.add(expression);
        panel.add(typeCombo);
        panel.add(evaluateButton);
        docText = new JTextArea(10, 40);
        result = new JTextField();
        result.setBorder(new TitledBorder("Result"));

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(docText), BorderLayout.CENTER);
        add(result, BorderLayout.SOUTH);

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(this, e);
        }

        XPathFactory xPathFactory = XPathFactory.newInstance();
        path = xPathFactory.newXPath();
        pack();
    }

    public void openFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("xpath"));
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML files";
            }
        });

        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION) return;
        File file = chooser.getSelectedFile();

        try {
            docText.setText(new String(Files.readAllBytes(file.toPath())));
            document = builder.parse(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e);
        } catch (SAXException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void evaluate() {

        try {

            String typeName = (String) typeCombo.getSelectedItem();
            QName returnType = (QName) XPathConstants.class.getField(typeName).get(null);
               Object evarResult = path.evaluate(expression.getText(), document, returnType);

            if (typeName.equals("NODESET")) {

                NodeList list = (NodeList) evarResult;
                StringBuilder builder = new StringBuilder();
                builder.append("{");

                for (int i = 0; i < list.getLength(); i++) {
                    if (i > 0) builder.append(", ");
                    builder.append("" + list.item(i));
                }
                builder.append("}");
                result.setText("" + builder);
            } else result.setText("" + evarResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}