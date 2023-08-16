package MokitoSpring.Makarov.service;
import org.apache.commons.lang3.StringUtils;

import MokitoSpring.Makarov.exception.EmployeeAlreadyAddedException;
import MokitoSpring.Makarov.exception.EmployeeNotFoundException;
import MokitoSpring.Makarov.exception.EmployeeStorageIsFullException;
import MokitoSpring.Makarov.exception.WrongNameException;
import MokitoSpring.Makarov.models.Employee;


import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final int MAX_SIZE = 2;

    public Employee add(String firstName, String lastName) {

        checkUpperCase(firstName, lastName);
        checkAlpha(firstName, lastName);

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + newEmployee + " уже существует");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    private void checkUpperCase(String firstName, String lastName) {
        String capitalizeFirstName = StringUtils.capitalize(firstName);
        String capitalizeLastName = StringUtils.capitalize(lastName);

        if (!(firstName.equals(capitalizeFirstName))) {
            throw new WrongNameException("Имя начинается не с заглавной буквы");
        }

        if (!(lastName.equals(capitalizeLastName))) {
            throw new WrongNameException("Фамилия начинается не с заглавной буквы");
        }
    }

    private void checkAlpha(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new WrongNameException("Имя содержит запрещенные символы");
        }

        if (!StringUtils.isAlpha(lastName)) {
            throw new WrongNameException("Фамилия содержит запрещенные символы");
        }
    }

    public Employee find(String firstName, String lastName) {
        Employee employeeForFind = new Employee(firstName, lastName);

        if (!employees.contains(employeeForFind)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }

        return employees.get(employees.indexOf(employeeForFind));
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);

        if (!employees.contains(employeeForRemove)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }

        employees.remove(employeeForRemove);
        return employeeForRemove;
    }

    public List<Employee> getAll() {
        return employees;
    }


}
