package net.lab1024.smartadmin.module.business.teacher.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementAddDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TSceneManagementUpdateDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TSceneManagementEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TSceneManagementExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TSceneManagementVO;
import net.lab1024.smartadmin.module.business.teacher.service.TSceneManagementService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 场景管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-11 09:05:57
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {"场景管理表"})
public class TSceneManagementController extends BaseController {

    @Autowired
    private TSceneManagementService tSceneManagementService;

    @ApiOperation(value = "分页查询场景管理表",notes = "@author SMS")
    @PostMapping("/tSceneManagement/page/query")
    public ResponseDTO<PageResultDTO<TSceneManagementVO>> queryByPage(@RequestBody TSceneManagementQueryDTO queryDTO) {
        return tSceneManagementService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "查询场景管理表",notes = "@author SMS")
    @GetMapping("/tSceneManagement/queryDataIndex")
    public ResponseDTO<Map<Integer,List<TSceneManagementVO>>> queryDataIndex() {
        return tSceneManagementService.queryDataIndex();
    }

    @ApiOperation(value = "查询消息管理表",notes = "@author SMS")
    @GetMapping("/tSceneManagement/queryData")
    public ResponseDTO<TSceneManagementEntity> queryData(@RequestParam("id")Long id) {
        return tSceneManagementService.queryData(id);
    }

    @ApiOperation(value = "添加场景管理表",notes = "@author SMS")
    @PostMapping("/tSceneManagement/add")
    public ResponseDTO<String> add(@RequestBody @Validated TSceneManagementAddDTO addTO){
        return tSceneManagementService.add(addTO);
    }

    @ApiOperation(value="修改场景管理表",notes = "@author SMS")
    @PostMapping("/tSceneManagement/update")
    public ResponseDTO<String> update(@RequestBody @Validated TSceneManagementUpdateDTO updateDTO){
        return tSceneManagementService.update(updateDTO);
    }

    @ApiOperation(value="批量删除场景管理表",notes = "@author SMS")
    @PostMapping("/tSceneManagement/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return tSceneManagementService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/tSceneManagement/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<TSceneManagementExcelVO> tSceneManagementList = tSceneManagementService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("场景管理表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, TSceneManagementExcelVO.class, tSceneManagementList);
        downloadExcel("场景管理表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/tSceneManagement/export/all")
    public void exportAll(@RequestBody @Validated TSceneManagementQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<TSceneManagementExcelVO> tSceneManagementList = tSceneManagementService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("场景管理表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, TSceneManagementExcelVO.class, tSceneManagementList);
        downloadExcel("场景管理表", workbook, response);
    }

}
