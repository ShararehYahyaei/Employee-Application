package org.example.employeethymeleaf.controller;


import org.example.employeethymeleaf.model.Employee;
import org.example.employeethymeleaf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // display all employees

    @GetMapping("/index")
    public String viewHomePage(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        System.out.println(allEmployees.size()+"ddgdgdg");
        model.addAttribute("listEmployees", allEmployees);
        return "index";
    }


    //signUp an employee
    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee, Model model) {
        Employee employee1 = employeeService.creaetEmployee(employee);
        model.addAttribute("employee", employee1);
        return "addEmployee";
    }


}
