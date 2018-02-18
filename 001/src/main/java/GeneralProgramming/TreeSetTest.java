package main.java.GeneralProgramming;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

    public static void main(String[] args) {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget",4517));
        parts.add(new Item("Modem", 8878));
        System.out.println(parts);

        SortedSet<Item> sortedSetByDesc = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                String descrA = o1.getDescription();
                String descrB = o2.getDescription();
                return descrA.compareTo(descrB);
            }
        });

        sortedSetByDesc.addAll(parts);
        System.out.println(sortedSetByDesc);
    }
}
