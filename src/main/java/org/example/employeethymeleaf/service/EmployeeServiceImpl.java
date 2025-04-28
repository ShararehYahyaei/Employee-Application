package org.example.employeethymeleaf.service;

import org.example.employeethymeleaf.model.Employee;
import org.example.employeethymeleaf.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeRepository employeeRepository1) {
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

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }
}
