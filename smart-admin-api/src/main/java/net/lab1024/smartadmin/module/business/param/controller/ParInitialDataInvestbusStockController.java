package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusStockQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusStockVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusStockExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataInvestbusStockService;
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
 * [ 初始数据表-投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:09
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-投资业务营业数据表(股票)"})
public class ParInitialDataInvestbusStockController extends BaseController {

    @Autowired
    private ParInitialDataInvestbusStockService parInitialDataInvestbusStockService;

    @ApiOperation(value = "分页查询初始数据表-投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusStock/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataInvestbusStockVO>> queryByPage(@RequestBody ParInitialDataInvestbusStockQueryDTO queryDTO) {
        return parInitialDataInvestbusStockService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusStock/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataInvestbusStockAddDTO addTO){
        return parInitialDataInvestbusStockService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusStock/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataInvestbusStockUpdateDTO updateDTO){
        return parInitialDataInvestbusStockService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-投资业务营业数据表(股票)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusStock/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataInvestbusStockService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusStock/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataInvestbusStockExcelVO> parInitialDataInvestbusStockList = parInitialDataInvestbusStockService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-投资业务营业数据表(股票)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataInvestbusStockExcelVO.class, parInitialDataInvestbusStockList);
        downloadExcel("初始数据表-投资业务营业数据表(股票)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusStock/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataInvestbusStockQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataInvestbusStockExcelVO> parInitialDataInvestbusStockList = parInitialDataInvestbusStockService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-投资业务营业数据表(股票)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataInvestbusStockExcelVO.class, parInitialDataInvestbusStockList);
        downloadExcel("初始数据表-投资业务营业数据表(股票)", workbook, response);
    }

}
