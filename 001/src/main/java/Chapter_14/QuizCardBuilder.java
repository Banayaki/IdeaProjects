package Chapter_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardlist;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }

    private void go(){
        //СОздание GUI
        frame = new JFrame("Quiz Card Builder");
        Font bigFont = new Font("saserif", Font.BOLD, 25);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel label = new JLabel("Question:");
        question = new JTextArea(6,10);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);
        JLabel label1 = new JLabel("Answer:");
        answer = new JTextArea(6, 10);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);
        JScrollPane paneForOne = new JScrollPane(question);
        paneForOne.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneForOne.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane paneForTwo = new JScrollPane(answer);
        paneForTwo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneForTwo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton next = new JButton("Next card");
        next.addActionListener(new NextCardListener());
        cardlist = new ArrayList<QuizCard>();

        panel.add(label);
        panel.add(paneForOne);
        panel.add(label1);
        panel.add(paneForTwo);
        panel.add(next);

        frame.getContentPane().add(panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,600);

        //Cодзаем объект JmenuBar и даобвляем в него пункты создать и сохранить
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuNew = new JMenuItem("New ");
        JMenuItem menuSave = new JMenuItem("Save");
        menuNew.addActionListener(new NewMenuListener());
        menuSave.addActionListener(new SaveMenuListener());
        menuFile.add(menuNew);
        menuFile.add(menuSave);
        menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);
    }

    private class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            QuizCard card = new QuizCard(question.getText(),answer.getText());
            cardlist.add(card);
            clearCard();
        }
    }

    private class SaveMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardlist.add(card);
            //Вызыввает диалоговое окно и программа останавливается на этой строке, пока пользователь не выберет меню save
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(frame);
            saveFile(fileChooser.getSelectedFile());
        }
    }
    private void clearCard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private class NewMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            cardlist.clear();
            clearCard();
        }
    }

    private void saveFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (QuizCard card : cardlist) {
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("couldn't write the cardList out");
            ex.printStackTrace();
        }
    }
}
