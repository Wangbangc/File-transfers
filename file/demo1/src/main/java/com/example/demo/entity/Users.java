package com.example.demo.entity;

import lombok.Getter;

@Getter
public class Users {
 private String name;

    private Integer userid;


    private String username;


    private String password;


    private String role;



    private Integer departmentid;

    public Users(Integer userid, String username, String password, String role, Integer departmentid, String name) {

    this.userid = userid;
    this.username = username;
    this.password = password;
    this.role = role;
    this.departmentid = departmentid;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }



    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }


    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }
}