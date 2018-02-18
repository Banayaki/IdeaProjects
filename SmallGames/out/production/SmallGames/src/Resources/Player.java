package Resources;

public class Player {

    private static String name;
    private static char[] password;

    public Player(String name, char[] password){
        this.name = name;
        this.password = password;
    }

    public static String getName() {
        return name;
    }
}
