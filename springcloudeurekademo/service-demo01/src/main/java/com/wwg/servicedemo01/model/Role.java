package com.wwg.servicedemo01.model;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String roleCode;
    private String roleName;
    private List<Menu> menus;
}
