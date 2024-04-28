package com.example.demo.controller;

import com.example.demo.dto.Result;
import com.example.demo.entity.Department;
import com.example.demo.entity.Users;
import com.example.demo.service.DepartmentsInterface;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentsInterface departmentsInterface;
    @GetMapping("/getAll")
    public Result getAllDepartments(@RequestHeader("Authorization") String token) {
        Users user = UserUtils.getUserInfoFromToken(token);
        try {
            if (user.getRole().equals("SuperAdmin")) {

                List<Department> departments = departmentsInterface.getAllDepartments();
                return Result.ok(departments);
            }else if (user.getRole().equals("DepartmentAdmin")){

                List<Department> departments = departmentsInterface.selectDepartments( user.getDepartmentid()) ;
                return Result.ok(departments);
            }
            else {
                return Result.fail("权限不足");
            }
        }catch (Exception e) {
            return Result.fail("获取部门列表失败: " + e.getMessage());
        }
    }
    @PostMapping("/create")
    public Result createDepartment(@RequestBody Department department) {
        System.out.println(department.getDepartmentname()+department.getDepartmentid());
        if (departmentsInterface.createDepartment(department.getDepartmentname())) {
            return Result.ok("部门创建成功");
        } else {
            return Result.fail("部门创建失败");
        }
    }
    @PostMapping("/update")
    public Result updateDepartmentName(@RequestBody Department department) {

      try {
          if (departmentsInterface.updateDepartmentNameById(department.getDepartmentid(), department.getDepartmentname())) {
              return Result.ok("部门名称更新成功");
          } else {
              return Result.fail("部门名称更新失败");
          }
      }catch (Exception e) {
          return Result.fail("部门名称更新失败: " + e.getMessage());
      }
    }
    @DeleteMapping("/delete")
    public Result deleteDepartmentByName(@RequestParam String departmentName) {
        try {
            if (departmentsInterface.deleteDepartmentByName(departmentName)) {
                return Result.ok("部门删除成功");
            } else {
                return Result.fail("部门删除失败,部门不存在");
            }
        }catch (Exception e) {
            return Result.fail("部门删除失败: " + e.getMessage());
        }

    }
    @GetMapping("/getDepartmentsByName")
    public Result getDepartmentsByName(@RequestParam String departmentName) {

        try {
            List<Department> departments = departmentsInterface.getDepartmentsByName(departmentName);
            if (departments != null && !departments.isEmpty()) {
                return Result.ok(departments);
            } else {
                return Result.fail("部门不存在");
            }
        } catch (Exception e) {
            return Result.fail("查询报错: " + e.getMessage());
        }
    }
}
