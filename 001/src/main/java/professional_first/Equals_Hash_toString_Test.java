package main.java.professional_first;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Equals_Hash_toString_Test {

    public static void main(String[] args) {
        Employee alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        Employee alice2 = alice1;
        Employee alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        Employee bob = new Employee("Bob", 50000, 1989, 10, 1);

        System.out.println("alice1 == alice2: " + (alice1 == alice2));

        System.out.println("alice1 == alice3: " + (alice1 == alice3));

        System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));

        System.out.println("alice1.equals(bob): " + alice1.equals(bob));

        System.out.println("bob.toString(): " + bob);

        Manager carl = new Manager("Carl", 80000, 1987, 12, 15);
        Manager boss = new Manager("Carl", 80000, 1987, 12, 15);
        boss.setBonus(5000);
        System.out.println("boss.toString(): " + boss);
        System.out.println("carl.equals(boss): " + carl.equals(boss));
        System.out.println("alice1.hashCode(): " + alice1.hashCode());
        System.out.println("alice3.hashCode(): " + alice3.hashCode());
        System.out.println("bob.hashCode(): " + bob.hashCode());
        System.out.println("carl.hashCode(): " + carl.hashCode());
    }
}

class Employee {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String aName, double aSalary, int aYear, int aMonth, int aDay) {
        name = aName;
        salary = aSalary;
        GregorianCalendar calendar = new GregorianCalendar(aYear, aMonth - 1, aDay);
        hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public boolean equals(Object otherObject) {
        //Быстро проверить объекты на идентичность
        if (this == otherObject) return true;
        // если явный параметр имеет пустое значение null, то возвращаем false
        if (otherObject == null) return false;
        // если классы не совпадают, они не равнозначны
        if (this.getClass() != otherObject.getClass()) return false;
        // теперь известно, что otherObject != null и он класса Employee
        Employee other = (Employee) otherObject;
        // проверить содержат ли поля одинаковые значеения
        return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name = " + name + ", salary = " + salary + ", hireDay = " + hireDay + "]";
    }
}

class Manager extends Employee {

    private double bonus;

    public Manager(String aName, double aSalary, int aYear, int aMonth, int aDay) {
        super(aName, aSalary, aYear, aMonth, aDay);
        bonus = 0;
    }

    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) return false;
        Manager other = (Manager) otherObject;
        return bonus == other.bonus;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 17 * new Double(bonus).hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + " [bonus = " + bonus + "]";
    }
}