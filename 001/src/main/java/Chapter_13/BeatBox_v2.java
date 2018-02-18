package main.java.Chapter_13;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class BeatBox_v2 implements Serializable {

    //Объявление пременных, создание массива наименований и интсрументов
    JPanel mainPanel;
    ArrayList<JCheckBox> checkBoxArrayList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;
    String[] instrumentName = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
    "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
    "Low-Mid Tom", "High Agogo", "Open Hi Conga"};
    int [] instruments = {35, 42, 46, 38, 49 ,39 ,50 ,60 ,70 ,72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox_v2().go();
    }

    public void go(){
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        //Создаем пустую границу, что бы создать поля между карями
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //Создание кнопок и присввание им места на панели
        checkBoxArrayList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Up Tempo");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Down Tempo");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton serializeIt = new JButton("SerializeIt");
        serializeIt.addActionListener(new Serializeit());
        buttonBox.add(serializeIt);

        JButton restore = new JButton("Restore");
        restore.addActionListener(new restoreListener());
        buttonBox.add(restore);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i<16; i++){
            nameBox.add(new Label(instrumentName[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(2);
        mainPanel = new JPanel(gridLayout);
        background.add(BorderLayout.CENTER, mainPanel);

        //создаем и добавялем флажки
        for(int i = 0; i<256; i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxArrayList.add(c);
            mainPanel.add(c);
        }

        setUpMidi();

        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);
    }
    //Создание дорожки и т.д.
    public void setUpMidi(){
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {e.printStackTrace();}
    }

    public void buildTrackStart(){
        //Создаем массив для хранение инструментов
        int[] trackList = null;
        //Избавляемся от старой дорожки
        sequence.deleteTrack(track);
        track = sequence.createTrack();
        //Задаем клавиши для каждого ряда
        for (int i = 0; i<16; i++){
            trackList = new int[16];
            int key = instruments[i];
            //Для каждого стобца
            for (int j = 0; j < 16 ; j++) {
                JCheckBox jc = (JCheckBox) checkBoxArrayList.get(j + (16*i));
                //Если есть флажок то добавялем в трэк
                if (jc.isSelected()){
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }
            //Создаем событие и добавляем на дорожку
            makeTracks(trackList);
            track.add(makeEvent(176,1,127,0,16));
        }

        track.add(makeEvent(192,9,1,0,15));
        try {
            sequencer.setSequence(sequence);
            //Задает количество повторений, в нашем случае непрерывный цикл
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {e.printStackTrace();}
    }
    //Слушатели для кнопок
    public class MyStartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackStart();
        }
    }

    public class MyStopListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            sequencer.stop();
        }
    }

    public class MyUpTempoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
    }

    public class MyDownTempoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 0.97));
        }
    }
    //Метод создает события для одного инструмента за один цикл
    public void makeTracks(int[] list){
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            //Событие включения и выклюения
            if(key !=0){
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd,chan,one,two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) { e.printStackTrace();}
        return event;
    }

    public class Serializeit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            boolean[] checkboxState = new boolean[256];
            for(int i = 0; i<256; i++){
                JCheckBox check = (JCheckBox) checkBoxArrayList.get(i);
                if(check.isSelected()){
                    checkboxState[i] = true;
                }
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(theFrame);
            saveFile(fileChooser.getSelectedFile(),checkboxState);
        }

            /*Один единственный перезаписываемый сэйв
               ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Save.ser"));
               os.writeObject(checkboxState);
               os.close();*/
    }

    public class restoreListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkBoxState = null;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(theFrame);
            checkBoxState = loadFile(fileChooser.getSelectedFile());
            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkBoxArrayList.get(i);
                if (checkBoxState[i]){
                    check.setSelected(true);
                } else {
                    check.setSelected(false);
                }
            }
            sequencer.stop();
            buildTrackStart();
        }
    }

    private boolean[] loadFile(File file){
        boolean[] array = new boolean[256];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0;i<256;i++){
                if(reader.readLine().equals("true")){
                    array[i] = true;
                    //System.out.println("I think it's truth");
                } else { array[i] = false;
                    //System.out.println("You facked up!");
                }
            }
        } catch (Exception ex) {ex.printStackTrace();    }
        return array;
    }

    private void saveFile(File file, boolean[] bool){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (boolean x : bool) {
                if (x == true){
                    writer.write("true" + "\n");
                } else {
                    writer.write("false" + "\n");
                }
            } writer.close();
            System.out.println("Save was be success");
        } catch (IOException ex) {ex.printStackTrace();}
    }
}
