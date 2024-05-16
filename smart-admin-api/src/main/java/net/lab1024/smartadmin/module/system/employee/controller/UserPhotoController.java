package net.lab1024.smartadmin.module.system.employee.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoAddDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoUpdateDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.UserPhotoQueryDTO;
import net.lab1024.smartadmin.module.system.employee.domain.entity.UserPhotoEntity;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoVO;
import net.lab1024.smartadmin.module.system.employee.domain.vo.UserPhotoExcelVO;
import net.lab1024.smartadmin.module.system.employee.service.UserPhotoService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [  ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-14 14:49:16
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {""})
public class UserPhotoController extends BaseController {

    @Autowired
    private UserPhotoService userPhotoService;

    @ApiOperation(value = "分页查询",notes = "@author SMS")
    @PostMapping("/userPhoto/page/query")
    public ResponseDTO<PageResultDTO<UserPhotoVO>> queryByPage(@RequestBody UserPhotoQueryDTO queryDTO) {
        return userPhotoService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "根据Id查询",notes = "@author SMS")
    @GetMapping("/userPhoto/queryById")
    public ResponseDTO<UserPhotoVO> queryById(@RequestParam("id")Long id) {
        return userPhotoService.queryById(id);
    }

    @ApiOperation(value = "添加",notes = "@author SMS")
    @PostMapping("/userPhoto/add")
    public ResponseDTO<UserPhotoEntity> add(@RequestBody @Validated UserPhotoVO addTO){
        return userPhotoService.add(addTO);
    }

    @ApiOperation(value="修改",notes = "@author SMS")
    @PostMapping("/userPhoto/update")
    public ResponseDTO<String> update(@RequestBody @Validated UserPhotoUpdateDTO updateDTO){
        return userPhotoService.update(updateDTO);
    }

    @ApiOperation(value="批量删除",notes = "@author SMS")
    @PostMapping("/userPhoto/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return userPhotoService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/userPhoto/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<UserPhotoExcelVO> userPhotoList = userPhotoService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, UserPhotoExcelVO.class, userPhotoList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/userPhoto/export/all")
    public void exportAll(@RequestBody @Validated UserPhotoQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<UserPhotoExcelVO> userPhotoList = userPhotoService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, UserPhotoExcelVO.class, userPhotoList);
        downloadExcel("", workbook, response);
    }

}
