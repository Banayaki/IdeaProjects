package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int limit = in.nextInt();

        for (int i = 0; i < limit; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int divider = nod(x, y);
            int multiply = nok(x, y);
            System.out.println("(" + divider + " " + multiply + ") ");
        }


    }

    private static int nok(int x, int y) {
        ArrayList<Integer> first = findNodk(x);
        ArrayList<Integer> second = findNodk(y);
        ArrayList<Integer> nods;
        int res = 1;

        if (first.size() > second.size()) nods = checkForNonRepeat(second, first);
        else nods = checkForNonRepeat(first, second);

        for (int number : nods) {
            res *= number;
        }
        return res;
    }

    private static ArrayList<Integer> checkForNonRepeat(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> res = new ArrayList<>();
        res.addAll(second);
        for (int firstNum : first) {
            for (int i = 0; i < second.size(); i++) {
                if (firstNum == second.get(i)) {
                    second.set(i, 0);
                    break;
                }
                if (i == second.size() - 1) res.add(firstNum);
            }
        }
        return res;
    }

    private static int nod(int x, int y) {
        ArrayList<Integer> first = findNodk(x);
        ArrayList<Integer> second = findNodk(y);
        ArrayList<Integer> nods;
        int res = 1;

        if (first.size() > second.size()) nods = checkForRepeat(first, second);
        else nods = checkForRepeat(second, first);

        for (int number : nods) {
            res *= number;
        }
        return res;
    }

    private static ArrayList<Integer> checkForRepeat(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int firstNum : first) {
            for (int i = 0; i < second.size(); i++) {
                if (firstNum == second.get(i)) {
                    res.add(firstNum);
                    second.set(i, 0);
                    break;
                }
            }
        }
        return res;
    }

    private static ArrayList<Integer> findNodk(int x) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 2; i <= x; ++i) {
            if (x == 1) break;
            else if (x % i == 0) {
                x /= i;
                result.add(i);
                i = 1;
            }
        }
        return result;
    }
}
