package main.java.professional_first;

import java.util.Date;
import java.util.GregorianCalendar;

public class CloneTest {
    public static void main(String[] args) {
        try {
            Employee_v2 original = new Employee_v2("John Q. Public", 50000);
            original.setHireDay(2000, 1, 1);
            Employee_v2 copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002, 12, 31);
            System.out.println("original = " + original);
            System.out.println("copy = " + copy);
        } catch (Exception e) { e.printStackTrace(); }
    }
}

class Employee_v2 implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee_v2(String aName, double aSalary) {
        name = aName;
        salary = aSalary;
        hireDay = new Date();
    }

    @Override
    public Employee_v2 clone() throws CloneNotSupportedException {
        Employee_v2 cloned = (Employee_v2) super.clone();
        //клонируем изменяемые поля
        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }

    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();

        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent) {
        salary += salary * byPercent / 100;
    }

    public String toString() {
        return "Employee [name = " + name + ", salary = " + salary + ", hireDay = " + hireDay + "]";
    }
}
