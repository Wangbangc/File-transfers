package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

public interface UsersInterface {
    List<Users> getAllUsers();
    boolean register(Users request);
    boolean insertUser(Users user);
    Users login(String username, String password);
    Users selectByUsername(String username);
    boolean updateUserRoleByUsername(String username, String role);

    boolean deleteUserByUsername(String username);
    boolean updateUser(Users user);

    List<Department> selectDepartments(Integer departmentid);

    List<Users> getUsersByKeyword(String keyword);
}
