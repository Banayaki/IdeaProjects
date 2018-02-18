package main.java.GeneralProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 49; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> winComb = numbers.subList(0, 6);
        Collections.sort(winComb);
        System.out.println(winComb);
    }
}
