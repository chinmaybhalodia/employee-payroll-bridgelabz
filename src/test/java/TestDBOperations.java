import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import com.example.*;

public class TestDBOperations {

    // testing the add method
    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Dummy1", "2023-12-12", "male", "phone", "address", 50000, "Test");
        int employees_before = DBOperations.readEmployees().size();
        DBOperations.addEmployee(employee);
        ArrayList<Employee> employees = DBOperations.readEmployees();
        int employees_after = employees.size();

        // checking if employee has been added or not
        assertEquals(employees_before + 1, employees_after);

        // checking if this particular entry has been added or not
        assertTrue(employees.stream().anyMatch(emp -> emp.getName().equals("Dummy1")));
    }

    // testing method to for removing employee
    @Test
    public void testRemoveEmployee() {
        Employee employee = new Employee("Dummy2", "2023-12-12", "male", "phone", "address", 50000, "Test");
        DBOperations.addEmployee(employee);
        int employees_before = DBOperations.readEmployees().size();
        DBOperations.removeEmployee("Dummy2");
        int employees_after = DBOperations.readEmployees().size();

        // checking if employee has been removed or not
        assertEquals(employees_before, employees_after + 1);
    }

    // testing method for update salary
    @Test
    public void testUpdateSalary() {
        Employee employee = new Employee("Dummy3", "2023-12-12", "male", "phone", "address", 50000, "Test");
        DBOperations.addEmployee(employee);
        DBOperations.updateSalary(30000, "Dummy3");
        String query = "select salary from employee_payroll where name = \"Dummy3\";";
        double new_salary = -1;
        try (
                Connection connection = DriverManager.getConnection(Details.URL, Details.USER, Details.PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                new_salary = resultSet.getDouble("salary");
            }
            assertEquals(30000, new_salary);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            fail();
        }
    }
}
