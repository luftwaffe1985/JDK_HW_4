import java.util.Random;

import Program_package.Employee;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Employee employee = new Employee();
        System.out.println("Fill the Table wit random employees data:");
        employee.fullRandomMapDictionary(15); // Data entry
        employee.printEmployList(employee.mapEmployees);

        System.out.println();
        System.out.println("Search by Table No.:"); // Choosing a random Table No.
        String tabNum = employee.mapEmployees.get(random.nextInt(0, 15)).getTableNumber();
        System.out.println("The employee with this No. is " + tabNum);
        System.out.println(employee.searchEmployeeByTableNum(tabNum, employee.mapEmployees));

        System.out.println();
        System.out.println("Search by Experience:");
        System.out.println("Range:");
        System.out.println(employee.searchByExperienceEmployee(2, random.nextInt(2, 10), employee.mapEmployees));
        System.out.println("Value:");
        System.out.println(employee.searchByExperienceEmployee(random.nextInt(1, 35), employee.mapEmployees));

        System.out.println(); // Choosing a random Name
        String name = employee.mapEmployees.get(random.nextInt(0, 15)).getName();
        System.out.println("Search by Name: " + name);
        System.out.println(
                "The employee's contacts " + name + ": "
                        + employee.searchPhoneByNameEmployee(name, employee.mapEmployees));

        System.out.println();
        System.out.println("Manual employee adding");
        employee.addEmployee(employee.mapEmployees);
        employee.printEmployList(employee.mapEmployees);
    }
}