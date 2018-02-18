package main.java.professional_first;

import java.util.ArrayList;

public class EmployeeTest {

    public static void main(String[] args) {
        // заполнить массив staff тремя объектами типа Employee
        ArrayList<Employee1> staff = new ArrayList<>();
        staff.add(new Employee1("Tom", 40000));
        staff.add(new Employee1("Dick", 4646466));
        staff.add(new Employee1("Harison", 9999999));

        //Вывести данные обо всех объктах типа Employee
        for (Employee1 e : staff){
            e.setId();
            System.out.println("Name: " + e.getName() + ", id = " + e.getId() + ", salary = " + e.getSalary());
        }
        // получить следующий доступный id
        System.out.println("Next available id is: " + Employee1.getNextID());
    }
}

class Employee1 {
    private static int nextID = 1;
    private String name;
    private double salary;
    private int id;

    public Employee1(String name, double salary) {
        this.name = name;
        this.salary = salary;
        id = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setId() {
        id = nextID;
        nextID++;
    }

    public static int getNextID(){
        return nextID;
    }

    public static void main(String[] args) {
        Employee1 e = new Employee1("Harry", 50000);
        System.out.println(e.getName() + " " + e.getSalary() + " " + e.getId());
    }


}
