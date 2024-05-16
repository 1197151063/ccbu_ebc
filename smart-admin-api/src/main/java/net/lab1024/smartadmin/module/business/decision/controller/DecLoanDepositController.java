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
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositVO;
import net.lab1024.smartadmin.module.business.decision.service.DecLoanDepositService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 存贷款 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 13:54:20
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentDec.STUDENT_DECISION_DECLOANDEPOSIT})
//@Api(tags = {"存贷款"})
public class DecLoanDepositController extends BaseController {

    @Autowired
    private DecLoanDepositService decLoanDepositService;

    @ApiOperation(value = "分页查询存贷款",notes = "@author SMS")
    @PostMapping("/decLoanDeposit/page/query")
    public ResponseDTO<PageResultDTO<DecLoanDepositVO>> queryByPage(@RequestBody DecLoanDepositQueryDTO queryDTO) {
        return decLoanDepositService.queryByPage(queryDTO);
    }

//    @ApiOperation(value = "查询存贷款",notes = "@author SMS")
//    @GetMapping("/decLoanDeposit/query")
//    public ResponseDTO<Map<String, Object>> query(@RequestParam("beforeCurrentStageId") Long beforeCurrentStageId, @RequestParam("afterCurrentStageId")Long afterCurrentStageId) {
//        return decLoanDepositService.query(beforeCurrentStageId,afterCurrentStageId);
//    }

    @ApiOperation(value = "查询存贷款",notes = "返回值说明{"+"" +
            "\n项目ID：currentProjectId," +
            "\n团队ID：currentTeamId," +
            "\n阶段ID：currentStageId,"+"}")
    @GetMapping("/decLoanDeposit/query")
    public ResponseDTO<DecLoanDepositVO> query(@RequestParam("currentProjectId") Long currentProjectId,@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId) {
        return decLoanDepositService.query(currentProjectId,currentTeamId,currentStageId);
    }

    @ApiOperation(value = "添加存贷款",notes = "@author SMS")
    @PostMapping("/decLoanDeposit/add")
    public ResponseDTO<String> add(@RequestBody DecLoanDepositAddDTO addTO, HttpServletRequest request){
        return decLoanDepositService.add(addTO,request);
    }

//    @ApiOperation(value = "预计算存贷款",notes = "@author SMS")
//    @GetMapping("/decLoanDeposit/preOperation")
//    public ResponseDTO<Map<String,Object>> preOperation(@RequestParam("currentProjectId") Long currentProjectId, @RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
//        return decLoanDepositService.preOperation(currentProjectId,currentTeamId,currentStageId);
//    }

    @ApiOperation(value="修改存贷款",notes = "@author SMS")
    @PostMapping("/decLoanDeposit/update")
    public ResponseDTO<String> update(@RequestBody @Validated DecLoanDepositUpdateDTO updateDTO){
        return decLoanDepositService.update(updateDTO);
    }

    @ApiOperation(value="批量删除存贷款",notes = "@author SMS")
    @PostMapping("/decLoanDeposit/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return decLoanDepositService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/decLoanDeposit/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<DecLoanDepositExcelVO> decLoanDepositList = decLoanDepositService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("存贷款", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecLoanDepositExcelVO.class, decLoanDepositList);
        downloadExcel("存贷款", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/decLoanDeposit/export/all")
    public void exportAll(@RequestBody @Validated DecLoanDepositQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<DecLoanDepositExcelVO> decLoanDepositList = decLoanDepositService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("存贷款", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecLoanDepositExcelVO.class, decLoanDepositList);
        downloadExcel("存贷款", workbook, response);
    }

}
