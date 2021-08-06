package com.example.redisDemo.repository;

import com.example.redisDemo.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {
    public Employee saveEmployee(Employee employee);

    public List<Employee> findAll();

    public Employee findById(int employeeId);

    boolean deleteEmployee(int employeeId);
}
