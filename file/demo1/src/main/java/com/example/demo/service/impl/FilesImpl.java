package com.example.demo.service.impl;

import com.example.demo.dto.FileUploadDto;
import com.example.demo.entity.Files;
import com.example.demo.mapper.FilePermissionsMapper;
import com.example.demo.mapper.FilesMapper;
import com.example.demo.service.FilesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class FilesImpl implements FilesInterface {
    @Autowired
    private FilesMapper filesMapper;
    @Autowired
    private FilePermissionsMapper filePermissionsMapper;
    @Override
    @Transactional
    public boolean updateFile(FileUploadDto fileUploadDto) {
        FileUploadDto fileUploadDto1 = filesMapper.selectBySerialNumber(fileUploadDto.getSerialNumber());
        fileUploadDto1.setFileContent(fileUploadDto.getFileContent());

        Random rand = new Random();
        String num = String.valueOf(rand.nextInt(900000000) + 100000000);
        fileUploadDto1.setSerialNumber(num);
        int fileId = filesMapper.insertFile(fileUploadDto1);

        System.out.println(fileId);
        if (fileId > 0) {
            int result = filePermissionsMapper.insertFilePermission(num, fileUploadDto1.getDepartmentId(), fileUploadDto1.getPermissionType());
            if (result > 0) {
                return true;
            }
        }
        return false;
    }
    @Override
    @Transactional
    public boolean uploadFileAndSetPermission(FileUploadDto fileUploadDto) {
        Random rand = new Random();
        String num = String.valueOf(rand.nextInt(900000000) + 100000000);
        fileUploadDto.setSerialNumber(num);
        int fileId = filesMapper.insertFile(fileUploadDto);

        System.out.println(fileId);
        if (fileId > 0) {
            int result = filePermissionsMapper.insertFilePermission(num, fileUploadDto.getDepartmentId(), fileUploadDto.getPermissionType());
            if (result > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteFileById(String SerialNumber) {
            int fileDeleted = filesMapper.deleteFileById(SerialNumber);
            if (fileDeleted > 0) {
                return true;
            }
        return false;
    }

    @Override
    public List<Files> getFilesByRoleAndDepartment(String PermissionType, Integer departmentId) {

        return filesMapper.getFilesByRoleAndDepartment(PermissionType, departmentId);
    }
}
