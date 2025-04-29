package org.example.employeethymeleaf.service;

import org.example.employeethymeleaf.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee creaetEmployee(Employee employee);
    void deleteEmployee(Long id);
    Page<Employee> findPaginated(int pageNumber, int pageSize,
                                 String sortField, String sortDirection);
}
