package com.example.demo.mapper;

import com.example.demo.entity.Department;
import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    Integer insert(Users record);
    List<Users> getUsersByKeyword(String keyword);
    int deleteByUsername(String username);
    int deleteByPrimaryKey(Integer userid);
    int updateRoleByUsername(String username, String role);
    int updateUser(Users user);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_user
     *
     * @mbggenerated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_user
     *
     * @mbggenerated
     */
    int insertSelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_user
     *
     * @mbggenerated
     */
    Users selectByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Users record);

    Users selectByUsername(String username);

    List<Users> getAllUsers();

    List<Department> selectdepartment(Integer departmentid);
}