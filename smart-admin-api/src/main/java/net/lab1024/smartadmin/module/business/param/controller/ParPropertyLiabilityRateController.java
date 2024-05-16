package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParPropertyLiabilityRateService;
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
import java.util.Map;

/**
 * [ 资产负债利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:53
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentPar.STUDENT_PARAM_PROPERTYLIABILITYRATE})
//@Api(tags = {"资产负债利率表"})
public class ParPropertyLiabilityRateController extends BaseController {

    @Autowired
    private ParPropertyLiabilityRateService parPropertyLiabilityRateService;

    @ApiOperation(value = "分页查询资产负债利率表",notes = "@author SMS")
    @PostMapping("/parPropertyLiabilityRate/page/query")
    public ResponseDTO<PageResultDTO<ParPropertyLiabilityRateVO>> queryByPage(@RequestBody ParPropertyLiabilityRateQueryDTO queryDTO) {
        return parPropertyLiabilityRateService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "查询资产负债利率表",notes = "@author SMS")
    @GetMapping("/parPropertyLiabilityRate/selectRate")
    public ResponseDTO<Map<String,ParPropertyLiabilityRateVO>> selectRate(@RequestParam("stageId") Long stageId) {
        return parPropertyLiabilityRateService.selectRate(stageId);
    }

    @ApiOperation(value = "添加资产负债利率表",notes = "@author SMS")
    @PostMapping("/parPropertyLiabilityRate/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParPropertyLiabilityRateAddDTO addTO){
        return parPropertyLiabilityRateService.add(addTO);
    }

    @ApiOperation(value="修改资产负债利率表",notes = "@author SMS")
    @PostMapping("/parPropertyLiabilityRate/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParPropertyLiabilityRateUpdateDTO updateDTO){
        return parPropertyLiabilityRateService.update(updateDTO);
    }

    @ApiOperation(value="批量删除资产负债利率表",notes = "@author SMS")
    @PostMapping("/parPropertyLiabilityRate/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parPropertyLiabilityRateService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parPropertyLiabilityRate/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParPropertyLiabilityRateExcelVO> parPropertyLiabilityRateList = parPropertyLiabilityRateService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("资产负债利率表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParPropertyLiabilityRateExcelVO.class, parPropertyLiabilityRateList);
        downloadExcel("资产负债利率表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parPropertyLiabilityRate/export/all")
    public void exportAll(@RequestBody @Validated ParPropertyLiabilityRateQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParPropertyLiabilityRateExcelVO> parPropertyLiabilityRateList = parPropertyLiabilityRateService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("资产负债利率表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParPropertyLiabilityRateExcelVO.class, parPropertyLiabilityRateList);
        downloadExcel("资产负债利率表", workbook, response);
    }

}
