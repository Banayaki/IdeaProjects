package main.professionalSecond.IO;

import java.io.Serializable;

class Manager extends Employee implements Serializable {

    private double bonus;
    private Employee sercretary;

    public Manager(String aName, double aSalary, int aYear, int aMonth, int aDay) {
        super(aName, aSalary, aYear, aMonth, aDay);
        bonus = 0;
    }

    public void setSercretary(Employee emp){
        sercretary = emp;
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