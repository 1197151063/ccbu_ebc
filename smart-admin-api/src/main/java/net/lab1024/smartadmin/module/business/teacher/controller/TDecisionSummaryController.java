package net.lab1024.smartadmin.module.business.teacher.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryAddDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryUpdateDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TDecisionSummaryVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TDecisionSummaryExcelVO;
import net.lab1024.smartadmin.module.business.teacher.service.TDecisionSummaryService;
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
 * [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-09 16:01:51
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {"决策总结表"})
public class TDecisionSummaryController extends BaseController {

    @Autowired
    private TDecisionSummaryService tDecisionSummaryService;

    @ApiOperation(value = "分页查询决策总结表",notes = "@author SMS")
    @PostMapping("/tDecisionSummary/page/query")
    public ResponseDTO<PageResultDTO<TDecisionSummaryVO>> queryByPage(@RequestBody TDecisionSummaryQueryDTO queryDTO) {
        return tDecisionSummaryService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "查询决策总结表",notes = "@author SMS")
    @GetMapping("/tDecisionSummary/queryDataIndex")
    public ResponseDTO<List<TDecisionSummaryVO>> queryDataIndex(@RequestParam("proId") Long proId) {
        return tDecisionSummaryService.queryDataIndex(proId);
    }

    @ApiOperation(value = "添加决策总结表",notes = "@author SMS")
    @PostMapping("/tDecisionSummary/add")
    public ResponseDTO<String> add(@RequestBody @Validated TDecisionSummaryAddDTO addTO){
        return tDecisionSummaryService.add(addTO);
    }

    @ApiOperation(value="修改决策总结表",notes = "@author SMS")
    @PostMapping("/tDecisionSummary/update")
    public ResponseDTO<String> update(@RequestBody @Validated TDecisionSummaryUpdateDTO updateDTO){
        return tDecisionSummaryService.update(updateDTO);
    }
    @ApiOperation(value="修改决策总结表",notes = "@author SMS")
    @GetMapping("/tDecisionSummary/updateStatus")
    public ResponseDTO<String> updateStatus(@RequestParam("id") Long id){
        return tDecisionSummaryService.updateStatus(id);
    }

    @ApiOperation(value="批量删除决策总结表",notes = "@author SMS")
    @PostMapping("/tDecisionSummary/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return tDecisionSummaryService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/tDecisionSummary/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<TDecisionSummaryExcelVO> tDecisionSummaryList = tDecisionSummaryService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("决策总结表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, TDecisionSummaryExcelVO.class, tDecisionSummaryList);
        downloadExcel("决策总结表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/tDecisionSummary/export/all")
    public void exportAll(@RequestBody @Validated TDecisionSummaryQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<TDecisionSummaryExcelVO> tDecisionSummaryList = tDecisionSummaryService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("决策总结表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, TDecisionSummaryExcelVO.class, tDecisionSummaryList);
        downloadExcel("决策总结表", workbook, response);
    }

}
