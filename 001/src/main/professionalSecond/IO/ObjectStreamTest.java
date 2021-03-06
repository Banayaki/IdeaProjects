package main.professionalSecond.IO;

import java.io.*;

public class ObjectStreamTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee harry = new Employee("Harry", 50000, 1989, 10, 1);
        Manager carl = new Manager("Carl", 80000, 1987, 12, 15);
        carl.setSercretary(harry);
        Manager tony = new Manager("Tony", 40000, 1990, 3, 15);
        tony.setSercretary(harry);

        Employee[] staff = new Employee[3];
        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("newEmployee.dat"))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("newEmployee.dat"))){
            Employee[] newStaff = (Employee[]) in.readObject();

            newStaff[1].raiseSalary(10);

            for (Employee e : newStaff){
                System.out.println(e);
            }
        }
    }
}
