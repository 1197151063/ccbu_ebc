package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataProfitVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataProfitExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataProfitService;
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
 * [ 初始数据表-利润 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:47:09
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-利润"})
public class ParInitialDataProfitController extends BaseController {

    @Autowired
    private ParInitialDataProfitService parInitialDataProfitService;

    @ApiOperation(value = "分页查询初始数据表-利润",notes = "@author SMS")
    @PostMapping("/parInitialDataProfit/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataProfitVO>> queryByPage(@RequestBody ParInitialDataProfitQueryDTO queryDTO) {
        return parInitialDataProfitService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-利润",notes = "@author SMS")
    @PostMapping("/parInitialDataProfit/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataProfitAddDTO addTO){
        return parInitialDataProfitService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-利润",notes = "@author SMS")
    @PostMapping("/parInitialDataProfit/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataProfitUpdateDTO updateDTO){
        return parInitialDataProfitService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-利润",notes = "@author SMS")
    @PostMapping("/parInitialDataProfit/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataProfitService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataProfit/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataProfitExcelVO> parInitialDataProfitList = parInitialDataProfitService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-利润", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataProfitExcelVO.class, parInitialDataProfitList);
        downloadExcel("初始数据表-利润", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataProfit/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataProfitQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataProfitExcelVO> parInitialDataProfitList = parInitialDataProfitService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-利润", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataProfitExcelVO.class, parInitialDataProfitList);
        downloadExcel("初始数据表-利润", workbook, response);
    }

}
