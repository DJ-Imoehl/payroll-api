package com.imoehl.payrollapi.controllers;

import com.imoehl.payrollapi.exceptions.EmployeeNotFoundException;
import com.imoehl.payrollapi.models.Employee;
import com.imoehl.payrollapi.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> findAll() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(new Employee(newEmployee.getName(), newEmployee.getRole()));
    }

    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee replacement, @PathVariable Long id) {
        return repository
                .findById(id)
                .map(employee -> {
                    employee.setName(replacement.getName());
                    employee.setRole(replacement.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    replacement.setId(id);
                    return replacement;
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
