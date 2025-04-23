package org.example.employeethymeleaf.controller;


import org.example.employeethymeleaf.model.Employee;
import org.example.employeethymeleaf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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



    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }
    //signUp an employee
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute  ("employee")Employee employee) {
       employeeService.creaetEmployee(employee);
        return "addEmployee";
    }


}
