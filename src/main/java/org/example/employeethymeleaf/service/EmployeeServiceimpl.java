package org.example.employeethymeleaf.service;

import org.example.employeethymeleaf.model.Employee;
import org.example.employeethymeleaf.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeServiceimpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceimpl(EmployeeRepository employeeRepository, EmployeeRepository employeeRepository1) {
        this.employeeRepository = employeeRepository1;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public Employee creaetEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
}
