package com.sergei.spring.rest.service;

import com.sergei.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public void saveNewEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
