package main.java.professional_first;

import java.util.Arrays;
import java.util.Scanner;

public class LotteryDrawing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("How many numbers do you need to draw?");
        int k = in.nextInt();

        System.out.println("What us the highest number you can draw?");
        int n = in.nextInt();

        //заполняем массив числами
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        //выбрать k номеров и поместить их во второй массив
        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            //получить случайный индекс в пределах от 0 до n-1
            int r = (int) (Math.random() * n);
            //выбрать элемент из произвольного места
            result[i] = numbers[r];
            //переместить последний элемент в произвольное место
            numbers[r] = numbers[n - 1];
            n--;
        }

        Arrays.sort(result);
        System.out.println("Bet the following combination. It will make toy rich!");
        for (int r : result) {
            System.out.println(r);
        }
    }
}
