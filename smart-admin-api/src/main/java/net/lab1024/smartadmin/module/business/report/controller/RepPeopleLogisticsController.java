package net.lab1024.smartadmin.module.business.report.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPeopleLogisticsUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPeopleLogisticsVO;
import net.lab1024.smartadmin.module.business.report.service.RepPeopleLogisticsService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 17:25:20
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_PERSONNE})
public class RepPeopleLogisticsController extends BaseController {

    @Autowired
    private RepPeopleLogisticsService repPeopleLogisticsService;

    @ApiOperation(value = "分页查询人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/repPeopleLogistics/page/query")
    public ResponseDTO<PageResultDTO<RepPeopleLogisticsVO>> queryByPage(@RequestBody RepPeopleLogisticsQueryDTO queryDTO) {
        return repPeopleLogisticsService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/repPeopleLogistics/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepPeopleLogisticsAddDTO addTO){
        return repPeopleLogisticsService.add(addTO);
    }

    @ApiOperation(value="修改人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/repPeopleLogistics/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepPeopleLogisticsUpdateDTO updateDTO){
        return repPeopleLogisticsService.update(updateDTO);
    }

    @ApiOperation(value="批量删除人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/repPeopleLogistics/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repPeopleLogisticsService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repPeopleLogistics/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepPeopleLogisticsExcelVO> repPeopleLogisticsList = repPeopleLogisticsService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepPeopleLogisticsExcelVO.class, repPeopleLogisticsList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repPeopleLogistics/export/all")
    public void exportAll(@RequestBody @Validated RepPeopleLogisticsQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepPeopleLogisticsExcelVO> repPeopleLogisticsList = repPeopleLogisticsService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepPeopleLogisticsExcelVO.class, repPeopleLogisticsList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

    @ApiOperation(value ="人事后勤数据表",notes = "@author wz")
    @GetMapping("/repPeopleLogistics/selectPeopleLogisticsData")
    public ResponseDTO<List<RepPeopleLogisticsVO>> selectPeopleLogisticsData(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return repPeopleLogisticsService.selectPeopleLogisticsData(currentTeamId,currentStageId);
    }

    @ApiOperation(value ="人事后勤数据表",notes = "@author wz")
    @GetMapping("/repPeopleLogistics/selectPeopleLogistics")
    public ResponseDTO<Map<String,RepPeopleLogisticsVO>> selectPeopleLogistics(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return repPeopleLogisticsService.selectPeopleLogistics(currentTeamId,currentStageId);
    }

}
