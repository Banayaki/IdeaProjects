import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySort {

    public static void main(String[] args) throws IOException {
        char[] array = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = reader.readLine().toCharArray();
        System.out.println(find(array, ch));
    }

    public static int find(char[] array, char[] ch){
        int res = 0;
        for(int i = 0; i<array.length; i++){
            if (array[i] == ch[0]){
                res = i;
                break;
            } else {
                return -1;
            }
        }
        return res;
    }

}
