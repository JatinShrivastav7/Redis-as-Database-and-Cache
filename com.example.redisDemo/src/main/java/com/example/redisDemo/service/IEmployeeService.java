package com.example.redisDemo.service;

import com.example.redisDemo.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> employeeDetails();
    public Employee addDetails(Employee employee);

    Employee employeeDetailsById(int employeeId);

    public boolean deleteDetails(int employeeId);
}
