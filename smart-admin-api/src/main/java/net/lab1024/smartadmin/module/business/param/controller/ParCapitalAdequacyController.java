package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParCapitalAdequacyExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParCapitalAdequacyService;
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
 * [ 资本充足率-风险加权系数% ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-05 11:21:36
 * @since JDK1.8
 */
@RestController
@Api(tags = {"资本充足率-风险加权系数%"})
public class ParCapitalAdequacyController extends BaseController {

    @Autowired
    private ParCapitalAdequacyService parCapitalAdequacyService;

    @ApiOperation(value = "分页查询资本充足率-风险加权系数%",notes = "@author SMS")
    @PostMapping("/parCapitalAdequacy/page/query")
    public ResponseDTO<PageResultDTO<ParCapitalAdequacyVO>> queryByPage(@RequestBody ParCapitalAdequacyQueryDTO queryDTO) {
        return parCapitalAdequacyService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加资本充足率-风险加权系数%",notes = "@author SMS")
    @PostMapping("/parCapitalAdequacy/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParCapitalAdequacyAddDTO addTO){
        return parCapitalAdequacyService.add(addTO);
    }

    @ApiOperation(value="修改资本充足率-风险加权系数%",notes = "@author SMS")
    @PostMapping("/parCapitalAdequacy/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParCapitalAdequacyUpdateDTO updateDTO){
        return parCapitalAdequacyService.update(updateDTO);
    }

    @ApiOperation(value="批量删除资本充足率-风险加权系数%",notes = "@author SMS")
    @PostMapping("/parCapitalAdequacy/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parCapitalAdequacyService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parCapitalAdequacy/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParCapitalAdequacyExcelVO> parCapitalAdequacyList = parCapitalAdequacyService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("资本充足率-风险加权系数%", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParCapitalAdequacyExcelVO.class, parCapitalAdequacyList);
        downloadExcel("资本充足率-风险加权系数%", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parCapitalAdequacy/export/all")
    public void exportAll(@RequestBody @Validated ParCapitalAdequacyQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParCapitalAdequacyExcelVO> parCapitalAdequacyList = parCapitalAdequacyService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("资本充足率-风险加权系数%", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParCapitalAdequacyExcelVO.class, parCapitalAdequacyList);
        downloadExcel("资本充足率-风险加权系数%", workbook, response);
    }

}
