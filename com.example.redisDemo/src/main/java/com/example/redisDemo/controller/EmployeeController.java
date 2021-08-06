package com.example.redisDemo.controller;

import com.example.redisDemo.entity.Employee;
import com.example.redisDemo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableCaching
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

   @GetMapping("/getDetails")
   public ResponseEntity<List<Employee>> getEmployeeDetails()
   {

       List<Employee> employeesList=this.employeeService.employeeDetails();
       if(employeesList.size()<=0)
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       return ResponseEntity.of(Optional.of(employeesList));

   }
    @GetMapping("/getDetails/{employeeId}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable String employeeId)
    {
        Employee empAttributes=this.employeeService.employeeDetailsById(Integer.parseInt(employeeId));
        if(empAttributes==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(empAttributes));
    }

    @PostMapping("/addDetails")
    public ResponseEntity<Employee> addEmployeeDetails(@RequestBody Employee employeeBody) {
        Employee empAttributes = null;
        try {
            empAttributes = this.employeeService.addDetails(employeeBody);
            System.out.println(empAttributes);
            return ResponseEntity.of(Optional.of(empAttributes));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete_details/{employee_id}")
    public ResponseEntity<HttpStatus> deleteEmployeeDetails(@PathVariable String employeeId)
    {
        boolean result=this.employeeService.deleteDetails(Integer.parseInt(employeeId));
        if(result)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
