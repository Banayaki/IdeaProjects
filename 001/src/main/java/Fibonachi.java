import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonachi {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        if(n == 0) {
            System.out.println("0");
        }
        if (n == 1) {
            System.out.println("1");
        }
        if (n > 1) {

            double phi = (Math.sqrt(5) + 1)/2;
            double result = (Math.pow(phi, n)/Math.sqrt(5) + 0.5);
            Math.round(result);
            System.out.println(result);

        } else {
            return;
        }
    }
}
