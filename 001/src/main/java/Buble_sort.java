public class Buble_sort {

    public static void main(String[] args) {

        int[] array = {5, 1, 8, 6, 7, 4};
        for (int i = 0; i<array.length; i++){
            for (int temp = 0; temp<array.length-1; temp++){
                if (array[i] < array[temp]){
                    int x = array[i];
                    array[i] = array[temp];
                    array[temp] = x;
                }
            }
        }

        for (int v : array){
            System.out.println(v);
        }

    }
}
