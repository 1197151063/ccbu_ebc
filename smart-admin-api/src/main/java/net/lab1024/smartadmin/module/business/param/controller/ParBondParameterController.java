package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParBondParameterVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParBondParameterExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParBondParameterService;
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
 * [ 债券参数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:42:33
 * @since JDK1.8
 */
@RestController
@Api(tags = {"债券参数表"})
public class ParBondParameterController extends BaseController {

    @Autowired
    private ParBondParameterService parBondParameterService;

    @ApiOperation(value = "分页查询债券参数表",notes = "@author SMS")
    @PostMapping("/parBondParameter/page/query")
    public ResponseDTO<PageResultDTO<ParBondParameterVO>> queryByPage(@RequestBody ParBondParameterQueryDTO queryDTO) {
        return parBondParameterService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加债券参数表",notes = "@author SMS")
    @PostMapping("/parBondParameter/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParBondParameterAddDTO addTO){
        return parBondParameterService.add(addTO);
    }

    @ApiOperation(value="修改债券参数表",notes = "@author SMS")
    @PostMapping("/parBondParameter/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParBondParameterUpdateDTO updateDTO){
        return parBondParameterService.update(updateDTO);
    }

    @ApiOperation(value="批量删除债券参数表",notes = "@author SMS")
    @PostMapping("/parBondParameter/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parBondParameterService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parBondParameter/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParBondParameterExcelVO> parBondParameterList = parBondParameterService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("债券参数表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParBondParameterExcelVO.class, parBondParameterList);
        downloadExcel("债券参数表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parBondParameter/export/all")
    public void exportAll(@RequestBody @Validated ParBondParameterQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParBondParameterExcelVO> parBondParameterList = parBondParameterService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("债券参数表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParBondParameterExcelVO.class, parBondParameterList);
        downloadExcel("债券参数表", workbook, response);
    }

}
