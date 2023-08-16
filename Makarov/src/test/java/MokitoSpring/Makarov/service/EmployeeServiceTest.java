package MokitoSpring.Makarov.service;

import MokitoSpring.Makarov.exception.EmployeeAlreadyAddedException;
import MokitoSpring.Makarov.exception.EmployeeNotFoundException;
import MokitoSpring.Makarov.exception.WrongNameException;
import MokitoSpring.Makarov.models.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private static final String FIRST_NAME = "Саша";
    private static final String SECOND_NAME = "Пригожин";
    private final EmployeeService employeeService = new EmployeeService();


    @Test
    void addFirstNameWithException() {
        // подготовка данных
        String WrongFirstName = "саша";
        String SecondName = "Маша";
        //подготовка результата
        String massage = "Имя начинается не с заглавной буквы";
        //тест
        Exception exception = assertThrows(WrongNameException.class, () -> {
            employeeService.add(WrongFirstName, SecondName);
        });
        assertEquals(massage, exception.getMessage());
    }

    @Test
    void addSuccess() {
        // подготовка данных
        String firstName = FIRST_NAME;
        String secondName = SECOND_NAME;
        //подготовка результата
        Employee expectedEmployy = new Employee(firstName, secondName);
        //ткст
        Employee actualEmployy = employeeService.add(firstName, secondName);
        assertEquals(expectedEmployy, actualEmployy);
    }

    @Test
    void AllreadyAddExceptoin() {
        // подготовка данных
        String firstName = FIRST_NAME;
        String secondName = SECOND_NAME;

        //подготовка результата
        Employee expectedEmployee = new Employee(firstName, secondName);
        String expectedMessage = "Сотрудник " + expectedEmployee + " уже существует";
        //тест
        Employee addedEmployee = employeeService.add(firstName, secondName);
        assertEquals(expectedEmployee, addedEmployee);

        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(firstName, secondName);
        });
        assertEquals(expectedMessage, exception.getMessage());


    }

    @Test
    void findSuccess() {
        // подготовка данных
        GetEmployees();
        String fristName = FIRST_NAME;
        String secondName = SECOND_NAME;
        //подготовка результата
        Employee expectedEmployy = new Employee(fristName, secondName);
        //тест
        Employee actualEmployy = employeeService.find(fristName, secondName);
        assertEquals(expectedEmployy,actualEmployy);


        removeEmployees();
    }
    @Test
    void findEmployyWithException() {
        // подготовка данных
        GetEmployees();
        String fristName = "Са";
        String secondName = "Приг";
        //подготовка результата
        Employee expectedEmployee = new Employee(fristName, secondName);
        String massage = "Такого сотрудника нет";
        //тест
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.find(fristName, secondName);
        });
        assertEquals(massage, exception.getMessage());


        removeEmployees();
    }

    @Test
    void removeWitException() {
        // подготовка данных
        GetEmployees();
        String fristName = "Са";
        String secondName = "Приг";
        //подготовка результата
        Employee expectedEmployee = new Employee(fristName, secondName);
        String massage = "Такого сотрудника нет";
        //тест
        Exception employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.remove(fristName, secondName);
        });
        assertEquals(massage, employeeNotFoundException.getMessage());

    }
    @Test
    void removeSuccess() {
        // подготовка данных
        GetEmployees();
        String fristName = "Санек";
        String secondName = "Погожин";
        //подготовка результата
        Employee expectedEmployy = new Employee(fristName, secondName);
        //тест
        Employee actualEmployy = employeeService.remove(fristName, secondName);
        assertEquals(expectedEmployy,actualEmployy);
    }

    @Test
    void getAll() {
        // подготовка данных
        GetEmployees();
        String firstName1 = FIRST_NAME;
        String secondName1 = SECOND_NAME;
        //подготовка результата
        Employee expectedEmployy = new Employee(FIRST_NAME, SECOND_NAME);
        //тест
        List<Employee> actualEmployy = employeeService.getAll();
        remove1Employees();
        assertEquals(expectedEmployy,actualEmployy);
        removeEmployees();

    }
    private void GetEmployees() {
        employeeService.add(FIRST_NAME, SECOND_NAME);
        employeeService.add("Санек", "Погожин");
    }

    private void removeEmployees() {
        employeeService.remove(FIRST_NAME, SECOND_NAME);
        employeeService.remove("Санек", "Погожин");
    }
    private void remove1Employees() {
        employeeService.remove("Санек", "Погожин");
    }
}