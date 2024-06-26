package net.lab1024.smartadmin.module.support.file;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.support.file.constant.FileServiceTypeEnum;
import net.lab1024.smartadmin.module.support.file.domain.dto.FileAddDTO;
import net.lab1024.smartadmin.module.support.file.domain.dto.FileQueryDTO;
import net.lab1024.smartadmin.module.support.file.domain.entity.FileEntity;
import net.lab1024.smartadmin.module.support.file.domain.vo.FileVO;
import net.lab1024.smartadmin.module.support.file.domain.vo.UploadVO;
import net.lab1024.smartadmin.module.support.file.service.FileService;
import net.lab1024.smartadmin.module.system.login.domain.RequestTokenBO;
import net.lab1024.smartadmin.util.SmartRequestTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 文件服务
 * @Author: sbq
 * @CreateDate: 2019/7/18 9:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.Admin.MANAGER_FILE})
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件本地上传", notes = "文件本地上传")
    @PostMapping("/api/file/localUpload/{moduleType}")
    public ResponseDTO<UploadVO> localUpload(MultipartFile file, @PathVariable Integer moduleType,HttpServletRequest request) throws Exception {
        return fileService.fileUpload(file, FileServiceTypeEnum.LOCAL, moduleType,request);
    }

    @ApiOperation(value = "获取本地文件URL", notes = "获取文件URL")
    @PostMapping("/api/file/get")
    public ResponseDTO<String> localGetFile(String path) {
        return fileService.getFileUrl(path, FileServiceTypeEnum.LOCAL);
    }

    @ApiOperation(value = "文件阿里云上传", notes = "文件阿里云上传")
    @PostMapping("/api/file/aliYunUpload/{moduleType}")
    public ResponseDTO<UploadVO> aliYunUpload(MultipartFile file, @PathVariable Integer moduleType,HttpServletRequest request) throws Exception {
        return fileService.fileUpload(file, FileServiceTypeEnum.ALI_OSS, moduleType,request);
    }

    @ApiOperation(value = "获取阿里云文件URL", notes = "获取阿里云文件URL")
    @PostMapping("/api/file/aliYunGet")
    public ResponseDTO<String> aliYunGet(String path) {
        return fileService.getFileUrl(path, FileServiceTypeEnum.ALI_OSS);
    }

    @ApiOperation(value = "文件七牛云上传", notes = "文件七牛云上传")
    @PostMapping("/api/file/qiNiuUpload/{moduleType}")
    public ResponseDTO<UploadVO> qiNiuUpload(MultipartFile file, @PathVariable Integer moduleType,HttpServletRequest request) throws Exception {
        return fileService.fileUpload(file, FileServiceTypeEnum.QI_NIU_OSS, moduleType,request);
    }

    @ApiOperation(value = "获取七牛云文件URL", notes = "获取七牛云URL")
    @PostMapping("/api/file/qiNiuGet")
    public ResponseDTO<String> qiNiuGet(String path) {
        return fileService.getFileUrl(path, FileServiceTypeEnum.QI_NIU_OSS);
    }

    @ApiOperation(value = "系统文件查询学员")
    @PostMapping("/api/file/query")
    public ResponseDTO<PageResultDTO<FileVO>> queryListByPage(@RequestBody FileQueryDTO queryDTO,HttpServletRequest request) {
        return fileService.queryListByPage(queryDTO,request);
    }
    @ApiOperation(value = "系统文件查询教师")
    @PostMapping("/api/file/queryTeacher")
    public ResponseDTO<PageResultDTO<FileVO>> queryTeacherListByPage(@RequestBody FileQueryDTO queryDTO) {
        return fileService.queryTeacherListByPage(queryDTO);
    }

    @ApiOperation(value = "系统文件删除")
    @GetMapping("/api/file/delete")
    public ResponseDTO<String> deleteFileId(@RequestParam("id") Integer id) {
        return fileService.deleteFileId(id);
    }

    @ApiOperation(value = "文件修改类型")
    @GetMapping("/api/file/updateType")
    public ResponseDTO<String> updateType(@RequestParam("id") Integer id,@RequestParam("fileUploadType") Integer fileUploadType) {
        return fileService.updateType(id,fileUploadType);
    }

    @ApiOperation(value = "系统文件查询---没有分页")
    @GetMapping("/api/file/queryAll")
    public List<FileVO> queryListByPage(FileEntity fileEntity) {
        return fileService.queryListByAll(fileEntity);
    }

    @ApiOperation(value = "系统文件下载通用接口（流下载）")
    @GetMapping("/api/file/downLoad")
    @NoNeedLogin
    public ResponseEntity<byte[]> downLoadById(Long id, HttpServletRequest request) {
        return fileService.downLoadById(id, request);
    }

    @ApiOperation(value = "系统文件保存通用接口")
    @PostMapping("/api/file/save")
    public ResponseDTO<String> saveFile(@Valid @RequestBody FileAddDTO addDTO) {
        RequestTokenBO requestToken = SmartRequestTokenUtil.getRequestUser();
        return fileService.saveFile(addDTO,requestToken);
    }
}
