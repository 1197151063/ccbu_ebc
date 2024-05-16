package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLoanDepositVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLoanDepositExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataLoanDepositService;
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
 * [ 初始内容表-资产负债 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:46:26
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始内容表-资产负债"})
public class ParInitialDataLoanDepositController extends BaseController {

    @Autowired
    private ParInitialDataLoanDepositService parInitialDataLoanDepositService;

    @ApiOperation(value = "分页查询初始内容表-资产负债",notes = "@author SMS")
    @PostMapping("/parInitialDataLoanDeposit/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataLoanDepositVO>> queryByPage(@RequestBody ParInitialDataLoanDepositQueryDTO queryDTO) {
        return parInitialDataLoanDepositService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始内容表-资产负债",notes = "@author SMS")
    @PostMapping("/parInitialDataLoanDeposit/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataLoanDepositAddDTO addTO){
        return parInitialDataLoanDepositService.add(addTO);
    }

    @ApiOperation(value="修改初始内容表-资产负债",notes = "@author SMS")
    @PostMapping("/parInitialDataLoanDeposit/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataLoanDepositUpdateDTO updateDTO){
        return parInitialDataLoanDepositService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始内容表-资产负债",notes = "@author SMS")
    @PostMapping("/parInitialDataLoanDeposit/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataLoanDepositService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataLoanDeposit/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataLoanDepositExcelVO> parInitialDataLoanDepositList = parInitialDataLoanDepositService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始内容表-资产负债", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataLoanDepositExcelVO.class, parInitialDataLoanDepositList);
        downloadExcel("初始内容表-资产负债", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataLoanDeposit/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataLoanDepositQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataLoanDepositExcelVO> parInitialDataLoanDepositList = parInitialDataLoanDepositService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始内容表-资产负债", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataLoanDepositExcelVO.class, parInitialDataLoanDepositList);
        downloadExcel("初始内容表-资产负债", workbook, response);
    }

}
