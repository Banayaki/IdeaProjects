package main.java;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.*;

public class BeatBoxClient {

    public JFrame theFrame;
    private JPanel background;
    private JPanel mainPanel;
    private JList incomingList;
    private JTextArea userMessage;
    private ArrayList<JCheckBox> checkBoxList;
    private int nextNum;
    private Vector<String> listVector = new Vector<>();
    String userName;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();
    private boolean connection;

    private Sequencer sequencer;
    private Sequence sequence;
    private Sequence mySequence = null;
    private Track track;

    private String[] instrumentName = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
            "Low-Mid Tom", "High Agogo", "Open Hi Conga"};
    private int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) throws IOException {
        PrevFrame frame = new PrevFrame();
    }

    public void startUp(String name, boolean b) {
        connection = b;
        userName = name;
        if (connection == true) {
            connection();
        }
        theFrame = new JFrame("BeatBox by Banayaki");
        background = new JPanel();
        setUpMidi();
        buildGUI();
    }

    private void connection() {
        try {
            Socket sock = new Socket("178.163.62.83", 4242);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Couldn't connect - you will have to play alone :C");
        }
    }

    private void buildGUI() {

        background.removeAll();

        theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        BorderLayout layout = new BorderLayout();

        background.setLayout(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        theFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ExitDialog exitDialog = new ExitDialog(theFrame, checkBoxList);
            }
        });

        checkBoxList = new ArrayList<>();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        saveItem.addActionListener(e -> {
            try {
                FileWriter writer = new FileWriter("My new track.txt");
                for (int i = 0; i < 256; i++) {
                    writer.write(checkBoxList.get(i).isSelected() + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JMenuItem saveAsItem = new JMenuItem("Save As");
        saveAsItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.showSaveDialog(theFrame);
            try {
                FileWriter writer = new FileWriter(chooser.getName());
                for (int i = 0; i < 256; i++) {
                    writer.write(checkBoxList.get(i).isSelected() + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        openItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.showOpenDialog(theFrame);
            chooser.setFileFilter(new FileNameExtensionFilter("", "txt"));
            try {
                BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
                for (int i = 0; i < 256; i++) {
                    if (reader.readLine().equals("false")) {
                        checkBoxList.set(i, new JCheckBox("", false));
                    } else {
                        checkBoxList.set(i, new JCheckBox("", true));
                    }
                }
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {
            ExitDialog exitFrame = new ExitDialog(theFrame, checkBoxList);
        });

        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu optionsMenu = new JMenu("Option's");
        optionsMenu.setMnemonic('O');

        JRadioButtonMenuItem connectionItem = new JRadioButtonMenuItem("Connect", connection);
        connectionItem.addActionListener(e -> {
            if (connectionItem.isSelected()) {
                connection = true;
                connection();
                buildGUI();
            } else connection = false;
            buildGUI();
        });
        optionsMenu.add(connectionItem);

        optionsMenu.addSeparator();

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> {
            for (int i = 0; i < 256; i++) {
                checkBoxList.set(i, new JCheckBox("", false));
            }
            fillCheckBox();
        });
        optionsMenu.add(clearItem);

        JMenuItem themeItem = new JMenuItem("Theme");
        themeItem.addActionListener(e -> {
            ThemeFrame themeFrame = new ThemeFrame(theFrame);
        });
        optionsMenu.add(themeItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        theFrame.setJMenuBar(menuBar);

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        start.addActionListener(new StartListener());
        buttonBox.add(start);


        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopListener());
        buttonBox.add(stop);

        if (connection == true) {
            JButton sendIt = new JButton("Send");
            sendIt.addActionListener(new SendItListener());
            buttonBox.add(sendIt);
        }

        JButton random = new JButton("Random");
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox check;
                for (int i = 0; i < 256; i++) {
                    check = new JCheckBox();
                    check.setSelected(new Random().nextBoolean());
                    checkBoxList.set(i, check);
                }
                fillCheckBox();
            }
        });
        buttonBox.add(random);

        JSlider tempSlider = new JSlider(0, 100, 10);
        tempSlider.setPaintTicks(true);
        tempSlider.setPaintLabels(true);
        tempSlider.setMajorTickSpacing(20);
        tempSlider.setMinorTickSpacing(5);
        tempSlider.addChangeListener(e -> sequencer.setTempoFactor((float) tempSlider.getValue() / 10));
        buttonBox.add(tempSlider);

        if (connection == true) {
            userMessage = new JTextArea();
            JScrollPane userMassageScroll = new JScrollPane(userMessage);
            userMassageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            userMassageScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            buttonBox.add(userMassageScroll);

            incomingList = new JList();
            incomingList.addListSelectionListener(new SelectionListener());
            incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane theList = new JScrollPane(incomingList);
            theList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            theList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            buttonBox.add(theList);
            incomingList.setListData(listVector);
        }

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentName[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);
        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setVgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setLocationRelativeTo(null);
        background.revalidate();
        background.repaint();
        theFrame.setVisible(true);
    }

    private void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void buildTrackAndStart() {
        ArrayList<Integer> trackList;
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new ArrayList<>();
            for (int j = 0; j < 16; j++) {
                JCheckBox jc = checkBoxList.get(j + (16 * i));
                if (jc.isSelected()) {
                    int key = instruments[i];
                    trackList.add(key);
                } else {
                    trackList.add(null);
                }
            }
            makeTracks(trackList);
        }
        track.add(makeEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    public class StopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    public class SendItListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkBoxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox check = checkBoxList.get(i);
                if (check.isSelected()) {
                    checkBoxState[i] = true;
                }
            }
            String messageToSend = null;
            try {
                out.writeObject(userName + ": " + userMessage.getText());
                out.writeObject(checkBoxState);
            } catch (Exception ex) {
                System.out.println("Could not send it to the serever");
            }
            userMessage.setText("");
        }
    }

    public class SelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    boolean[] selectedState = otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        }
    }

    public class RemoteReader implements Runnable {
        boolean[] checkBoxState = null;
        String nameToShow = null;
        Object obj = null;

        @Override
        public void run() {
            try {
                while ((obj = in.readObject()) != null) {
                    System.out.println("Got an object from server");
                    System.out.println(obj.getClass());
                    String nameToShow = (String) obj;
                    checkBoxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkBoxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void changeSequence(boolean[] checkBoxState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox check = checkBoxList.get(i);
            if (checkBoxState[i]) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
        }
    }

    private void makeTracks(ArrayList list) {
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) it.next();
            if (num != null) {
                int numKey = num;
                track.add(makeEvent(144, 9, numKey, 100, i));
                track.add(makeEvent(128, 9, numKey, 100, i + 1));
            }
        }
    }

    private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    private void fillCheckBox() {
        mainPanel.removeAll();
        JCheckBox check;
        for (int i = 0; i < 256; i++) {
            check = new JCheckBox();
            check.setSelected(checkBoxList.get(i).isSelected());
            mainPanel.add(check);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

