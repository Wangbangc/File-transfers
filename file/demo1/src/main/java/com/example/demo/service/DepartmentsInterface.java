package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;

public interface DepartmentsInterface {
    boolean createDepartment(String departmentName);
    boolean updateDepartmentNameById(Integer departmentId, String departmentName);
    boolean deleteDepartmentByName(String departmentName);
    List<Department> getAllDepartments();

    List<Department> selectDepartments(Integer departmentid);

    List<Department> getDepartmentsByName(String departmentName);
}
