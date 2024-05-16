package net.lab1024.smartadmin.module.business.report.controller;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPersonnelLogisticsDataAutomationVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPersonnelLogisticsDataAutomationExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepPersonnelLogisticsDataAutomationService;
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
 * [ 人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:24:21
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_AUTOMATION})
//@Api(tags = {"人事后勤数据表(自动化投资)"})
public class RepPersonnelLogisticsDataAutomationController extends BaseController {

    @Autowired
    private RepPersonnelLogisticsDataAutomationService repPersonnelLogisticsDataAutomationService;

    @ApiOperation(value = "分页查询人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/repPersonnelLogisticsDataAutomation/page/query")
    public ResponseDTO<PageResultDTO<RepPersonnelLogisticsDataAutomationVO>> queryByPage(@RequestBody RepPersonnelLogisticsDataAutomationQueryDTO queryDTO) {
        return repPersonnelLogisticsDataAutomationService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/repPersonnelLogisticsDataAutomation/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepPersonnelLogisticsDataAutomationAddDTO addTO){
        return repPersonnelLogisticsDataAutomationService.add(addTO);
    }

    @ApiOperation(value="修改人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/repPersonnelLogisticsDataAutomation/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepPersonnelLogisticsDataAutomationUpdateDTO updateDTO){
        return repPersonnelLogisticsDataAutomationService.update(updateDTO);
    }

    @ApiOperation(value="批量删除人事后勤数据表(自动化投资)",notes = "@author SMS")
    @PostMapping("/repPersonnelLogisticsDataAutomation/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repPersonnelLogisticsDataAutomationService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repPersonnelLogisticsDataAutomation/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepPersonnelLogisticsDataAutomationExcelVO> repPersonnelLogisticsDataAutomationList = repPersonnelLogisticsDataAutomationService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤数据表(自动化投资)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepPersonnelLogisticsDataAutomationExcelVO.class, repPersonnelLogisticsDataAutomationList);
        downloadExcel("人事后勤数据表(自动化投资)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repPersonnelLogisticsDataAutomation/export/all")
    public void exportAll(@RequestBody @Validated RepPersonnelLogisticsDataAutomationQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepPersonnelLogisticsDataAutomationExcelVO> repPersonnelLogisticsDataAutomationList = repPersonnelLogisticsDataAutomationService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤数据表(自动化投资)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepPersonnelLogisticsDataAutomationExcelVO.class, repPersonnelLogisticsDataAutomationList);
        downloadExcel("人事后勤数据表(自动化投资)", workbook, response);
    }

    @ApiOperation(value = "自动化投资返回到页面", notes = "@author SMS")
    @GetMapping("/repPersonnelLogisticsDataAutomation/selectPerLogDataAutomation")
    public ResponseDTO<RepPersonnelLogisticsDataAutomationVO> selectPerLogDataAutomation(@RequestParam("currentTeamId") Long currentTeamId,@RequestParam("currentStageId")Long currentStageId){
        return repPersonnelLogisticsDataAutomationService.selectPerLogDataAutomation(currentTeamId,currentStageId);
    }
}
