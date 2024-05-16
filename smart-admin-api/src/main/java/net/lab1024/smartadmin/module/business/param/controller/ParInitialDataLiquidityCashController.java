package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityCashVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityCashExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataLiquidityCashService;
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
 * [ 初始数据表-流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:44:40
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-流动性报表-现金"})
public class ParInitialDataLiquidityCashController extends BaseController {

    @Autowired
    private ParInitialDataLiquidityCashService parInitialDataLiquidityCashService;

    @ApiOperation(value = "分页查询初始数据表-流动性报表-现金",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityCash/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataLiquidityCashVO>> queryByPage(@RequestBody ParInitialDataLiquidityCashQueryDTO queryDTO) {
        return parInitialDataLiquidityCashService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-流动性报表-现金",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityCash/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataLiquidityCashAddDTO addTO){
        return parInitialDataLiquidityCashService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-流动性报表-现金",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityCash/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataLiquidityCashUpdateDTO updateDTO){
        return parInitialDataLiquidityCashService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-流动性报表-现金",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityCash/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataLiquidityCashService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityCash/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataLiquidityCashExcelVO> parInitialDataLiquidityCashList = parInitialDataLiquidityCashService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-流动性报表-现金", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataLiquidityCashExcelVO.class, parInitialDataLiquidityCashList);
        downloadExcel("初始数据表-流动性报表-现金", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityCash/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataLiquidityCashQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataLiquidityCashExcelVO> parInitialDataLiquidityCashList = parInitialDataLiquidityCashService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-流动性报表-现金", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataLiquidityCashExcelVO.class, parInitialDataLiquidityCashList);
        downloadExcel("初始数据表-流动性报表-现金", workbook, response);
    }

}
