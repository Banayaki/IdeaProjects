package main.java.GeneralProgramming;

import javafx.util.Pair;

public class PairTestFirst {
    public static void main(String[] args) {
        String[] words = { "Mary", "had", "a", "little", "lamb"};
        Pair mm = ArrayAlgFirst.minmax(words);
        System.out.println("min = " + mm.getKey());
        System.out.println("max = " + mm.getValue());
    }
}

class ArrayAlgFirst{
    //Получает символьные строи с минимальными и максимальными значениями
    //Среди элементов массива

    public static Pair<String, String> minmax(String[] a){
        if ( a == null || a.length == 0) return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }
}
