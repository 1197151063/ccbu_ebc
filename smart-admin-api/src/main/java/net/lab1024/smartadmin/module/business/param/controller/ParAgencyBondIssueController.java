package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParAgencyBondIssueQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParAgencyBondIssueExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParAgencyBondIssueService;
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
 * [ 代理债券发行业务信息表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:37:59
 * @since JDK1.8
 */
@RestController
@Api(tags = {"代理债券发行业务信息表"})
public class ParAgencyBondIssueController extends BaseController {

    @Autowired
    private ParAgencyBondIssueService parAgencyBondIssueService;

    @ApiOperation(value = "分页查询代理债券发行业务信息表",notes = "@author SMS")
    @PostMapping("/parAgencyBondIssue/page/query")
    public ResponseDTO<PageResultDTO<ParAgencyBondIssueVO>> queryByPage(@RequestBody ParAgencyBondIssueQueryDTO queryDTO) {
        return parAgencyBondIssueService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加代理债券发行业务信息表",notes = "@author SMS")
    @PostMapping("/parAgencyBondIssue/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParAgencyBondIssueAddDTO addTO){
        return parAgencyBondIssueService.add(addTO);
    }

    @ApiOperation(value="修改代理债券发行业务信息表",notes = "@author SMS")
    @PostMapping("/parAgencyBondIssue/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParAgencyBondIssueUpdateDTO updateDTO){
        return parAgencyBondIssueService.update(updateDTO);
    }

    @ApiOperation(value="批量删除代理债券发行业务信息表",notes = "@author SMS")
    @PostMapping("/parAgencyBondIssue/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parAgencyBondIssueService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parAgencyBondIssue/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParAgencyBondIssueExcelVO> parAgencyBondIssueList = parAgencyBondIssueService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("代理债券发行业务信息表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParAgencyBondIssueExcelVO.class, parAgencyBondIssueList);
        downloadExcel("代理债券发行业务信息表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parAgencyBondIssue/export/all")
    public void exportAll(@RequestBody @Validated ParAgencyBondIssueQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParAgencyBondIssueExcelVO> parAgencyBondIssueList = parAgencyBondIssueService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("代理债券发行业务信息表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParAgencyBondIssueExcelVO.class, parAgencyBondIssueList);
        downloadExcel("代理债券发行业务信息表", workbook, response);
    }

}
