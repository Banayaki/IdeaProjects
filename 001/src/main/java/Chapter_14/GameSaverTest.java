package Chapter_14;

import java.io.*;

public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50,"Elf", new String[] {"Bow", "Sword", "Knuckle"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[] {"Hands", "Big axe"});
        GameCharacter three = new GameCharacter(120, "Wizard", new String[] {"Fireboll", "Invisibility"});

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        one = null; two = null; three = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
            GameCharacter oneRes = (GameCharacter) is.readObject();
            GameCharacter twoRes = (GameCharacter) is.readObject();
            GameCharacter threeRes = (GameCharacter) is.readObject();

            System.out.println("тип первого: " + oneRes.getType());
            System.out.println("тип второго: " + twoRes.getType());
            System.out.println("тип третьего: " + threeRes.getType());
        } catch (Exception ex) { ex.printStackTrace();}
    }
}
