package com.sergei.spring.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sergei.spring.rest.entity.Employee;
import com.sergei.spring.rest.exception_handling.EmployeeIncorrectData;
import com.sergei.spring.rest.exception_handling.NoSuchEmployeeException;
import com.sergei.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            throw new NoSuchEmployeeException("There is no employee with ID= " + id + "in Database!");
        }
        return employee;
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {

        //creating an object of EmployeeIncorrectData and setting an error message
        EmployeeIncorrectData employeeIncorrectData = new EmployeeIncorrectData();
        employeeIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(employeeIncorrectData, HttpStatus.NOT_FOUND);
    }


}
