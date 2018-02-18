import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GaidelLabGUI {
    public JFrame frame;
    private JPanel panel;
    private JFileChooser chooser;
    private FileReader inFile;

    public static void main(String[] args) {
        GaidelLabGUI gui = new GaidelLabGUI();

        //inFile.close();
    }

    public GaidelLabGUI() {
        frame = new JFrame();
        panel = new JPanel();
        chooser = new JFileChooser();
        buildGUI();
    }

    private void buildGUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton openFile = new JButton("Open file");
        openFile.isDefaultButton();
        openFile.addActionListener(e -> {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("C:\\Users\\Banayaki\\Desktop\\tests"));
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
            chooser.addChoosableFileFilter(filter);
            chooser.showOpenDialog(panel);

            try {
                inFile = new FileReader(chooser.getSelectedFile());
                System.out.println("Fine");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        });
        panel.add(openFile);
        frame.setContentPane(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
