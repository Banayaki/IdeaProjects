package Chapter_14;


import java.io.*;

public class DungeonGame implements Serializable {

    public int x = 3;
    transient long y = 4;
    private short z = 5;

    public static void main(String[] args) {
        DungeonGame d = new DungeonGame();
        System.out.println(d.getX() + d.getY() + d.getZ());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dg.ser"));
            oos.writeObject(d);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dg.ser"));
            d = (DungeonGame) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(d.getX() + d.getY() + d.getZ());
    }

    int getX(){
        return x;
    }
    long getY(){
        return y;
    }
    short getZ(){
        return z;
    }
}
