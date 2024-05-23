package com.sergei.spring.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sergei.spring.rest.entity.Employee;
import com.sergei.spring.rest.exception_handling.NoSuchEmployeeException;
import com.sergei.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();

        return employeeList;
    }

    @GetMapping("/employees/{id}")
    public Object getEmployee(@PathVariable int id) throws JsonProcessingException {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID= " + id + " in Database!");
        }
        return employee;
    }
}
