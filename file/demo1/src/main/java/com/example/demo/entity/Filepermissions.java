package com.example.demo.entity;

public class Filepermissions extends FilepermissionsKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column filepermissions.PermissionType
     *
     * @mbggenerated
     */
    private String permissiontype;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filepermissions.PermissionType
     *
     * @return the value of filepermissions.PermissionType
     *
     * @mbggenerated
     */
    public String getPermissiontype() {
        return permissiontype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filepermissions.PermissionType
     *
     * @param permissiontype the value for filepermissions.PermissionType
     *
     * @mbggenerated
     */
    public void setPermissiontype(String permissiontype) {
        this.permissiontype = permissiontype == null ? null : permissiontype.trim();
    }
}