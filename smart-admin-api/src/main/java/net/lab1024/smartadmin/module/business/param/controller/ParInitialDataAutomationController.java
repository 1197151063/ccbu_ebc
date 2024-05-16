package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataAutomationQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataAutomationVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataAutomationExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataAutomationService;
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
 * [ 初始数据表-人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-06 14:22:45
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-人事后勤数据表(自动化投资)"})
public class ParInitialDataAutomationController extends BaseController {

    @Autowired
    private ParInitialDataAutomationService parInitialDataAutomationService;

    @ApiOperation(value = "分页查询初始数据表-人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/parInitialDataAutomation/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataAutomationVO>> queryByPage(@RequestBody ParInitialDataAutomationQueryDTO queryDTO) {
        return parInitialDataAutomationService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/parInitialDataAutomation/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataAutomationAddDTO addTO){
        return parInitialDataAutomationService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/parInitialDataAutomation/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataAutomationUpdateDTO updateDTO){
        return parInitialDataAutomationService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/parInitialDataAutomation/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataAutomationService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataAutomation/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataAutomationExcelVO> parInitialDataAutomationList = parInitialDataAutomationService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-人事后勤数据表(自动化投资)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataAutomationExcelVO.class, parInitialDataAutomationList);
        downloadExcel("初始数据表-人事后勤数据表(自动化投资)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataAutomation/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataAutomationQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataAutomationExcelVO> parInitialDataAutomationList = parInitialDataAutomationService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-人事后勤数据表(自动化投资)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataAutomationExcelVO.class, parInitialDataAutomationList);
        downloadExcel("初始数据表-人事后勤数据表(自动化投资)", workbook, response);
    }

}
