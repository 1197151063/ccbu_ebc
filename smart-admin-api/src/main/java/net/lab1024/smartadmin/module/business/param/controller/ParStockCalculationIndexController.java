package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParStockCalculationIndexVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParStockCalculationIndexExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParStockCalculationIndexService;
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
 * [ 股价计划指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:09:54
 * @since JDK1.8
 */
@RestController
@Api(tags = {"股价计划指标"})
public class ParStockCalculationIndexController extends BaseController {

    @Autowired
    private ParStockCalculationIndexService parStockCalculationIndexService;

    @ApiOperation(value = "分页查询股价计划指标",notes = "@author SMS")
    @PostMapping("/parStockCalculationIndex/page/query")
    public ResponseDTO<PageResultDTO<ParStockCalculationIndexVO>> queryByPage(@RequestBody ParStockCalculationIndexQueryDTO queryDTO) {
        return parStockCalculationIndexService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加股价计划指标",notes = "@author SMS")
    @PostMapping("/parStockCalculationIndex/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParStockCalculationIndexAddDTO addTO){
        return parStockCalculationIndexService.add(addTO);
    }

    @ApiOperation(value="修改股价计划指标",notes = "@author SMS")
    @PostMapping("/parStockCalculationIndex/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParStockCalculationIndexUpdateDTO updateDTO){
        return parStockCalculationIndexService.update(updateDTO);
    }

    @ApiOperation(value="批量删除股价计划指标",notes = "@author SMS")
    @PostMapping("/parStockCalculationIndex/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parStockCalculationIndexService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parStockCalculationIndex/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParStockCalculationIndexExcelVO> parStockCalculationIndexList = parStockCalculationIndexService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("股价计划指标", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParStockCalculationIndexExcelVO.class, parStockCalculationIndexList);
        downloadExcel("股价计划指标", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parStockCalculationIndex/export/all")
    public void exportAll(@RequestBody @Validated ParStockCalculationIndexQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParStockCalculationIndexExcelVO> parStockCalculationIndexList = parStockCalculationIndexService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("股价计划指标", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParStockCalculationIndexExcelVO.class, parStockCalculationIndexList);
        downloadExcel("股价计划指标", workbook, response);
    }

}
