package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParIndexNumberQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParIndexNumberVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParIndexNumberExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParIndexNumberService;
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
 * [ 自动化投资指数 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 18:42:39
 * @since JDK1.8
 */
@RestController
@Api(tags = {"自动化投资指数"})
public class ParIndexNumberController extends BaseController {

    @Autowired
    private ParIndexNumberService parIndexNumberService;

    @ApiOperation(value = "分页查询自动化投资指数",notes = "@author SMS")
    @PostMapping("/parIndexNumber/page/query")
    public ResponseDTO<PageResultDTO<ParIndexNumberVO>> queryByPage(@RequestBody ParIndexNumberQueryDTO queryDTO) {
        return parIndexNumberService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加自动化投资指数",notes = "@author SMS")
    @PostMapping("/parIndexNumber/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParIndexNumberAddDTO addTO){
        return parIndexNumberService.add(addTO);
    }

    @ApiOperation(value="修改自动化投资指数",notes = "@author SMS")
    @PostMapping("/parIndexNumber/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParIndexNumberUpdateDTO updateDTO){
        return parIndexNumberService.update(updateDTO);
    }

    @ApiOperation(value="批量删除自动化投资指数",notes = "@author SMS")
    @PostMapping("/parIndexNumber/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parIndexNumberService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parIndexNumber/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParIndexNumberExcelVO> parIndexNumberList = parIndexNumberService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("自动化投资指数", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParIndexNumberExcelVO.class, parIndexNumberList);
        downloadExcel("自动化投资指数", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parIndexNumber/export/all")
    public void exportAll(@RequestBody @Validated ParIndexNumberQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParIndexNumberExcelVO> parIndexNumberList = parIndexNumberService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("自动化投资指数", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParIndexNumberExcelVO.class, parIndexNumberList);
        downloadExcel("自动化投资指数", workbook, response);
    }

}
