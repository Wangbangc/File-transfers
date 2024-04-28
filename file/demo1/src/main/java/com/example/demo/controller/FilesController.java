package com.example.demo.controller;

import com.example.demo.dto.FileUploadDto;
import com.example.demo.dto.Result;
import com.example.demo.entity.Files;
import com.example.demo.entity.Users;
import com.example.demo.service.FilesInterface;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private FilesInterface filesInterface;

    @PostMapping("/upload")
    public Result uploadFileAndSetPermission(@RequestHeader("Authorization") String token,@RequestParam("file") MultipartFile file, @RequestParam("departmentId") Integer departmentId, @RequestParam("permissionType") String permissionType) {
      System.out.println(file.getOriginalFilename());
      System.out.println(file.getContentType());
      try {
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setFileName(file.getOriginalFilename());
            fileUploadDto.setFileType(file.getContentType());
            fileUploadDto.setPublisherId(UserUtils.getUserInfoFromToken(token).getUserid());
            fileUploadDto.setDepartmentId(departmentId);
            fileUploadDto.setPermissionType(permissionType);
            try {
                fileUploadDto.setFileContent(file.getBytes());
            } catch (IOException e) {
                return Result.fail("File upload failed: " + e.getMessage());
            }

            if (filesInterface.uploadFileAndSetPermission(fileUploadDto)) {
                return Result.ok("File uploaded successfully");
            } else {
                return Result.fail("File upload failed");
            }
        }catch (Exception e) {
            return Result.fail("File upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result deleteFile(@RequestParam String SerialNumber) {
       try {
           boolean isDeleted = filesInterface.deleteFileById(SerialNumber);
           if (isDeleted) {
               return Result.ok("文件删除成功");
           } else {
               return Result.fail("文件删除失败");
           }
       }catch (Exception e) {
           return Result.fail("文件删除失败: " + e.getMessage());
       }
    }

    @GetMapping("/getFiles")
    public Result getFiles(@RequestHeader("Authorization") String token) {
       Users users= UserUtils.getUserInfoFromToken(token);
       try {

           List<Files> files = filesInterface.getFilesByRoleAndDepartment(users.getRole(), users.getDepartmentid());

           if (files != null && !files.isEmpty()) {
               return Result.ok(files);
           } else {
               return Result.fail("No files found");

           }
       }catch (Exception e) {
           return Result.fail("Failed to get files: " + e.getMessage());
       }
    }
    @PutMapping("/update")
    public Result updateFile(@RequestParam("file")  MultipartFile file,@RequestParam("serialNumber") String SerialNumber) {
        try {

          FileUploadDto fileUploadDto = new FileUploadDto();
System.out.println(file.getBytes());
            fileUploadDto.setFileContent(file.getBytes());

            fileUploadDto.setSerialNumber(SerialNumber);

            boolean isUpdated = filesInterface.updateFile(fileUploadDto);
            if (isUpdated) {
                return Result.ok("文件更新成功");
            } else {
                return Result.fail("文件更新失败");
            }
        } catch (Exception e) {
            return Result.fail("文件更新失败: " + e.getMessage());
        }
    }
}