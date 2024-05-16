package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataPeopleLogisticsQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataPeopleLogisticsVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataPeopleLogisticsExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataPeopleLogisticsService;
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
 * @date 2021-12-07 16:27:47
 * @since JDK1.8
 */
@RestController
@Api(tags = {"人事后勤信息录入"})
public class ParInitialDataPeopleLogisticsController extends BaseController {

    @Autowired
    private ParInitialDataPeopleLogisticsService parInitialDataPeopleLogisticsService;

    @ApiOperation(value = "分页查询人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parInitialDataPeopleLogistics/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataPeopleLogisticsVO>> queryByPage(@RequestBody ParInitialDataPeopleLogisticsQueryDTO queryDTO) {
        return parInitialDataPeopleLogisticsService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parInitialDataPeopleLogistics/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataPeopleLogisticsAddDTO addTO){
        return parInitialDataPeopleLogisticsService.add(addTO);
    }

    @ApiOperation(value="修改人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parInitialDataPeopleLogistics/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataPeopleLogisticsUpdateDTO updateDTO){
        return parInitialDataPeopleLogisticsService.update(updateDTO);
    }

    @ApiOperation(value="批量删除人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/parInitialDataPeopleLogistics/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataPeopleLogisticsService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataPeopleLogistics/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataPeopleLogisticsExcelVO> parInitialDataPeopleLogisticsList = parInitialDataPeopleLogisticsService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataPeopleLogisticsExcelVO.class, parInitialDataPeopleLogisticsList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataPeopleLogistics/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataPeopleLogisticsQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataPeopleLogisticsExcelVO> parInitialDataPeopleLogisticsList = parInitialDataPeopleLogisticsService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataPeopleLogisticsExcelVO.class, parInitialDataPeopleLogisticsList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

}
