package Chapter_14;

import java.io.FileWriter;
import java.io.IOException;

public class WriteAFile {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("FirstTestSaver.txt");
            writer.write("Приветики");
            writer.close();
        } catch (IOException ex) { ex.printStackTrace();}
    }
}
