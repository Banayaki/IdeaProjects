package main.professionalSecond.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextFileTest {

    public static void main(String[] args) throws IOException{
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry", 81100, 1989, 10, 1);
        staff[2] = new Employee("Artem", 99999, 1999, 3, 15);

        try (PrintWriter out = new PrintWriter("employee.txt", "UTF-8")) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner(new FileInputStream("employee.txt"), "UTF-8")){
            Employee[] newStaff = readData(in);
            for (Employee e : newStaff) {
                System.out.println(e.getName() + e.getSalary() + e.getHireDay());
            }
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) throws IOException{

        out.println(employees.length);

        for (Employee e : employees){
            writeEmployee(out, e);
        }
    }

    public static void writeEmployee(PrintWriter out, Employee e){

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(e.getHireDay());
        out.println(e.getName() + "|" + e.getSalary() + "|" + calendar.get(Calendar.YEAR) +
        "|" + calendar.get(Calendar.MONTH) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    private static Employee[] readData(Scanner in){

        int n = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++){
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static Employee readEmployee(Scanner in){

        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        int mount = Integer.parseInt(tokens[3]);
        int day = Integer.parseInt(tokens[4]);
        return new Employee(name, salary, year, mount, day);
    }
}
