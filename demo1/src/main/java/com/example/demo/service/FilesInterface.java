package com.example.demo.service;

import com.example.demo.dto.FileUploadDto;
import com.example.demo.entity.Files;

import java.util.List;

public interface FilesInterface {
    boolean uploadFileAndSetPermission(FileUploadDto fileUploadDto);
    boolean deleteFileById(String SerialNumber);
    List<Files> getFilesByRoleAndDepartment(String PermissionType, Integer departmentId);
    boolean updateFile(FileUploadDto fileUploadDto);
}
