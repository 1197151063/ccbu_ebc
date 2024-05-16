package net.lab1024.smartadmin.module.business.report.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepStockPriceQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepStockPriceVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepStockPriceExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepStockPriceService;
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
 * [ 股价表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:06:10
 * @since JDK1.8
 */
@RestController
@Api(tags = {"股价表"})
public class RepStockPriceController extends BaseController {

    @Autowired
    private RepStockPriceService repStockPriceService;

    @ApiOperation(value = "分页查询股价表",notes = "@author SMS")
    @PostMapping("/repStockPrice/page/query")
    public ResponseDTO<PageResultDTO<RepStockPriceVO>> queryByPage(@RequestBody RepStockPriceQueryDTO queryDTO) {
        return repStockPriceService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加股价表",notes = "@author SMS")
    @PostMapping("/repStockPrice/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepStockPriceAddDTO addTO){
        return repStockPriceService.add(addTO);
    }

    @ApiOperation(value="修改股价表",notes = "@author SMS")
    @PostMapping("/repStockPrice/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepStockPriceUpdateDTO updateDTO){
        return repStockPriceService.update(updateDTO);
    }

    @ApiOperation(value="批量删除股价表",notes = "@author SMS")
    @PostMapping("/repStockPrice/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repStockPriceService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repStockPrice/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepStockPriceExcelVO> repStockPriceList = repStockPriceService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("股价表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepStockPriceExcelVO.class, repStockPriceList);
        downloadExcel("股价表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repStockPrice/export/all")
    public void exportAll(@RequestBody @Validated RepStockPriceQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepStockPriceExcelVO> repStockPriceList = repStockPriceService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("股价表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepStockPriceExcelVO.class, repStockPriceList);
        downloadExcel("股价表", workbook, response);
    }

}
