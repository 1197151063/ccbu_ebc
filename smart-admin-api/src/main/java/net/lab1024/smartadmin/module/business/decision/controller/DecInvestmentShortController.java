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
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentLongAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentShortQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentShortUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentLongVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentShortExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentShortVO;
import net.lab1024.smartadmin.module.business.decision.service.DecInvestmentShortService;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 投资业务(长短期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:36:08
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentDec.STUDENT_DECISION_INVESTMENT})
//@Api(tags = {"投资业务提交表"})
public class DecInvestmentShortController extends BaseController {

    @Autowired
    private DecInvestmentShortService decInvestmentShortService;

    @ApiOperation(value = "分页查询投资业务(短期投资)提交表",notes = "@author SMS")
    @PostMapping("/decInvestmentShort/page/query")
    public ResponseDTO<PageResultDTO<DecInvestmentShortVO>> queryByPage(@RequestBody DecInvestmentShortQueryDTO queryDTO) {
        return decInvestmentShortService.queryByPage(queryDTO);
    }
    @ApiOperation(value = "查询短期投资",notes = "参数说明{"+"" +
            "\n项目ID：currentProjectId," +
            "\n团队ID：currentTeamId," +
            "\n阶段ID：currentStageId,"+"}")
    @GetMapping("/decInvestmentShort/query")
    public ResponseDTO<DecInvestmentShortVO> query(@RequestParam("currentProjectId") Long currentProjectId, @RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId) {
        return decInvestmentShortService.query(currentProjectId,currentTeamId,currentStageId);
    }

    @ApiOperation(value = "查询长期期投资",notes = "参数说明{"+"" +
            "\n项目ID：currentProjectId," +
            "\n团队ID：currentTeamId," )
    @GetMapping("/decInvestmentLong/query")
    public ResponseDTO<List<DecInvestmentLongVO>> queryLongInvestment(@RequestParam("currentProjectId") Long currentProjectId, @RequestParam("currentTeamId") Long currentTeamId) {
        return decInvestmentShortService.queryAllLongInvestment(currentProjectId,currentTeamId);
    }

    @ApiOperation(value = "查询股票债券")
    @GetMapping("/decInvestmentShort/selectPar")
    public ResponseDTO<Map<Long,List<ParProjectRateVO>>> selectPar(@RequestParam("beforeCurrentStageId")Long beforeCurrentStageId, @RequestParam("currentStageId")Long currentStageId){
        return decInvestmentShortService.selectPar(beforeCurrentStageId,currentStageId);
    }

//    /*
//    查询股票债券
//     */
//    @GetMapping("/decInvestmentShort/query")
//    public ResponseDTO<DecInvestmentLongVO> query(@RequestParam("currentProjectId") Long currentProjectId, @RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId, @RequestParam("bondType")String bondType) {
//        return decInvestmentShortService.query(currentProjectId,currentTeamId,currentStageId,bondType);
//    }

    @ApiOperation(value = "添加投资业务(短期投资)提交表",notes = "@author SMS")
    @PostMapping("/decInvestmentShort/add")
    public ResponseDTO<String> add(@RequestBody @Validated DecInvestmentShortVO addDTO,HttpServletRequest request){
        return decInvestmentShortService.add(addDTO,request);
    }
    @ApiOperation(value = "添加投资业务(长期投资)提交表",notes = "@author SMS")
    @PostMapping("/decInvestmentLong/add")
    public ResponseDTO<String> addLongInvestment(@RequestBody @Validated DecInvestmentLongAddDTO addDTO){
        return decInvestmentShortService.addLongInvestment(addDTO);
    }

//    @ApiOperation(value = "投资业务(短期投资)预计算",notes = "@author SMS")
//    @PostMapping("/decInvestmentShort/preOperation")
//    public ResponseDTO<Map<String,Object>> preOperation(@RequestBody @Validated DecInvestmentShortVO addTO){
//        return decInvestmentShortService.preOperation(addTO);
//    }

    @ApiOperation(value="修改投资业务(短期投资)提交表",notes = "@author SMS")
    @PostMapping("/decInvestmentShort/update")
    public ResponseDTO<String> update(@RequestBody @Validated DecInvestmentShortUpdateDTO updateDTO){
        return decInvestmentShortService.update(updateDTO);
    }

    @ApiOperation(value="批量删除投资业务(短期投资)提交表",notes = "@author SMS")
    @PostMapping("/decInvestmentShort/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return decInvestmentShortService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/decInvestmentShort/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<DecInvestmentShortExcelVO> decInvestmentShortList = decInvestmentShortService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("投资业务(短期投资)提交表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecInvestmentShortExcelVO.class, decInvestmentShortList);
        downloadExcel("投资业务(短期投资)提交表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/decInvestmentShort/export/all")
    public void exportAll(@RequestBody @Validated DecInvestmentShortQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<DecInvestmentShortExcelVO> decInvestmentShortList = decInvestmentShortService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("投资业务(短期投资)提交表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecInvestmentShortExcelVO.class, decInvestmentShortList);
        downloadExcel("投资业务(短期投资)提交表", workbook, response);
    }

}
