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
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataBondExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataBondVO;
import net.lab1024.smartadmin.module.business.report.service.RepInvestbusOperatDataBondService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:22:46
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_INVESTMENT_BOND})
//@Api(tags = {"投资业务营业数据表(债券)"})
public class RepInvestbusOperatDataBondController extends BaseController {

    @Autowired
    private RepInvestbusOperatDataBondService repInvestbusOperatDataBondService;

    @ApiOperation(value = "分页查询投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataBond/page/query")
    public ResponseDTO<PageResultDTO<RepInvestbusOperatDataBondVO>> queryByPage(@RequestBody RepInvestbusOperatDataBondQueryDTO queryDTO) {
        return repInvestbusOperatDataBondService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataBond/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepInvestbusOperatDataBondAddDTO addTO){
        return repInvestbusOperatDataBondService.add(addTO);
    }

    @ApiOperation(value="修改投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataBond/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepInvestbusOperatDataBondUpdateDTO updateDTO){
        return repInvestbusOperatDataBondService.update(updateDTO);
    }

    @ApiOperation(value="批量删除投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataBond/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repInvestbusOperatDataBondService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataBond/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepInvestbusOperatDataBondExcelVO> repInvestbusOperatDataBondList = repInvestbusOperatDataBondService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("投资业务营业数据表(债券)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepInvestbusOperatDataBondExcelVO.class, repInvestbusOperatDataBondList);
        downloadExcel("投资业务营业数据表(债券)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repInvestbusOperatDataBond/export/all")
    public void exportAll(@RequestBody @Validated RepInvestbusOperatDataBondQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepInvestbusOperatDataBondExcelVO> repInvestbusOperatDataBondList = repInvestbusOperatDataBondService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("投资业务营业数据表(债券)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepInvestbusOperatDataBondExcelVO.class, repInvestbusOperatDataBondList);
        downloadExcel("投资业务营业数据表(债券)", workbook, response);
    }
    @ApiOperation(value = "查询债券结果表", notes = "@author wz")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currentTeamId",dataType="Long",required = true),
            @ApiImplicitParam(name="currentStageId",dataType="Long",required = true)
    })

    @GetMapping("/repInvestbusOperatDataBond/selectInvestbusOperatDataBond")
    public ResponseDTO<Map<String, Map<String, RepInvestbusOperatDataBondVO>>> selectInvestbusOperatDataBond(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){

        return repInvestbusOperatDataBondService.selectInvestbusOperatDataBond(currentTeamId,currentStageId);
    }

}
