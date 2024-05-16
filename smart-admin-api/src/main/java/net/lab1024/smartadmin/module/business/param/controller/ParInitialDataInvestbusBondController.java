package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusBondVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusBondExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataInvestbusBondService;
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
 * [ 初始数据表-投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:03:28
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-投资业务营业数据表(债券)"})
public class ParInitialDataInvestbusBondController extends BaseController {

    @Autowired
    private ParInitialDataInvestbusBondService parInitialDataInvestbusBondService;

    @ApiOperation(value = "分页查询初始数据表-投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusBond/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataInvestbusBondVO>> queryByPage(@RequestBody ParInitialDataInvestbusBondQueryDTO queryDTO) {
        return parInitialDataInvestbusBondService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusBond/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataInvestbusBondAddDTO addTO){
        return parInitialDataInvestbusBondService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusBond/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataInvestbusBondUpdateDTO updateDTO){
        return parInitialDataInvestbusBondService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-投资业务营业数据表(债券)",notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusBond/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataInvestbusBondService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusBond/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataInvestbusBondExcelVO> parInitialDataInvestbusBondList = parInitialDataInvestbusBondService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-投资业务营业数据表(债券)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataInvestbusBondExcelVO.class, parInitialDataInvestbusBondList);
        downloadExcel("初始数据表-投资业务营业数据表(债券)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataInvestbusBond/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataInvestbusBondQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataInvestbusBondExcelVO> parInitialDataInvestbusBondList = parInitialDataInvestbusBondService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-投资业务营业数据表(债券)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataInvestbusBondExcelVO.class, parInitialDataInvestbusBondList);
        downloadExcel("初始数据表-投资业务营业数据表(债券)", workbook, response);
    }

}
