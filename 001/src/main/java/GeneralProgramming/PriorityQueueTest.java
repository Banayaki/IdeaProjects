package main.java.GeneralProgramming;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<GregorianCalendar> pq = new PriorityQueue();
        pq.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9));
        pq.add(new GregorianCalendar(1999, Calendar.MARCH, 15));
        pq.add(new GregorianCalendar(2001, Calendar.JANUARY, 1));
        pq.add(new GregorianCalendar(1954, Calendar.JULY, 30));

        System.out.println("Iterating over elements...");
        for (GregorianCalendar date : pq) {
            System.out.println(date.get(Calendar.YEAR));
        }
        System.out.println("Removing elements...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove().get(Calendar.YEAR));
        }
    }
}
