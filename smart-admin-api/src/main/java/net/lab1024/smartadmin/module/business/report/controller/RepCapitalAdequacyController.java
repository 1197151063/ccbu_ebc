package net.lab1024.smartadmin.module.business.report.controller;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepCapitalAdequacyQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepCapitalAdequacyService;
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
 * [ 资本充足率报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:17:26
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_CAPITAL})
//@Api(tags = {"资本充足率报告"})
public class RepCapitalAdequacyController extends BaseController {

    @Autowired
    private RepCapitalAdequacyService repCapitalAdequacyService;

    @ApiOperation(value = "分页查询资本充足率报告",notes = "@author SMS")
    @PostMapping("/repCapitalAdequacy/page/query")
    public ResponseDTO<PageResultDTO<RepCapitalAdequacyVO>> queryByPage(@RequestBody RepCapitalAdequacyQueryDTO queryDTO) {
        return repCapitalAdequacyService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加资本充足率报告",notes = "@author SMS")
    @PostMapping("/repCapitalAdequacy/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepCapitalAdequacyAddDTO addTO){
        return repCapitalAdequacyService.add(addTO);
    }

    @ApiOperation(value="修改资本充足率报告",notes = "@author SMS")
    @PostMapping("/repCapitalAdequacy/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepCapitalAdequacyUpdateDTO updateDTO){
        return repCapitalAdequacyService.update(updateDTO);
    }

    @ApiOperation(value="批量删除资本充足率报告",notes = "@author SMS")
    @PostMapping("/repCapitalAdequacy/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repCapitalAdequacyService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repCapitalAdequacy/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepCapitalAdequacyExcelVO> repCapitalAdequacyList = repCapitalAdequacyService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("资本充足率报告", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepCapitalAdequacyExcelVO.class, repCapitalAdequacyList);
        downloadExcel("资本充足率报告", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repCapitalAdequacy/export/all")
    public void exportAll(@RequestBody @Validated RepCapitalAdequacyQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepCapitalAdequacyExcelVO> repCapitalAdequacyList = repCapitalAdequacyService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("资本充足率报告", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepCapitalAdequacyExcelVO.class, repCapitalAdequacyList);
        downloadExcel("资本充足率报告", workbook, response);
    }


    @ApiOperation(value = "资本充足率报告", notes = "@author wz")
    @GetMapping("/repCapitalAdequacy/selectRepCapitalAdequacy")
    public ResponseDTO<List<RepCapitalAdequacyVO>> selectRepCapitalAdequacy(@RequestParam("currentTeamId") Long currentTeamId,@RequestParam("currentStageId")Long currentStageId){
        return repCapitalAdequacyService.selectRepCapitalAdequacy(currentTeamId,currentStageId);
    }

}
