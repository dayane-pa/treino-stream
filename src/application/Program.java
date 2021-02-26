package application;


import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.interfaces.DSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        String path = "/home/dayane/Documents/exercicio.employee.stream";

        System.out.println("Enter full file path: " + path);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            List<Employee> employeeList = new ArrayList<>();

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                employeeList.add(new Employee(split[0], split[1], Double.parseDouble(split[2])));
                line = bufferedReader.readLine();
            }
            System.out.print("Enter salary: ");
            double userValue = scanner.nextDouble();
            System.out.println("Email of people whose salary is more than " + String.format("%.2f" ,userValue) + ":");

            Comparator<String> comparator = (name1, name2) -> name1.toUpperCase().compareTo(name2.toUpperCase());

            List<String> emailList = employeeList.stream()
                    .filter(p -> p.getSalary() > userValue)
                    .map(p -> p.getEmail())
                    .sorted(comparator)
                    .collect(Collectors.toList());

            emailList.forEach(System.out::println);

            Double salarySum = employeeList.stream()
                    .filter(p -> p.getName().charAt(0) == 'M')
                    .map(p -> p.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.printf("Sum of salary of people whose name starts with 'M': " + String.format("%.2f" , salarySum));

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


        scanner.close();
    }
}



