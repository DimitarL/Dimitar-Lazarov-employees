import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter a path to the file: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        Map<Integer, Employee> employeeListMap = new HashMap<>();

        try {
            File file = new File(path);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();

                if (currentLine.length() > 0) {
                    Record currentRecord = new Record(currentLine);

                    if (employeeListMap.containsKey(currentRecord.getEmployeeId())) {
                        employeeListMap.get(currentRecord.getEmployeeId()).addProjectFromRecord(currentRecord);
                    } else {
                        employeeListMap.put(currentRecord.getEmployeeId(), new Employee(currentRecord));
                    }
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.printf("An error occurred: %s%n", e);
            System.out.println("Terminating...");
            return;
        }

        findMaxPair(new ArrayList<>(employeeListMap.values()));
    }

    private static void findMaxPair(List<Employee> employeeList) {
        long maxWorkingDays = 0;
        EmployeePair<Integer, Integer> maxWorkingEmployees = new EmployeePair<>(0, 0);

        for (int i = 0; i < employeeList.size() - 1; i++) {
            for (int j = i + 1; j < employeeList.size(); j++) {
                Employee first = employeeList.get(i);
                Employee second = employeeList.get(j);

                long currentWorkingMaxDays = first.getMaxWorkingDays(second);
                if (currentWorkingMaxDays > maxWorkingDays) {
                    maxWorkingEmployees.setEmployee1(first.getId());
                    maxWorkingEmployees.setEmployee2(second.getId());
                    maxWorkingDays = currentWorkingMaxDays;
                }
            }
        }

        System.out.println("The couple of employees who have worked together on common projects for the longest " +
                "time is: " + maxWorkingEmployees.getEmployee1() + " & " + maxWorkingEmployees.getEmployee2() +
                ". They have worked together for " + maxWorkingDays + " days.");
    }
}