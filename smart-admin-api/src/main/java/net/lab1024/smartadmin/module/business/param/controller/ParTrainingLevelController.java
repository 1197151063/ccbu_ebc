package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTrainingLevelQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTrainingLevelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTrainingLevelExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParTrainingLevelService;
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
 * [ 培训水平 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:52:40
 * @since JDK1.8
 */
@RestController
@Api(tags = {"培训水平"})
public class ParTrainingLevelController extends BaseController {

    @Autowired
    private ParTrainingLevelService parTrainingLevelService;

    @ApiOperation(value = "分页查询培训水平",notes = "@author SMS")
    @PostMapping("/parTrainingLevel/page/query")
    public ResponseDTO<PageResultDTO<ParTrainingLevelVO>> queryByPage(@RequestBody ParTrainingLevelQueryDTO queryDTO) {
        return parTrainingLevelService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加培训水平",notes = "@author SMS")
    @PostMapping("/parTrainingLevel/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParTrainingLevelAddDTO addTO){
        return parTrainingLevelService.add(addTO);
    }

    @ApiOperation(value="修改培训水平",notes = "@author SMS")
    @PostMapping("/parTrainingLevel/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParTrainingLevelUpdateDTO updateDTO){
        return parTrainingLevelService.update(updateDTO);
    }

    @ApiOperation(value="批量删除培训水平",notes = "@author SMS")
    @PostMapping("/parTrainingLevel/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parTrainingLevelService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parTrainingLevel/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParTrainingLevelExcelVO> parTrainingLevelList = parTrainingLevelService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("培训水平", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParTrainingLevelExcelVO.class, parTrainingLevelList);
        downloadExcel("培训水平", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parTrainingLevel/export/all")
    public void exportAll(@RequestBody @Validated ParTrainingLevelQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParTrainingLevelExcelVO> parTrainingLevelList = parTrainingLevelService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("培训水平", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParTrainingLevelExcelVO.class, parTrainingLevelList);
        downloadExcel("培训水平", workbook, response);
    }

}
