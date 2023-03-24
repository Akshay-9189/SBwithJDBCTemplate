package com.csi.service;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(Employee employee);

    void updateEmployee(int empId, Employee employee);

    List<Employee> getAllEmployee();

    void deleteEmployee(int empId);

    boolean signIn(String empEmailId, String empPassword);

    Employee getEmployeeById(int empId);

    List<Employee> getEmployeeByName(String empName);

    Employee getEmployeeByContactNumber(String empContactNumber);

    Employee getEmployeeByEmailId(String empEmailId);

    List<Employee> sortEmployeeById();

    List<Employee> sortByEmployeeAge();

    List<Employee> sortEmployeeBySalary();

    Employee getByNameEmail(String empName, String empEmailId);

    List<Employee> sortEmployeeByName();

    List<Employee> filterBySalaryB(double empSalary);

    List<Employee> filterBySalaryA(double empSalary);

    boolean loadEligibility(int empId);

    void saveBulkOfData(List<Employee> employees);

    List<Employee> search(String input);

    Employee getEmployeeByDOB(String empDOB);

    void deleteAllData();

    void patchEmployee(String empId, Employee employee);
}