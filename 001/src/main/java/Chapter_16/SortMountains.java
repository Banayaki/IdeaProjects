package main.java.Chapter_16;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SortMountains {
    LinkedList<Mountain> mtn = new LinkedList<Mountain>();

    class NameCompare implements Comparator<Mountain> {
        @Override
        public int compare(Mountain o1, Mountain o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    class HeightCompare implements Comparator<Mountain> {
        @Override
        public int compare(Mountain o1, Mountain o2) {
            return o2.height-o1.height;
        }
    }

    public static void main(String[] args) {
        new SortMountains().go();
    }

    public void go() {
        mtn.add(new Mountain("Лонг-Рейндж", 14255));
        mtn.add(new Mountain("Эльберт", 14433));
        mtn.add(new Mountain("Марун", 14156));
        mtn.add(new Mountain("Касл", 14265));

        System.out.println("В порядке добавления: \n" + mtn);
        NameCompare nc = new NameCompare();
        Collections.sort(mtn, nc);
        System.out.println("В алфовитном порядке: \n" + mtn);
        HeightCompare hc = new HeightCompare();
        Collections.sort(mtn, hc);
        System.out.println("По высоте: \n" + mtn);
    }
}

class Mountain {
    String name;
    int height;

    public Mountain(String name, int height){
        this.name = name;
        this.height = height;
    }
    public String toString(){
        return name + " " + height;
    }
}
