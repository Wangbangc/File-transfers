package com.example.demo.service.impl;

import com.example.demo.entity.Department;
import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.service.UsersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UsersInterface {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public boolean insertUser(Users user) {
        Users user1 = usersMapper.selectByUsername(user.getUsername());
        if (user1 != null) {
            return false;
        }
       if (usersMapper.insert(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Users login(String username, String password) {
        Users user = usersMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; }

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.getAllUsers();
    }

    @Override
    public boolean register (Users request) {
//查询有没有这个用户
        Users user = usersMapper.selectByUsername(request.getUsername());
        if (user != null) {
            return false;
        }
        request.setDepartmentid(1);
        request.setRole("SuperAdmin");
      if (usersMapper.insert(request) > 0) {
            return true;
        }
        return false;
    }
    @Override
    public Users selectByUsername(String username) {
        return usersMapper.selectByUsername(username);
    }

    @Override
    public boolean deleteUserByUsername(String username) {
        return usersMapper.deleteByUsername(username) > 0;
    }
    @Override
    public boolean updateUserRoleByUsername(String username, String role) {
        return usersMapper.updateRoleByUsername(username, role) > 0;
    }
    @Override
    public boolean updateUser(Users user) {
        return usersMapper.updateUser(user) > 0;
    }

    @Override
    public List<Department> selectDepartments(Integer departmentid) {
        return usersMapper.selectdepartment(departmentid);
    }
    @Override
    public List<Users> getUsersByKeyword(String keyword) {
        return usersMapper.getUsersByKeyword(keyword);
    }
}