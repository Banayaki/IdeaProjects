package main.java.professional_first;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {
    public static void main(String[] args) {
        // построить объекты d текущей даты
        GregorianCalendar calendar = new GregorianCalendar();
        //Locale.setDefault(Locale.US);

        int today = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.print("Today is: " + today);
        int month = calendar.get(Calendar.MONTH);
        System.out.println("." + (month+1));

        //установить объект d на начало месяца
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);

        // получить первый день недели (понедельинк)
        int firstDayOfWeek = calendar.getFirstDayOfWeek();

        // определить отступ, требующийся первой строке
        int indent = 0;
        while (weekday != firstDayOfWeek) {
            indent++;
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);
        }

        // вывести названия дней недели
        String[] weekDayNames = new DateFormatSymbols().getShortWeekdays();
        do {
            System.out.printf("%4s", weekDayNames[weekday]);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);
        } while (weekday != firstDayOfWeek);
        System.out.println();
        for (int i = 1; i <= indent; i++) {
            System.out.print("    ");
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        do {
            // вывести день недели
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d", day);
            // пометить текущий день *
            if (day == today) System.out.print("*");
            else System.out.print(" ");
            // продвинуть объект calendar к следующему дню
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);
            // начать очередную неделю с новой строки
            if (weekday == firstDayOfWeek) System.out.println();
        }
        while (calendar.get(Calendar.MONTH) == month);
        // завершить цикл, когда в объекте calendar устанавливается первый день следующего месяца
        // перевести строку, если треубется

        if (weekday != firstDayOfWeek) System.out.println();
    }
}
