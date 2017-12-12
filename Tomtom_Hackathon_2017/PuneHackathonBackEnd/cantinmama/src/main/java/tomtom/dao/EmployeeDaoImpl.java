package tomtom.dao;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tomtom.bean.Employee;


@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = null;

        try {
            employees = jdbcTemplate.query("SELECT * FROM trn_employee",
                    new BeanPropertyRowMapper<Employee>(Employee.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployee(Long employeeId) {
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?",
                    new Object[]{employeeId}, new BeanPropertyRowMapper<Employee>(Employee.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return employee;

    }

    public int deleteEmployee(Long employeeId) {
        int count = jdbcTemplate.update("DELETE from trn_employee WHERE employee_id = ?", new Object[]{employeeId});
        return count;
    }

    public int updateEmployee(Employee employee) {
        int count = jdbcTemplate.update(
                "UPDATE users set due_ammount = ?  where id = ?", new Object[]{
                        employee.getDueAmmount(),employee.getId()});
        return count;
    }

    public int createEmployee(Employee employee) {
        int count = jdbcTemplate.update(
                "INSERT INTO users(first_name, last_name, registration_date,sodexo_card_number,email,card_expiry_date,due_ammount,user_id)VALUES(?,?,?,?,?,?,?,?)", new Object[]{
                        employee.getFirstName(), employee.getLastName(), new Date(System.currentTimeMillis()), employee.getSodexoCardNumber()
                        , employee.getEmail(),employee.getCardExpiryDate(),employee.getDueAmmount(),employee.getUserID()});
        return count;
    }

}