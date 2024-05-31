package net.lab1024.smartadmin.module.support.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.support.file.FileDao;
import net.lab1024.smartadmin.module.support.file.constant.FileModuleTypeEnum;
import net.lab1024.smartadmin.module.support.file.constant.FileResponseCodeConst;
import net.lab1024.smartadmin.module.support.file.constant.FileServiceTypeEnum;
import net.lab1024.smartadmin.module.support.file.domain.dto.FileAddDTO;
import net.lab1024.smartadmin.module.support.file.domain.dto.FileDTO;
import net.lab1024.smartadmin.module.support.file.domain.dto.FileQueryDTO;
import net.lab1024.smartadmin.module.support.file.domain.entity.FileEntity;
import net.lab1024.smartadmin.module.support.file.domain.vo.FileVO;
import net.lab1024.smartadmin.module.support.file.domain.vo.UploadVO;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import net.lab1024.smartadmin.module.system.login.domain.RequestTokenBO;
import net.lab1024.smartadmin.util.SmartBaseEnumUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2019 1024lab.netInc. All rights reserved.
 * @date
 * @since JDK1.8
 */
@Service
public class FileService {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private java.util.Map<String, IFileService> fileServiceMap;
    @Value("${file-upload-service.path}")
    private String filePath = "";

    /**
     * 获取文件服务实现
     *
     * @param typeEnum
     * @return
     */
    private IFileService getFileService(FileServiceTypeEnum typeEnum) {
        /**
         * 获取文件服务
         */
        String serviceName = typeEnum.getServiceName();
        IFileService fileService = fileServiceMap.get(serviceName);
        if (null == fileService) {
            throw new RuntimeException("未找到文件服务实现类：" + serviceName);
        }
        return fileService;
    }

    /**
     * 文件上传服务
     *
     * @param file
     * @param typeEnum   文件服务类型枚举类
     * @param moduleType 文件夹类型
     * @return
     */
    public ResponseDTO<UploadVO> fileUpload(MultipartFile file, FileServiceTypeEnum typeEnum, Integer moduleType,HttpServletRequest request) {
        FileModuleTypeEnum moduleTypeEnum = SmartBaseEnumUtil.getEnumByValue(moduleType, FileModuleTypeEnum.class);
        if (null == moduleTypeEnum) {
            return ResponseDTO.wrap(FileResponseCodeConst.FILE_MODULE_ERROR);
        }
        // 获取文件服务
        IFileService fileService = this.getFileService(typeEnum);

        List<FileEntity> list =new ArrayList<>();
        FileEntity fileEntity =new FileEntity();
        fileEntity.setModuleId("1");
        fileEntity.setModuleType("1");
        fileEntity.setFileLocationType(1);
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileSize("");
        fileEntity.setFileType("");
        fileEntity.setFilePath(filePath+file.getOriginalFilename());
        HttpSession session = request.getSession();

        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        fileEntity.setCreaterUser(loginDetailVO.getId());

        list.add(fileEntity);
        fileDao.insertFileEntityBatch(list);

        ResponseDTO<UploadVO> response = fileService.fileUpload(file, moduleTypeEnum.getPath(),fileEntity.getId());

        return response;
    }

    /**
     * 根据文件绝对路径 获取文件URL
     *
     * @param path
     * @return
     */
    public ResponseDTO<String> getFileUrl(String path, FileServiceTypeEnum typeEnum) {
        IFileService fileService = this.getFileService(typeEnum);
        return fileService.getFileUrl(path);
    }

    /**
     * 批量插入
     *
     * @param fileDTOList
     */
    public void insertFileBatch(List<FileDTO> fileDTOList) {
        fileDao.insertFileBatch(fileDTOList);
    }

    /**
     * 根据module 删除文件信息
     *
     * @param id
     * @return
     */
    public ResponseDTO<String> deleteFileId(Integer id) {
        fileDao.deleteFileId(id);
        return ResponseDTO.succ();
    }

    /**
     * 根据module 获取文件信息
     *
     * @param moduleId
     * @return
     */
    public List<FileVO> listFilesByModuleId(String moduleId) {
        return fileDao.listFilesByModuleId(moduleId);
    }

