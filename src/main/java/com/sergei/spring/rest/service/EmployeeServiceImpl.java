package com.sergei.spring.rest.service;

import com.sergei.spring.rest.dao.EmployeeDAO;
import com.sergei.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {

        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveNewEmployee(Employee employee) {
        employeeDAO.saveNewEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(final int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(final int id) {
        employeeDAO.deleteEmployee(id);
    }
}
