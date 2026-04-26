package com.thymeleaf.controller;

import com.thymeleaf.dao.EmployeeRepository;
import com.thymeleaf.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//HTTP requests handle functions
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/list")
    public String getEmployees(Model model){
        model.addAttribute("employees",employeeRepository.findAllByOrderByLastNameAsc());

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        model.addAttribute("employee",new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String addEmployee(@ModelAttribute("employee")Employee employee){
        employeeRepository.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theid,
                                    Model model){
        model.addAttribute("employee",employeeRepository.findById(theid));
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId")int theid){
        employeeRepository.deleteById(theid);
        return "redirect:/employees/list";
    }

}
