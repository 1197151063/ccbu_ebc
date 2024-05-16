package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLiquidityTotalityQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityTotalityVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLiquidityTotalityExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataLiquidityTotalityService;
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
 * [ 初始数据表-流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:45:23
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-流动性报表-总体"})
public class ParInitialDataLiquidityTotalityController extends BaseController {

    @Autowired
    private ParInitialDataLiquidityTotalityService parInitialDataLiquidityTotalityService;

    @ApiOperation(value = "分页查询初始数据表-流动性报表-总体",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityTotality/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataLiquidityTotalityVO>> queryByPage(@RequestBody ParInitialDataLiquidityTotalityQueryDTO queryDTO) {
        return parInitialDataLiquidityTotalityService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-流动性报表-总体",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityTotality/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataLiquidityTotalityAddDTO addTO){
        return parInitialDataLiquidityTotalityService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-流动性报表-总体",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityTotality/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataLiquidityTotalityUpdateDTO updateDTO){
        return parInitialDataLiquidityTotalityService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-流动性报表-总体",notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityTotality/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataLiquidityTotalityService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityTotality/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataLiquidityTotalityExcelVO> parInitialDataLiquidityTotalityList = parInitialDataLiquidityTotalityService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-流动性报表-总体", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataLiquidityTotalityExcelVO.class, parInitialDataLiquidityTotalityList);
        downloadExcel("初始数据表-流动性报表-总体", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataLiquidityTotality/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataLiquidityTotalityQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataLiquidityTotalityExcelVO> parInitialDataLiquidityTotalityList = parInitialDataLiquidityTotalityService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-流动性报表-总体", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataLiquidityTotalityExcelVO.class, parInitialDataLiquidityTotalityList);
        downloadExcel("初始数据表-流动性报表-总体", workbook, response);
    }

}
