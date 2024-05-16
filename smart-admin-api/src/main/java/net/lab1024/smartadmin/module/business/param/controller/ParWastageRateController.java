package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParWastageRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParWastageRateExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParWastageRateService;
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
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 16:05:49
 * @since JDK1.8
 */
@RestController
@Api(tags = {"人事后勤信息录入"})
public class ParWastageRateController extends BaseController {

    @Autowired
    private ParWastageRateService parWastageRateService;

    @ApiOperation(value = "分页查询人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parWastageRate/page/query")
    public ResponseDTO<PageResultDTO<ParWastageRateVO>> queryByPage(@RequestBody ParWastageRateQueryDTO queryDTO) {
        return parWastageRateService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parWastageRate/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParWastageRateAddDTO addTO){
        return parWastageRateService.add(addTO);
    }

    @ApiOperation(value="修改人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parWastageRate/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParWastageRateUpdateDTO updateDTO){
        return parWastageRateService.update(updateDTO);
    }

    @ApiOperation(value="批量删除人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parWastageRate/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parWastageRateService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parWastageRate/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParWastageRateExcelVO> parWastageRateList = parWastageRateService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParWastageRateExcelVO.class, parWastageRateList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parWastageRate/export/all")
    public void exportAll(@RequestBody @Validated ParWastageRateQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParWastageRateExcelVO> parWastageRateList = parWastageRateService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParWastageRateExcelVO.class, parWastageRateList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

}
