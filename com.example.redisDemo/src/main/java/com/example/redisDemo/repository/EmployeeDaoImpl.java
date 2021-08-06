package com.example.redisDemo.repository;

import com.example.redisDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="Employee";
    @Override
    public Employee saveEmployee(Employee employee) {
            redisTemplate.opsForHash().put(KEY, employee.getEmployeeId(), employee);
            return employee;
    }
    @Override
    public List<Employee> findAll()
    {
       return redisTemplate.opsForHash().values(KEY);
    }

    @Override
    @Cacheable(key = "#employeeId", value = "Employee",unless = "#result.employeeId<103")
    public Employee findById(int employeeId) {
        System.out.println("called findById() from database");
        return (Employee) redisTemplate.opsForHash().get(KEY,employeeId);
    }

    @Override
    @CacheEvict(key = "#employeeId",value = "Employee")
    public boolean deleteEmployee(int employeeId) {
        try{
            redisTemplate.opsForHash().delete(KEY,employeeId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
