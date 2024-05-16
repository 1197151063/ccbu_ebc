package net.lab1024.smartadmin.module.business.report.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepAgencyBondUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepAgencyBondExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepAgencyBondVO;
import net.lab1024.smartadmin.module.business.report.service.RepAgencyBondService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 12:46:28
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {"代理债券发行业务数据表"})
public class RepAgencyBondController extends BaseController {

    @Autowired
    private RepAgencyBondService repAgencyBondService;

    @ApiOperation(value = "分页查询代理债券发行业务数据表",notes = "@author SMS")
    @PostMapping("/repAgencyBond/page/query")
    public ResponseDTO<PageResultDTO<RepAgencyBondVO>> queryByPage(@RequestBody RepAgencyBondQueryDTO queryDTO) {
        return repAgencyBondService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加代理债券发行业务数据表",notes = "@author SMS")
    @PostMapping("/repAgencyBond/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepAgencyBondAddDTO addTO){
        return repAgencyBondService.add(addTO);
    }

    @ApiOperation(value="修改代理债券发行业务数据表",notes = "@author SMS")
    @PostMapping("/repAgencyBond/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepAgencyBondUpdateDTO updateDTO){
        return repAgencyBondService.update(updateDTO);
    }

    @ApiOperation(value="批量删除代理债券发行业务数据表",notes = "@author SMS")
    @PostMapping("/repAgencyBond/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repAgencyBondService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repAgencyBond/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepAgencyBondExcelVO> repAgencyBondList = repAgencyBondService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("代理债券发行业务数据表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepAgencyBondExcelVO.class, repAgencyBondList);
        downloadExcel("代理债券发行业务数据表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repAgencyBond/export/all")
    public void exportAll(@RequestBody @Validated RepAgencyBondQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepAgencyBondExcelVO> repAgencyBondList = repAgencyBondService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("代理债券发行业务数据表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepAgencyBondExcelVO.class, repAgencyBondList);
        downloadExcel("代理债券发行业务数据表", workbook, response);
    }

    //查询所有代理债券
    @GetMapping("/repAgencyBond/selectAgencyBondAll")
    public ResponseDTO<List<RepAgencyBondVO>> selectAgencyBondAll(@RequestParam("currentProjectId")Long currentProjectId, @RequestParam("currentStageId")Long currentStageId){
        return repAgencyBondService.selectAgencyBondAll(currentProjectId,currentStageId);
    }

}
