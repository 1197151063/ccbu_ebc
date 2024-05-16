package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParFormulaService;
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
 * [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 14:42:04
 * @since JDK1.8
 */
@RestController
@Api(tags = {"存贷款计算公式表"})
public class ParFormulaController extends BaseController {

    @Autowired
    private ParFormulaService parFormulaService;

    @ApiOperation(value = "分页查询存贷款计算公式表",notes = "@author SMS")
    @PostMapping("/parFormula/page/query")
    public ResponseDTO<PageResultDTO<ParFormulaVO>> queryByPage(@RequestBody ParFormulaQueryDTO queryDTO) {
        return parFormulaService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加存贷款计算公式表",notes = "@author SMS")
    @PostMapping("/parFormula/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParFormulaAddDTO addTO){
        return parFormulaService.add(addTO);
    }

    @ApiOperation(value="修改存贷款计算公式表",notes = "@author SMS")
    @PostMapping("/parFormula/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParFormulaUpdateDTO updateDTO){
        return parFormulaService.update(updateDTO);
    }

    @ApiOperation(value="批量删除存贷款计算公式表",notes = "@author SMS")
    @PostMapping("/parFormula/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parFormulaService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parFormula/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParFormulaExcelVO> parFormulaList = parFormulaService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("存贷款计算公式表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParFormulaExcelVO.class, parFormulaList);
        downloadExcel("存贷款计算公式表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parFormula/export/all")
    public void exportAll(@RequestBody @Validated ParFormulaQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParFormulaExcelVO> parFormulaList = parFormulaService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("存贷款计算公式表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParFormulaExcelVO.class, parFormulaList);
        downloadExcel("存贷款计算公式表", workbook, response);
    }

}
