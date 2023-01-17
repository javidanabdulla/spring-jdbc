package az.spring.jdbc.dao;

import az.spring.jdbc.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void insert (Employee employee);

    void update (Employee employee);

    void delete (Employee employee);

    Employee getEmployeeId (int id);

    List<Employee> getAllEmployees();

    long count();
}
