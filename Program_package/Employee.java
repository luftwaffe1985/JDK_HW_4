package Program_package;
// Создать справочник сотрудников

// Необходимо:
// Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников - 
// каждый сотрудник должен иметь следующие атрибуты:
// Табельный номер
// Номер телефона
// Имя
// Стаж
// Добавить метод, который ищет сотрудника по стажу (может быть список)
// Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
// Добавить метод, который ищет сотрудника по табельному номеру
// Добавить метод добавления нового сотрудника в справочник

import java.util.*;

public class Employee {
    Integer keyId;
    private String tableNumber;
    private String phoneNumber;
    private String name;
    private Integer experience;
    private Scanner scanner;

    public HashMap<Integer, Employee> mapEmployees = new HashMap<>();

    private Random random = new Random();

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return String.format("%9s %13s %20s %5d %s", getTableNumber(),
                getPhoneNumber(), getName(), getExperience(), "\n");

    }

    public List<Employee> searchByExperienceEmployee(Integer minValueExp, Integer maxValueExp,
            HashMap<Integer, Employee> map) {
        List<Employee> employList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (map.get(key).getExperience() >= minValueExp &&
                    map.get(key).experience <= maxValueExp) {
                employList.add(map.get(key));
            }
        }
        System.out.println("Employees List with Exp from"
                + minValueExp + " to " + maxValueExp);
        return employList;
    }

    public List<Employee> searchByExperienceEmployee(Integer valueExp,
            HashMap<Integer, Employee> map) {
        List<Employee> employList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (map.get(key).getExperience() == valueExp) {
                employList.add(map.get(key));
            }
        }
        System.out.println("Employees List with Exp "
                + valueExp);
        return employList;
    }

    public List<String> searchPhoneByNameEmployee(String name,
            HashMap<Integer, Employee> map) {
        List<String> phoneNumber = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (map.get(key).getName().equals(name)) {
                phoneNumber.add(map.get(key).getPhoneNumber());
            }
        }
        return phoneNumber;
    }

    public Employee searchEmployeeByTableNum(String tabNum,
            HashMap<Integer, Employee> map) {

        Employee employee = new Employee();
        for (Integer key : map.keySet()) {
            if (map.get(key).getTableNumber().equals(tabNum)) {
                employee = map.get(key);
            }
        }
        return employee;
    }

    public void fullRandomMapDictionary(Integer numberOfEmployees) {

        String[] firstName = { "Youri", "Roxane", "Serhio", "Nika", "Hary", "Dabby", "Cos",
                "Lisa" };
        String[] lastName = { "Balashov", "Lex", "Ramos", "Pascal", "Brown", "Fegure", "Cosmos", "Simpson" };
        for (int i = 0; i < numberOfEmployees; i++) {
            Employee employee = new Employee();
            employee.setTableNumber(String.format("%s%d", "e-", random.nextInt(100001, 500000)));
            employee.setPhoneNumber(String.format("%s%d", "+79", random.nextInt(010101010, 319999999)));

            Integer tempIndex1 = random.nextInt(0, 8);
            Integer tempIndex2 = random.nextInt(0, 8);
            employee.setName(firstName[tempIndex1] + " "
                    + ((tempIndex1 % 2 != 0) ? lastName[tempIndex2] + "а" : lastName[tempIndex2]));
            employee.setExperience(random.nextInt(1, 40));

            this.mapEmployees.put(i, employee);
        }
    }

    public void addEmployee(HashMap<Integer, Employee> mapEmployees) {
        Employee employee = new Employee();
        Integer nextId = -1;
        Boolean successName = true;
        for (Integer key : mapEmployees.keySet()) {
            if (key > nextId)
                nextId = key;
        }
        employee.setTableNumber(String.format("%s%d", "e-", random.nextInt(100001, 500000)));
        System.out.println("The employee has been set the following table No.: " + employee.getTableNumber());
        try {
            employee.setName(enterNameFromConsole("Enter the employee's full name: "));
        } catch (IllegalArgumentNameException e) {
            successName = false;
        }
        if (successName) {
            String phoneNumber = enterPhoneFromConsole("Enter the employee's 10-digit phone number: ");
            if (phoneNumber.equals("")) {
                employee.setPhoneNumber("");
            } else {
                employee.setPhoneNumber(phoneNumber);
            }
            employee.setExperience(enterExperienceFromConsole("Enter the employee's experience (years)"));
            mapEmployees.put(++nextId, employee);
        }
    }

    public String enterNameFromConsole(String str) throws IllegalArgumentNameException { // Data entrance invitation
        scanner = new Scanner(System.in);
        String alphabet = Alphabet.ENGLISH_ALPHABET + Alphabet.RUSSIAN_ALPHABET + " ";
        String name = "";
        System.out.println(str);
        name = scanner.nextLine();
        String[] string = name.split("");
        for (int j = 0; j < string.length; j++) {
            if (!alphabet.contains(string[j])) {
                throw new IllegalArgumentNameException(
                        "Incorrect data entry. The User " + name + " has not been introduced.\n");
            }
        }
        return name;
    }

    public String enterPhoneFromConsole(String str) { // Data entrance invitation
        scanner = new Scanner(System.in);
        String numbers = "0123456789";
        System.out.println(str);
        System.out.print("+7 ");
        int incorrectChar = 0;
        String phoneNum = scanner.next();
        String[] phone = phoneNum.split("");
        for (int i = 0; i < phone.length; i++) {
            if (!numbers.contains(phone[i])) {
                incorrectChar++;
            }
        }
        if (incorrectChar == 0)
            phoneNum = "+7" + phoneNum;
        else {
            System.out.println("Incorrect entry. Empty field is tried to be entered");
            phoneNum = "";
        }
        return phoneNum;
    }

    public Integer enterExperienceFromConsole(String str) { // Data entrance invitation
        String experience = "";
        String strExperience = "";
        for (int i = 0; i <= 80; i++) {
            strExperience += i;
        }
        System.out.println(str);
        experience = scanner.next();
        if (strExperience.contains(experience)) {
            return Integer.parseInt(experience);
        } else {
            System.out.println("Incorrect entry. The value set is - 1");
            experience = "1";
        }
        return Integer.parseInt(experience);
    }

    public void printEmployList(HashMap<Integer, Employee> employList) {
        // List <Integer> keys = new ArrayList<>();
        System.out.printf("%3s %9s %13s %18s %5s %s", "id", "Table No.", "Phone No.", "Full Name", "Experience", "\n");
        System.out.println("----------------------------------------------------------");
        for (Integer key : employList.keySet()) {
            System.out.printf("%3d", key);
            System.out.print(employList.get(key));
        }

    }
}