package MokitoSpring.Makarov.service;

import MokitoSpring.Makarov.models.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private EmployeeService employeeService;

    @Test
    void withMaxSalarySuccess() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = Double.MAX_VALUE;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        double expectedMaxSalary = Math.max(salary1, salary2);

        //Начало теста
        double actualMaxSalary = departmentService.getMaxSalaryByDepId(departmentId1);
        assertEquals(expectedMaxSalary, actualMaxSalary);
        assertNotEquals(departmentId1, departmentId30);
        verify(employeeService).getAll();
        verifyNoMoreInteractions(employeeService);
    }
    @Test
    void withMinSalarySuccess() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = Double.MAX_VALUE;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        double expectedMaxSalary = Math.min(salary1, salary2);

        //Начало теста
        double actualMinSalary = departmentService.getMinSalaryByDepId(departmentId1);
        assertEquals(expectedMaxSalary, actualMinSalary);
        assertNotEquals(departmentId1, departmentId30);
        verify(employeeService).getAll();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void employeesByDepartmentSuccess() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);
        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = Double.MAX_VALUE;
        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //подгтовка
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        Map<Integer, List<Employee>> expectedEmployeeDep = new HashMap<>();
        expectedEmployeeDep.put(departmentId1, Arrays.asList(employee1, employee2));
        expectedEmployeeDep.put(departmentId30, Arrays.asList(employee3));
        //тест
        Map<Integer, List<Employee>> actualEmployee = departmentService.employeesByDepartment(null);
        assertEquals(expectedEmployeeDep, actualEmployee);
        verify(employeeService).getAll();
    }
    @Test
    void employeesByDepartmentIdSuccess() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = Double.MAX_VALUE;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        Map<Integer, List<Employee>> expectedEmployeesByDepId = new HashMap<>();
        expectedEmployeesByDepId.put(departmentId1, Arrays.asList(employee1, employee2));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeesByDepId = departmentService.employeesByDepartment(departmentId1);
        assertEquals(expectedEmployeesByDepId, actualEmployeesByDepId);
        verify(employeeService).getAll();
    }

    @Test
    void getSalarySumByDepId() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 300;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 300;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = 600;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        double expectedSumSalary = departmentService.getSalarySumByDepId(departmentId30);

        //Начало теста
        double actualSumSalary = departmentService.getSalarySumByDepId(departmentId1);
        assertEquals(expectedSumSalary, actualSumSalary);
        assertNotEquals(departmentId1, departmentId30);
    }

    @Test
    void getMaxSalaryByDepId() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = 999;
        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //подгтовка
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        double expectedMaxSalaryByDep = Math.max(salary1, salary2);
        //тест
        Double actualEmployee = departmentService.getMaxSalaryByDepId(departmentId30);
        assertEquals(expectedMaxSalaryByDep, actualEmployee);
        verify(employeeService).getAll();
    }

    @Test
    void getMinSalaryByDepId() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Саня";
        String lastName1 = "Пригожин";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Ваня";
        String lastName2 = "Павлов";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Женя";
        String lastName3 = "Белов";
        double salary3 = 999;
        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //подгтовка
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        double expectedMinSalaryByDep = Math.min(salary3, salary1);
        //тест
        Double actualEmployee = departmentService.getMinSalaryByDepId(departmentId1);
е        assertEquals(expectedMinSalaryByDep, actualEmployee);
        verify(employeeService).getAll();
    }

}