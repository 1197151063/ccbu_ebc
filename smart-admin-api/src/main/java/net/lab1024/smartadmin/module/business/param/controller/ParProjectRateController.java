package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParProjectRateService;
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
import java.util.Map;

/**
 * [ 项目利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:11
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentPar.STUDENT_PARAM_PROJECTRATE})
//@Api(tags = {"项目利率表"})
public class ParProjectRateController extends BaseController {

    @Autowired
    private ParProjectRateService parProjectRateService;

    @ApiOperation(value = "分页查询项目利率表",notes = "@author SMS")
    @PostMapping("/parProjectRate/page/query")
    public ResponseDTO<PageResultDTO<ParProjectRateVO>> queryByPage(@RequestBody ParProjectRateQueryDTO queryDTO) {
        return parProjectRateService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "查询项目利率表",notes = "@author SMS")
    @GetMapping("/parProjectRate/selectParProjectRate")
    public ResponseDTO <Map<String,ParProjectRateVO>> selectParProjectRate(@RequestParam("stageId") Long stageId) {
        return parProjectRateService.selectParProjectRate(stageId);
    }

    @ApiOperation(value = "添加项目利率表",notes = "@author SMS")
    @PostMapping("/parProjectRate/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParProjectRateAddDTO addTO){
        return parProjectRateService.add(addTO);
    }

    @ApiOperation(value="修改项目利率表",notes = "@author SMS")
    @PostMapping("/parProjectRate/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParProjectRateUpdateDTO updateDTO){
        return parProjectRateService.update(updateDTO);
    }

    @ApiOperation(value="批量删除项目利率表",notes = "@author SMS")
    @PostMapping("/parProjectRate/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parProjectRateService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parProjectRate/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParProjectRateExcelVO> parProjectRateList = parProjectRateService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("项目利率表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParProjectRateExcelVO.class, parProjectRateList);
        downloadExcel("项目利率表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parProjectRate/export/all")
    public void exportAll(@RequestBody @Validated ParProjectRateQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParProjectRateExcelVO> parProjectRateList = parProjectRateService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("项目利率表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParProjectRateExcelVO.class, parProjectRateList);
        downloadExcel("项目利率表", workbook, response);
    }

}
