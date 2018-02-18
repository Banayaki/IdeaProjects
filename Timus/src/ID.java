import java.util.ArrayList;
import java.util.Scanner;

public class ID {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Long> array = new ArrayList<>();
        while (in.hasNextLong()) {
            array.add(in.nextLong());
        }
        for (int i = 0; i < array.size(); i++) {
            System.out.println((Math.sqrt(array.get(array.size() - i - 1))));
        }

    }
}
