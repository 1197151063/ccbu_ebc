package net.lab1024.smartadmin.module.business.decision.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementVO;
import net.lab1024.smartadmin.module.business.decision.service.DecFinancialManagementService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 财务管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:32:30
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentDec.STUDENT_DECISION_FINANACE})
//@Api(tags = {"财务管理表"})
public class DecFinancialManagementController extends BaseController {

    @Autowired
    private DecFinancialManagementService decFinancialManagementService;

    @ApiOperation(value = "分页查询财务管理表",notes = "@author SMS")
    @PostMapping("/decFinancialManagement/page/query")
    public ResponseDTO<PageResultDTO<DecFinancialManagementVO>> queryByPage(@RequestBody DecFinancialManagementQueryDTO queryDTO) {
        return decFinancialManagementService.queryByPage(queryDTO);
    }


    /**
     * 查询决策表
     * @param currentProjectId
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    @ApiOperation(value = "查询财务管理决策表",notes = "@author SMS")
    @GetMapping("/decFinancialManagement/queryFinancial")
    public ResponseDTO<DecFinancialManagementVO> queryFinancial(@RequestParam("currentProjectId") Long currentProjectId, @RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId) {
        return decFinancialManagementService.queryFinancial(currentProjectId,currentTeamId,currentStageId);
    }

//    @NoNeedLogin
//    @ApiOperation(value = "财务管理预计算",notes = "@author SMS")
//    @PostMapping("/decFinancialManagement/preOperation")
//    public ResponseDTO<Map<String,Object>> preOperation(@RequestBody @Validated DecFinancialManagementAddDTO addTO){
//        return decFinancialManagementService.preOperation(addTO);
//    }


    @ApiOperation(value = "添加财务管理表",notes = "@author SMS")
    @PostMapping("/decFinancialManagement/add")
    public ResponseDTO<String> add(@RequestBody @Validated DecFinancialManagementAddDTO addTO, HttpServletRequest request){
        return decFinancialManagementService.add(addTO,request);
    }

    @ApiOperation(value="修改财务管理表",notes = "@author SMS")
    @PostMapping("/decFinancialManagement/update")
    public ResponseDTO<String> update(@RequestBody @Validated DecFinancialManagementUpdateDTO updateDTO){
        return decFinancialManagementService.update(updateDTO);
    }

    @ApiOperation(value="批量删除财务管理表",notes = "@author SMS")
    @PostMapping("/decFinancialManagement/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return decFinancialManagementService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/decFinancialManagement/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<DecFinancialManagementExcelVO> decFinancialManagementList = decFinancialManagementService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("财务管理表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecFinancialManagementExcelVO.class, decFinancialManagementList);
        downloadExcel("财务管理表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/decFinancialManagement/export/all")
    public void exportAll(@RequestBody @Validated DecFinancialManagementQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<DecFinancialManagementExcelVO> decFinancialManagementList = decFinancialManagementService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("财务管理表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecFinancialManagementExcelVO.class, decFinancialManagementList);
        downloadExcel("财务管理表", workbook, response);
    }

}
