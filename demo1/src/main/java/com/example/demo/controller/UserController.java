package com.example.demo.controller;

import com.example.demo.dto.Result;
import com.example.demo.entity.Department;
import com.example.demo.entity.Users;
import com.example.demo.service.UsersInterface;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersInterface usersInterface;

    @PostMapping("/register")
    public Result register(@RequestBody Users request) {
       try {
           if (usersInterface.register(request)) {
               return Result.ok();
           } else {
               //生成token
               String token = UserUtils.generateUserToken(request.getUserid(), request.getUsername(), request.getPassword(),"SuperAdmin", request.getDepartmentid(), request.getName());
               return Result.fail(token);
           }
       }catch (Exception e){
           return Result.fail(e.getMessage());
       }
    }

    @PostMapping("/insertUser")
    public Result insertUser(@RequestHeader("Authorization")String token ,@RequestBody Users request) {
      try {
          if(request.getDepartmentid()==null){
              request.setDepartmentid(UserUtils.getUserInfoFromToken(token).getDepartmentid());
          }
          System.out.println(request.getUsername());
          if (usersInterface.insertUser(request)) {
              return Result.ok();
          } else {
              return Result.fail("插入失败,用户名重复");
          }
      }catch (Exception e){
          return Result.fail(e.getMessage());
      }
    }

    @PostMapping("/login")
    public Result login(@RequestBody Users request) {
    try {
        System.out.println(request.getUsername());
        Users user = usersInterface.login(request.getUsername(), request.getPassword());

        if (user != null) {
            String token = UserUtils.generateUserToken(user.getUserid(), user.getUsername(), user.getPassword(), user.getRole(), user.getDepartmentid(), user.getName());
            List<String> s=new ArrayList<>();
            s.add(token);
            s.add(user.getRole());
            return Result.ok(s);
        } else {
            return Result.fail("登录失败");
        }
    }catch (Exception e){
        return Result.fail(e.getMessage());
    }
    }

    @GetMapping("/getAllUsers")
    public Result getAllUsers(@RequestHeader("Authorization") String token) {
        Users user = UserUtils.getUserInfoFromToken(token);

            try {
                if (user.getRole().equals("SuperAdmin")) {
                List<Users> users = usersInterface.getAllUsers();
                return Result.ok(users);
            }else if(user.getRole().equals("DepartmentAdmin")) {
                    List<Department> departments = usersInterface.selectDepartments( user.getDepartmentid()) ;
               return Result.ok(departments);
                }else {
                return Result.fail("权限不足");
            }
            } catch (Exception e) {
                return Result.fail("获取用户列表失败: " + e.getMessage());
            }

    }

    @GetMapping("/getUserByUsername")
    public Result getUserByUsername(@RequestParam String username) {
       try {
           Users user = usersInterface.selectByUsername(username);
           if (user != null) {
               return Result.ok(user);
           } else {
               return Result.fail("用户不存在");
           }
       }catch (Exception e){
           return Result.fail(e.getMessage());
       }
    }

    @DeleteMapping("/deleteUserByUsername")
    public Result deleteUserByUsername(@RequestParam String username) {
        System.out.println(username);
       try {
           if (usersInterface.deleteUserByUsername(username)) {
               return Result.ok("删除成功");
           } else {
               return Result.fail("删除失败,该员工不存在");
           }
       }catch (Exception e){
           return Result.fail(e.getMessage()+"删除失败,该员工不存在");
       }
    }
    @PostMapping("/updateUserRole")
    public Result updateUserRole(@RequestBody Users user) {
       try {
           if (usersInterface.updateUserRoleByUsername(user.getUsername(), user.getRole())) {
               return Result.ok("权限修改成功");
           } else {
               return Result.fail("权限修改失败");
           }
       }catch (Exception e){
           return Result.fail(e.getMessage());
       }
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody Users user) {
        System.out.println(user.getUsername());
        if (user.getDepartmentid()==null){
           user.setDepartmentid(usersInterface.selectByUsername(user.getUsername()).getDepartmentid());
        }
      try {
          if (usersInterface.updateUser(user)) {
              return Result.ok();
          } else {
              return Result.fail("更新失败");
          }
      }catch (Exception e){
          return Result.fail(e.getMessage());
      }
    }
   @GetMapping("user")
   public  Result electUser(@RequestHeader("Authorization") String token){
        Users users1 = UserUtils.getUserInfoFromToken(token);
        return Result.ok(users1);
   }
    @PutMapping("/update2")
    public Result updateUser2(@RequestHeader("Authorization") String token, @RequestBody Users user) {
       Users users1 = UserUtils.getUserInfoFromToken(token);
      System.out.println(user.getPassword());
      user.setUsername(users1.getUsername());
        user.setDepartmentid(users1.getDepartmentid());
        try {
            if (usersInterface.updateUser(user)) {
                return Result.ok();
            } else {
                return Result.fail("更新失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
    @GetMapping("/getUsersByKeyword")
    public Result getUsersByKeyword(@RequestParam String keyword) {
        try {
            List<Users> users = usersInterface.getUsersByKeyword(keyword);
            if (users != null && !users.isEmpty()) {
                return Result.ok(users);
            } else {
                return Result.fail("用户不存在");
            }
        } catch (Exception e) {
            return Result.fail("搜索错误: " + e.getMessage());
        }
    }

}
