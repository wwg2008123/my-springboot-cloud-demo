package com.wwg.servicedemo01.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private Integer age;
    private String userNo;
    private List<Role> roles;
    private String password;
    private String token;
}
