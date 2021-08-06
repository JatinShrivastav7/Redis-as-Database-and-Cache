package com.example.redisDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RedisHash("Employee")
public class Employee implements Serializable {
    @Id
    private int employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeeDepartment;
}
