package com.csi.dao;

import com.csi.constants.Queries;
import com.csi.exception.EmployeeNotFound;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Employee employee(ResultSet resultSet, int row) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2))
                .empAddress(resultSet.getString(3)).empContactNumber(resultSet.getLong(4))
                .empDOB(resultSet.getDate(5)).empSalary(resultSet.getDouble(6))
                .empEmailId(resultSet.getString(7)).empPassword(resultSet.getString(8)).build();
    }

    @Override
    public void saveEmployee(Employee employee) {
        jdbcTemplate.update(Queries.INSERT.getQuery(), employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(),
                employee.getEmpContactNumber(), employee.getEmpDOB(), employee.getEmpSalary(), employee.getEmpEmailId(),
                employee.getEmpPassword());
    }

    @Override
    public void updateEmployee(int empId, Employee employee) {
        jdbcTemplate.update(Queries.UPDATE.getQuery(), employee.getEmpName(), employee.getEmpAddress(),
                employee.getEmpContactNumber(), employee.getEmpDOB(), employee.getEmpSalary(), employee.getEmpEmailId(),
                employee.getEmpPassword(), empId);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return jdbcTemplate.query(Queries.GET_ALL.getQuery(), this::employee);
    }

    @Override
    public void deleteEmployee(int empId) {
        jdbcTemplate.update(Queries.DELETE_BY_ID.getQuery(), empId);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        try {
            return jdbcTemplate.query(Queries.GET_BY_ID.getQuery(), this::employee, empId).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new EmployeeNotFound("Employee Not Found");
        }
    }

    @Override
    public Employee getByNameEmail(String empName, String empEmailId) {
        return jdbcTemplate.query(Queries.GET_BY_NAME_MAIL.getQuery(), this::employee, empName, empEmailId).get(0);
    }

    @Override
    public Employee getEmployeeByContactNumber(String empContactNumber) {
        return jdbcTemplate.query(Queries.GET_BY_CONTACT.getQuery(), this::employee, empContactNumber).get(0);
    }

    @Override
    public Employee getEmployeeByEmailId(String empEmailId) {
        return jdbcTemplate.query(Queries.GET_BY_EMAIL.getQuery(), this::employee, empEmailId).get(0);
    }

    @Override
    public void saveBulkOfData(List<Employee> employees) {
        employees.forEach(emp ->
                jdbcTemplate.update(Queries.INSERT.getQuery(), emp.getEmpId(), emp.getEmpName(), emp.getEmpAddress(),
                        emp.getEmpContactNumber(), emp.getEmpDOB(), emp.getEmpSalary(), emp.getEmpEmailId(),
                        emp.getEmpPassword())
        );
    }

    @Override
    public Employee getEmployeeByDOB(String empDOB) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(empDOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jdbcTemplate.query(Queries.GET_BY_DOB.getQuery(), this::employee, date).get(0);
    }

    @Override
    public void deleteAllData() {
        jdbcTemplate.update(Queries.DELETE_ALL.getQuery());
    }

    @Override
    public void patchEmployee(String empId, Employee employee) {
        jdbcTemplate.update(Queries.PATCH_ADDRESS_CONTACT.getQuery(), employee.getEmpAddress(), employee.getEmpContactNumber(), empId);
    }
}