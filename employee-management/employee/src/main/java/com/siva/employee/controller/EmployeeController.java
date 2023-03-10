package com.siva.employee.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siva.employee.model.Address;
import com.siva.employee.model.Employee.Employee;
import com.siva.employee.service.EmployeeService;
import com.siva.employee.service.SequenceGeneratorService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all the employees
    @GetMapping("/allEmployees")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    // Get employee by Id
    @GetMapping("employee/{id}")
    public String getEmployee(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeWithCode(id));
        return "employee";
    }

    // Going to create employee page
    @GetMapping("/createEmployee")
    public String createEmployeeForm(Model model) {
        return "employee";
    }

    // Creating a new employee
    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute(name = "employee") Employee employee) {
        employee.setId(seqGenerator.generateSequence(Employee.SEQ_NAME));
        employeeService.createEmployee(employee);
        return "redirect:/allEmployees";
    }

    // Deleting a employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@RequestParam(name = "id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/allEmployees";
    }

    //Filtering based on conditon
    @GetMapping("/filter")
    public String filterEmployee(@RequestParam Map<String, String> request, Model model) {
        model.addAttribute("employees", employeeService.filterEmployees(request));
        return "employees";
    }

    // Searching a employee whith his name
    // @GetMapping("/search")
    // public String searchEmployee(@RequestParam(name = "name") String name, Model model) {
    //     model.addAttribute("employees", employeeService.findByName(name));
    //     return "employees";
    // }

    // Updating a employee
    // @PatchMapping("/update")
    // public String updateEmployee(@RequestParam(name="id")int id, Model model){

    // }

    /***************************** Model Attribute ******************************** */
    @ModelAttribute("employee")
    public Employee getEmployee() {
        return new Employee();
    }

    @ModelAttribute("address")
    public Address getAddress() {
        return new Address();
    }

}
