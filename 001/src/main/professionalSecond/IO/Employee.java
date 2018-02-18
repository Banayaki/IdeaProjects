package main.professionalSecond.IO;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

    private static int nextID = 1;
    private String name;
    private Date calendar;
    private double salary;
    private int id;

    public Employee(String name, double salary, int y, int m, int d) {
        this.name = name;
        this.salary = salary;
        calendar = new Date(y, m - 1, d);
        id = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void raiseSalary(int percent){
        salary = getSalary() * (100 + percent);
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return calendar;
    }

    public void setId() {
        id = nextID;
        nextID++;
    }

    public static int getNextID() {
        return nextID;
    }

}
