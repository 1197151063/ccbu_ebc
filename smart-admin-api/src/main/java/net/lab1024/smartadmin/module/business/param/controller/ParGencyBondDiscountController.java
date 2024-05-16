package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParGencyBondDiscountQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParGencyBondDiscountVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParGencyBondDiscountExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParGencyBondDiscountService;
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
 * [ 代理债券折扣 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:44:56
 * @since JDK1.8
 */
@RestController
@Api(tags = {"代理债券折扣"})
public class ParGencyBondDiscountController extends BaseController {

    @Autowired
    private ParGencyBondDiscountService parGencyBondDiscountService;

    @ApiOperation(value = "分页查询代理债券折扣",notes = "@author SMS")
    @PostMapping("/parGencyBondDiscount/page/query")
    public ResponseDTO<PageResultDTO<ParGencyBondDiscountVO>> queryByPage(@RequestBody ParGencyBondDiscountQueryDTO queryDTO) {
        return parGencyBondDiscountService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加代理债券折扣",notes = "@author SMS")
    @PostMapping("/parGencyBondDiscount/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParGencyBondDiscountAddDTO addTO){
        return parGencyBondDiscountService.add(addTO);
    }

    @ApiOperation(value="修改代理债券折扣",notes = "@author SMS")
    @PostMapping("/parGencyBondDiscount/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParGencyBondDiscountUpdateDTO updateDTO){
        return parGencyBondDiscountService.update(updateDTO);
    }

    @ApiOperation(value="批量删除代理债券折扣",notes = "@author SMS")
    @PostMapping("/parGencyBondDiscount/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parGencyBondDiscountService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parGencyBondDiscount/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParGencyBondDiscountExcelVO> parGencyBondDiscountList = parGencyBondDiscountService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("代理债券折扣", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParGencyBondDiscountExcelVO.class, parGencyBondDiscountList);
        downloadExcel("代理债券折扣", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parGencyBondDiscount/export/all")
    public void exportAll(@RequestBody @Validated ParGencyBondDiscountQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParGencyBondDiscountExcelVO> parGencyBondDiscountList = parGencyBondDiscountService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("代理债券折扣", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParGencyBondDiscountExcelVO.class, parGencyBondDiscountList);
        downloadExcel("代理债券折扣", workbook, response);
    }

}