    /**
     * @param filesStr 逗号分隔文件id字符串
     * @return
     */
    public List<FileVO> getFileDTOList(String filesStr) {
        if (StringUtils.isEmpty(filesStr)) {
            return Lists.newArrayList();
        }
        String[] fileIds = filesStr.split(",");
        List<Long> fileIdList = Arrays.asList(fileIds).stream().map(e -> Long.valueOf(e)).collect(Collectors.toList());
        List<FileVO> files = fileDao.listFilesByFileIds(fileIdList);
        return files;
    }

    /**
     * 分页查询文件列表
     *
     * @param queryDTO
     * @return
     */
    public ResponseDTO<PageResultDTO<FileVO>> queryListByPage(FileQueryDTO queryDTO,HttpServletRequest request) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        HttpSession session = request.getSession();
        LoginDetailVO loginDetailVO = (LoginDetailVO)session.getAttribute("app_user_login");
        List<FileVO> fileList = fileDao.queryListByPage(page, queryDTO,loginDetailVO.getId());
        if (CollectionUtils.isNotEmpty(fileList)) {
            fileList.forEach(e -> {
                // 根据文件服务类 获取对应文件服务 查询 url
                FileServiceTypeEnum serviceTypeEnum = SmartBaseEnumUtil.getEnumByValue(e.getFileLocationType(), FileServiceTypeEnum.class);
                IFileService fileService = this.getFileService(serviceTypeEnum);
                e.setFileUrl(fileService.getFileUrl(e.getFilePath()).getData());
            });
        }
        PageResultDTO<FileVO> pageResultDTO = SmartPageUtil.convert2PageResult(page, fileList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 分页查询文件列表
     *
     * @param queryDTO
     * @return
     */
    public ResponseDTO<PageResultDTO<FileVO>> queryTeacherListByPage(FileQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        List<FileVO> fileList = fileDao.queryTeacherListByPage(page, queryDTO,queryDTO.getFileUploadType());
        if (CollectionUtils.isNotEmpty(fileList)) {
            fileList.forEach(e -> {
                // 根据文件服务类 获取对应文件服务 查询 url
                FileServiceTypeEnum serviceTypeEnum = SmartBaseEnumUtil.getEnumByValue(e.getFileLocationType(), FileServiceTypeEnum.class);
                IFileService fileService = this.getFileService(serviceTypeEnum);
                e.setFileUrl(fileService.getFileUrl(e.getFilePath()).getData());
            });
        }
        PageResultDTO<FileVO> pageResultDTO = SmartPageUtil.convert2PageResult(page, fileList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 根据id 下载文件
     *
     * @param id
     * @param request
     * @return
     */
    public ResponseEntity<byte[]> downLoadById(Long id, HttpServletRequest request) {
        FileEntity entity = fileDao.selectById(id);
        if (null == entity) {
            throw new RuntimeException("文件信息不存在");
        }

        // 根据文件服务类 获取对应文件服务 查询 url
        FileServiceTypeEnum serviceTypeEnum = SmartBaseEnumUtil.getEnumByValue(entity.getFileLocationType(), FileServiceTypeEnum.class);
        IFileService fileService = this.getFileService(serviceTypeEnum);
        ResponseEntity<byte[]> stream = fileService.fileDownload(entity.getFilePath(), entity.getFileName(), request);
        return stream;
    }

    /**
     * 系统文件保存通用接口
     * @param addDTO
     * @return
     */
    public ResponseDTO<String> saveFile(FileAddDTO addDTO, RequestTokenBO requestToken) {
        FileEntity entity = SmartBeanUtil.copy(addDTO,FileEntity.class);
        entity.setCreaterUser(requestToken.getRequestUserId());
//        entity.setCreateTime(new Date());
        fileDao.insert(entity);
        return ResponseDTO.succ();
    }

    public List<FileVO> queryListByAll(FileEntity fileEntity) {
        return fileDao.queryListByAll(fileEntity);
    }

    public ResponseDTO<String> updateType(Integer id, Integer fileUploadType) {
        fileDao.updateType(id,fileUploadType);
        return ResponseDTO.succ();
    }
}
