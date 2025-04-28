package org.example.employeethymeleaf.controller;


import org.example.employeethymeleaf.model.Employee;
import org.example.employeethymeleaf.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("listEmployees", allEmployees);
      return findPaginated(1,model);
    }


    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    //signUp an employee
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.creaetEmployee(employee);
        return "addEmployee";
    }


    @PostMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable(value = "employeeId") Long id) {
        //todo call delete method from service
        employeeService.deleteEmployee(id);
        return "redirect:/index";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
                                Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNumber, pageSize);
        List<Employee> employees = page.getContent();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("employees", employees);
        return "index";
    }

}
