package com.csi.service;

import com.csi.dao.EmployeeDao;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    public void updateEmployee(int empId, Employee employee) {
        Employee employee1 = employeeDao.getEmployeeById(empId);
        if (employee1 != null) {
            employeeDao.updateEmployee(empId, employee);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public void deleteEmployee(int empId) {
        employeeDao.deleteEmployee(empId);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        AtomicBoolean flag = new AtomicBoolean(false);
        employeeDao.getAllEmployee().forEach(emp -> {
            if (emp.getEmpEmailId().equals(empEmailId) && emp.getEmpPassword().equals(empPassword)) {
                flag.set(true);
            }
        });
        return flag.get();
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return employeeDao.getEmployeeById(empId);
    }

    @Override
    public List<Employee> getEmployeeByName(String empName) {
        return employeeDao.getAllEmployee().stream().filter(emp -> emp.getEmpName().equals(empName)).toList();
    }

    @Override
    public Employee getEmployeeByContactNumber(String empContactNumber) {
        return employeeDao.getEmployeeByContactNumber(empContactNumber);
    }

    @Override
    public Employee getEmployeeByEmailId(String empEmailId) {
        return employeeDao.getEmployeeByEmailId(empEmailId);
    }

    @Override
    public List<Employee> sortEmployeeById() {
        return employeeDao.getAllEmployee().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).toList();
    }

    @Override
    public List<Employee> sortByEmployeeAge() {
        return employeeDao.getAllEmployee().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).toList();
    }

    @Override
    public List<Employee> sortEmployeeBySalary() {
        return employeeDao.getAllEmployee().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).toList();
    }

    @Override
    public Employee getByNameEmail(String empName, String empEmailId) {
        return employeeDao.getByNameEmail(empName, empEmailId);
    }

    @Override
    public List<Employee> sortEmployeeByName() {
        return employeeDao.getAllEmployee().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList();
    }

    @Override
    public List<Employee> filterBySalaryB(double empSalary) {
        return employeeDao.getAllEmployee().stream().filter(emp -> emp.getEmpSalary() <= empSalary).toList();
    }

    @Override
    public List<Employee> filterBySalaryA(double empSalary) {
        return employeeDao.getAllEmployee().stream().filter(emp -> emp.getEmpSalary() >= empSalary).toList();
    }

    @Override
    public boolean loadEligibility(int empId) {
        boolean eligibility = false;
        Employee employee = employeeDao.getEmployeeById(empId);
        if (employee.getEmpSalary() >= 50000.00) {
            eligibility = true;
        }
        return eligibility;
    }

    @Override
    public void saveBulkOfData(List<Employee> employees) {
        employeeDao.saveBulkOfData(employees);
    }

    @Override
    public List<Employee> search(String input) {
        List<Employee> employees = new ArrayList<>();
        if (input.matches("^[0-9]+$")) {
            employeeDao.getAllEmployee().forEach(employee -> {
                if (employee.getEmpContactNumber() == Long.parseLong(input)) {
                    employees.add(employee);
                }
            });
        } else if (input.matches("^[(0-9)]+[(.){1}]+[(0-9){2}]+$")) {
            employeeDao.getAllEmployee().forEach(employee -> {
                if (employee.getEmpSalary() == Double.parseDouble(input)) {
                    employees.add(employee);
                }
            });
        } else if (input.matches("(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])-(0[1-9]|1[0-2]|[1-9])-([0-9]{4})")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            if (input.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
                employeeDao.getAllEmployee().forEach(employee -> {
                    if (simpleDateFormat.format(employee.getEmpDOB()).equals(input)) {
                        employees.add(employee);
                    }
                });
            } else if (input.matches("([0-9]{1})-([0-9]{1})-([0-9]{4})")) {
                String start = input.substring(0, 2);
                String last = input.substring(2, 8);
                String newInput = "0" + start + "0" + last;
                employeeDao.getAllEmployee().forEach(employee -> {
                    if (simpleDateFormat.format(employee.getEmpDOB()).equals(newInput)) {
                        employees.add(employee);
                    }
                });
            } else if (input.matches("([0-9]{2})-([0-9]{1})-([0-9]{4})")) {
                String start = input.substring(0, 3);
                String last = input.substring(3, 9);
                String newInput = start + "0" + last;
                employeeDao.getAllEmployee().forEach(employee -> {
                    if (simpleDateFormat.format(employee.getEmpDOB()).equals(newInput)) {
                        employees.add(employee);
                    }
                });
            } else {
                employeeDao.getAllEmployee().forEach(employee -> {
                    if (simpleDateFormat.format(employee.getEmpDOB()).equals("0" + input)) {
                        employees.add(employee);
                    }
                });
            }
        } else {
            employeeDao.getAllEmployee().forEach(emp -> {
                if (emp.getEmpName().equals(input) || emp.getEmpAddress().equals(input) || emp.getEmpEmailId().equals(input)) {
                    employees.add(emp);
                }
            });
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByDOB(String empDOB) {
        return employeeDao.getEmployeeByDOB(empDOB);
    }

    @Override
    public void deleteAllData() {
        employeeDao.deleteAllData();
    }

    @Override
    public void patchEmployee(String empId, Employee employee) {
        employeeDao.patchEmployee(empId, employee);
    }
}