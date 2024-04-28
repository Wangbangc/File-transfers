package com.example.demo.service.impl;

import com.example.demo.entity.Department;
import com.example.demo.mapper.DepartmentsMapper;
import com.example.demo.service.DepartmentsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsImpl implements DepartmentsInterface {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Override
    public boolean createDepartment(String departmentName) {
        return departmentsMapper.insertDepartment(departmentName) > 0;
    }
    @Override
    public boolean updateDepartmentNameById(Integer departmentId, String departmentName) {
        return departmentsMapper.updateDepartmentNameById(departmentId, departmentName) > 0;
    }

    @Override
    public boolean deleteDepartmentByName(String departmentName) {
        return departmentsMapper.deleteDepartmentByName(departmentName) > 0;
    }
    @Override
    public List<Department> getDepartmentsByName(String departmentName) {
        return departmentsMapper.getDepartmentsByName(departmentName);
    }
    @Override
    public List<Department> getAllDepartments() {
        return departmentsMapper.getAllDepartments();
    }

    @Override
    public List<Department> selectDepartments(Integer departmentid) {
        return departmentsMapper.selectdepartment(departmentid);
    }
}
