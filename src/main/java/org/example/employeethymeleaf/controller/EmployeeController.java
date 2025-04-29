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

    //todo  display all employees

    @GetMapping("/index")
    public String viewHomePage(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", allEmployees);
        int pageNumber = 1; // default page number
        String sortField = "firstName"; // default sorting field (or any other field)
        String sortDirection = "ASC";
      return findPaginated(pageNumber,sortField,sortDirection,model);
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
                                @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
                                @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNumber, pageSize, sortField, sortDir);
        List<Employee> employees = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("employees", employees);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "index";
    }
}
