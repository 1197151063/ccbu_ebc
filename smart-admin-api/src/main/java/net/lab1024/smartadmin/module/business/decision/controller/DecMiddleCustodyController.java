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
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMiddleCustodyUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMiddleCustodyExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMiddleCustodyVO;
import net.lab1024.smartadmin.module.business.decision.service.DecMiddleCustodyService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 中间业务提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 15:28:40
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentDec.STUDENT_DECISION_MIDDLE})
//@Api(tags = {"中间业务提交表"})
public class DecMiddleCustodyController extends BaseController {

    @Autowired
    private DecMiddleCustodyService decMiddleCustodyService;

    @ApiOperation(value = "分页查询中间业务提交表",notes = "@author SMS")
    @PostMapping("/decMiddleCustody/page/query")
    public ResponseDTO<PageResultDTO<DecMiddleCustodyVO>> queryByPage(@RequestBody DecMiddleCustodyQueryDTO queryDTO) {
        return decMiddleCustodyService.queryByPage(queryDTO);
    }
    @ApiOperation(value = "查询中间业务决策表",notes = "@author SMS")
//    @GetMapping("/decMiddleCustody/query")
//    public ResponseDTO<Map<String, Object>> query(@RequestParam("currentStageId") Long currentStageId,@RequestParam("agencyCurrentStageId") Long agencyCurrentStageId) {
//        return decMiddleCustodyService.query(currentStageId,agencyCurrentStageId);
//    }
    @GetMapping("/decMiddleCustody/queryData")
    public ResponseDTO<DecMiddleCustodyVO> queryData(@RequestParam("currentProjectId") Long currentProjectId, @RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId) {
        return decMiddleCustodyService.queryData(currentProjectId,currentTeamId,currentStageId);
    }

    @ApiOperation(value = "添加中间业务提交表",notes = "@author SMS")
    @PostMapping("/decMiddleCustody/add")
    public ResponseDTO<String> add(@RequestBody @Validated DecMiddleCustodyAddDTO addTO, HttpServletRequest request){
        return decMiddleCustodyService.add(addTO,request);
    }

//    @ApiOperation(value = "中间业务预计算",notes = "@author SMS")
//    @PostMapping("/decMiddleCustody/preOperation")
//    public ResponseDTO<Map<String,Object>> preOperation(@RequestBody @Validated DecMiddleCustodyAddDTO addTO){
//        return decMiddleCustodyService.preOperation(addTO);
//    }

    @ApiOperation(value="修改中间业务提交表",notes = "@author SMS")
    @PostMapping("/decMiddleCustody/update")
    public ResponseDTO<String> update(@RequestBody @Validated DecMiddleCustodyUpdateDTO updateDTO){
        return decMiddleCustodyService.update(updateDTO);
    }

    @ApiOperation(value="批量删除中间业务提交表",notes = "@author SMS")
    @PostMapping("/decMiddleCustody/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return decMiddleCustodyService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/decMiddleCustody/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<DecMiddleCustodyExcelVO> decMiddleCustodyList = decMiddleCustodyService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("中间业务提交表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecMiddleCustodyExcelVO.class, decMiddleCustodyList);
        downloadExcel("中间业务提交表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/decMiddleCustody/export/all")
    public void exportAll(@RequestBody @Validated DecMiddleCustodyQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<DecMiddleCustodyExcelVO> decMiddleCustodyList = decMiddleCustodyService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("中间业务提交表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecMiddleCustodyExcelVO.class, decMiddleCustodyList);
        downloadExcel("中间业务提交表", workbook, response);
    }

}
