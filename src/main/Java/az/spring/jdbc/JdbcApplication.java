package az.spring.jdbc;

import az.spring.jdbc.config.SpringJdbcConfig;
import az.spring.jdbc.dao.EmployeeDao;
import az.spring.jdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);

        //Insert
        Employee employee = new Employee();
        employee.setName("Vali");
        employee.setSurname("Valiyev");
        employee.setAge(30);
        employee.setSalary(5000);

        //employeeDao.insert(employee);

        //Count
        System.out.println("count: " + employeeDao.count());

        //Select by id
        System.out.println(employeeDao.getEmployeeById(1));

        //Select all
        System.out.println(employeeDao.getAllEmployees());

        //Delete
        employeeDao.delete(10);

        //Update
        Employee employee1 = new Employee();
        employee1.setName("Qulu");
        employee1.setSurname("Quluyev");
        employee1.setAge(52);
        employee1.setSalary(1000);
        employee1.setId(5);

        employeeDao.update(employee1);
    }
}
