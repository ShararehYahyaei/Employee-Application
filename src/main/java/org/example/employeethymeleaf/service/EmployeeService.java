package org.example.employeethymeleaf.service;

import org.example.employeethymeleaf.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee creaetEmployee(Employee employee);
    void deleteEmployee(Long id);
}
