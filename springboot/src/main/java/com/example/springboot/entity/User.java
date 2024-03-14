package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Integer id;
    private String name;
    private String username;
    private Integer age;
    private Integer account;
    private Integer score;
    private String sex;
    private String phone;
    private String address;
}
