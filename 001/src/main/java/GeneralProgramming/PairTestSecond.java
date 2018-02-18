package main.java.GeneralProgramming;

import javafx.util.Pair;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PairTestSecond {
    public static void main(String[] args) {
        GregorianCalendar[] birthdays ={
                new GregorianCalendar(1906, Calendar.DECEMBER, 9),
                new GregorianCalendar(1815, Calendar.DECEMBER, 10),
                new GregorianCalendar(1903, Calendar.DECEMBER, 3),
                new GregorianCalendar(1910, Calendar.JUNE, 22)
        };
        Pair<GregorianCalendar, GregorianCalendar> mm = ArrayAlgSecond.minmax(birthdays);
        System.out.println("min = " + mm.getKey().getTime());
        System.out.println("max = " + mm.getValue().getTime());
    }
}

class ArrayAlgSecond {
    public static <T extends Comparable> Pair<T, T> minmax(T[] a){
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min,max);
    }
}