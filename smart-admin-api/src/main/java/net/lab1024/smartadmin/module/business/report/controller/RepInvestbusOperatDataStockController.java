package net.lab1024.smartadmin.module.business.report.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataStockExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataStockVO;
import net.lab1024.smartadmin.module.business.report.service.RepInvestbusOperatDataStockService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:23:18
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_INVESTMENT_STOCK})
//@Api(tags = {"投资业务营业数据表(股票)"})
public class RepInvestbusOperatDataStockController extends BaseController {

    @Autowired
    private RepInvestbusOperatDataStockService repInvestbusOperatDataStockService;

    @ApiOperation(value = "分页查询投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataStock/page/query")
    public ResponseDTO<PageResultDTO<RepInvestbusOperatDataStockVO>> queryByPage(@RequestBody RepInvestbusOperatDataStockQueryDTO queryDTO) {
        return repInvestbusOperatDataStockService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataStock/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepInvestbusOperatDataStockAddDTO addTO){
        return repInvestbusOperatDataStockService.add(addTO);
    }

    @ApiOperation(value="修改投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataStock/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepInvestbusOperatDataStockUpdateDTO updateDTO){
        return repInvestbusOperatDataStockService.update(updateDTO);
    }

    @ApiOperation(value="批量删除投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataStock/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repInvestbusOperatDataStockService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataStock/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepInvestbusOperatDataStockExcelVO> repInvestbusOperatDataStockList = repInvestbusOperatDataStockService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("投资业务营业数据表(股票)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepInvestbusOperatDataStockExcelVO.class, repInvestbusOperatDataStockList);
        downloadExcel("投资业务营业数据表(股票)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataStock/export/all")
    public void exportAll(@RequestBody @Validated RepInvestbusOperatDataStockQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepInvestbusOperatDataStockExcelVO> repInvestbusOperatDataStockList = repInvestbusOperatDataStockService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("投资业务营业数据表(股票)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepInvestbusOperatDataStockExcelVO.class, repInvestbusOperatDataStockList);
        downloadExcel("投资业务营业数据表(股票)", workbook, response);
    }

    @ApiOperation(value = "查询股票结果表",notes = "返回值说明{" +


      "}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currentTeamId",dataType="Long",required = true),
            @ApiImplicitParam(name="currentStageId",dataType="Long",required = true)
    })
    @GetMapping("/repInvestbusOperatDataStock/selectInvestbusOperatDataStock")
    public ResponseDTO<Map<String,Map<String, RepInvestbusOperatDataStockVO>>> selectInvestbusOperatDataStock(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return repInvestbusOperatDataStockService.selectInvestbusOperatDataStock(currentTeamId,currentStageId);
    }

}
