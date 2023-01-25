package az.spring.jdbc.dao.impl;

import az.spring.jdbc.dao.EmployeeDao;
import az.spring.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Employee employee) {
        String query = "insert into employee (name,surname,age,salary) values (:name,:surname,:age,:salary)";

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(employee);

        jdbcTemplate.update(query,parameters);
    }

    @Override
    public void update(Employee employee) {
        String query = "update employee set name=:name,surname=:surname,age=:age,salary=:salary where id=:id";

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(employee);
        jdbcTemplate.update(query, parameters);
    }

    @Override
    public void delete(int id) {
        String query = "delete from employee where id=:id";

        SqlParameterSource parameters = new MapSqlParameterSource().addValue("id",id);
        jdbcTemplate.update(query, parameters);
    }

    @Override
    public Employee getEmployeeById(int id) {
        String queryName = "select * from employee where id =:id";

        SqlParameterSource parameters = new MapSqlParameterSource().addValue("id",id);
        Employee employee = jdbcTemplate.queryForObject(queryName,parameters, new BeanPropertyRowMapper<>(Employee.class));

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "Select * from employee";
        List<Employee> employee = jdbcTemplate.query(sql, new EmployeeRowMapper());

        return employee;
    }

    @Override
    public long count() {
        String query = "select count(*) from employee";
        return jdbcTemplate.queryForObject(query,new MapSqlParameterSource(), Long.class);
    }

    public static class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("age"),
                    rs.getDouble("salary"));
        }
    }
}
