package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveData(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Employee Saved Successfully");
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee) {
        employeeService.updateEmployee(empId, employee);
        return ResponseEntity.ok("Employee having id " + empId + " updated successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee having id " + empId + " deleted successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<String> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        if (employeeService.signIn(empEmailId, empPassword)) {
            return ResponseEntity.ok("Sign in successfully");
        } else {
            return new ResponseEntity<>("Sign in failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(empId));
    }

    @GetMapping("/get-by-name/{empName}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeService.getEmployeeByName(empName));
    }

    @GetMapping("/get-by-name-email/{empName}/{empEmailId}")
    public ResponseEntity<Employee> getByNameEmail(@PathVariable String empName, @PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeService.getByNameEmail(empName, empEmailId));
    }

    @GetMapping("/get-by-contact-number/{empContactNumber}")
    public ResponseEntity<Employee> getEmployeeByContactNumber(@PathVariable String empContactNumber) {
        return ResponseEntity.ok(employeeService.getEmployeeByContactNumber(empContactNumber));
    }

    @GetMapping("/get-by-email-id/{empEmailId}")
    public ResponseEntity<Employee> getEmployeeByEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeService.getEmployeeByEmailId(empEmailId));
    }

    @GetMapping("/sort/id")
    public ResponseEntity<List<Employee>> sortByEmployeeId() {
        return ResponseEntity.ok(employeeService.sortEmployeeById());
    }

    @GetMapping("/sort/age")
    public ResponseEntity<List<Employee>> sortByEmployeeAge() {
        return ResponseEntity.ok(employeeService.sortByEmployeeAge());
    }

    @GetMapping("/sort/salary")
    public ResponseEntity<List<Employee>> sortEmployeeBySalary() {
        return ResponseEntity.ok(employeeService.sortEmployeeBySalary());
    }

    @GetMapping("/sort/name")
    public ResponseEntity<List<Employee>> sortEmployeeByName() {
        return ResponseEntity.ok(employeeService.sortEmployeeByName());
    }

    @GetMapping("/filter/salary-below/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalaryB(double empSalary) {
        return ResponseEntity.ok(employeeService.filterBySalaryB(empSalary));
    }

    @GetMapping("/filter/salary-above/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalaryA(double empSalary) {
        return ResponseEntity.ok(employeeService.filterBySalaryA(empSalary));
    }

    @GetMapping("/loan-eligibility/{empId}")
    public ResponseEntity<String> checkLoanEligibility(@PathVariable int empId) {
        if (employeeService.loadEligibility(empId)) {
            return ResponseEntity.ok("Eligible for loan");
        } else {
            return ResponseEntity.ok("Not eligible for loan");
        }
    }

    @PostMapping("/save-bulk-data")
    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employees) {
        employeeService.saveBulkOfData(employees);
        return ResponseEntity.ok("All employees saved successfully");
    }

    @Operation(summary = "Excluding search by id")
    @GetMapping("/search/{input}")
    public ResponseEntity<List<Employee>> search(@PathVariable String input) {
        return ResponseEntity.ok(employeeService.search(input));
    }

    @GetMapping("/get-by-dob/{empDOB}")
    public ResponseEntity<Employee> getEmployeeByDOB(@PathVariable String empDOB) {
        return ResponseEntity.ok(employeeService.getEmployeeByDOB(empDOB));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllData() {
        employeeService.deleteAllData();
        return ResponseEntity.ok("All employees deleted successfully");
    }

    @PatchMapping("/patch-employee/{empId}")
    public ResponseEntity<String> patchEmployee(@PathVariable String empId, @RequestBody Employee employee) {
        employeeService.patchEmployee(empId, employee);
        return ResponseEntity.ok("Employee having id " + empId + " address & contact number updated successfully");
    }
}