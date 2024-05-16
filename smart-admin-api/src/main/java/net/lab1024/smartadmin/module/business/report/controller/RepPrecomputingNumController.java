package net.lab1024.smartadmin.module.business.report.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPrecomputingNumQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPrecomputingNumVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPrecomputingNumExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepPrecomputingNumService;
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
 * [ 预计算次数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-27 17:08:27
 * @since JDK1.8
 */
@RestController
@Api(tags = {"预计算次数表"})
public class RepPrecomputingNumController extends BaseController {

    @Autowired
    private RepPrecomputingNumService repPrecomputingNumService;

    @ApiOperation(value = "分页查询预计算次数表",notes = "@author SMS")
    @PostMapping("/repPrecomputingNum/page/query")
    public ResponseDTO<PageResultDTO<RepPrecomputingNumVO>> queryByPage(@RequestBody RepPrecomputingNumQueryDTO queryDTO) {
        return repPrecomputingNumService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加预计算次数表",notes = "@author SMS")
    @PostMapping("/repPrecomputingNum/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepPrecomputingNumAddDTO addTO){
        return repPrecomputingNumService.add(addTO);
    }

    @ApiOperation(value="修改预计算次数表",notes = "@author SMS")
    @PostMapping("/repPrecomputingNum/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepPrecomputingNumUpdateDTO updateDTO){
        return repPrecomputingNumService.update(updateDTO);
    }

    @ApiOperation(value="批量删除预计算次数表",notes = "@author SMS")
    @PostMapping("/repPrecomputingNum/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repPrecomputingNumService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repPrecomputingNum/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepPrecomputingNumExcelVO> repPrecomputingNumList = repPrecomputingNumService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("预计算次数表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepPrecomputingNumExcelVO.class, repPrecomputingNumList);
        downloadExcel("预计算次数表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repPrecomputingNum/export/all")
    public void exportAll(@RequestBody @Validated RepPrecomputingNumQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepPrecomputingNumExcelVO> repPrecomputingNumList = repPrecomputingNumService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("预计算次数表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepPrecomputingNumExcelVO.class, repPrecomputingNumList);
        downloadExcel("预计算次数表", workbook, response);
    }

}
