package com.example.redisDemo.service;

import com.example.redisDemo.entity.Employee;
import com.example.redisDemo.repository.IEmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements IEmployeeService {

   @Autowired
   private IEmployeeDao employeeDao;
    @Override
    public Employee addDetails(Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    @Override
    public Employee employeeDetailsById(int employeeId) {
        return employeeDao.findById(employeeId);
    }

    @Override
    public boolean deleteDetails(int employeeId) {
       return employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public List<Employee> employeeDetails() {
        return this.employeeDao.findAll();
    }
}
