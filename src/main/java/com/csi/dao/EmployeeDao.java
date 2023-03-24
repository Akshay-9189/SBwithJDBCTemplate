package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void saveEmployee(Employee employee);

    void updateEmployee(int empId, Employee employee);

    List<Employee> getAllEmployee();

    void deleteEmployee(int empId);

    Employee getEmployeeById(int empId);

    Employee getByNameEmail(String empName, String empEmailId);

    Employee getEmployeeByContactNumber(String empContactNumber);

    Employee getEmployeeByEmailId(String empEmailId);

    void saveBulkOfData(List<Employee> employees);

    Employee getEmployeeByDOB(String empDOB);

    void deleteAllData();

    void patchEmployee(String empId, Employee employee);
}