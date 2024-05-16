package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParComprehensiveScoreVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParComprehensiveScoreExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParComprehensiveScoreService;
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
 * [ 综合评分指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-16 10:39:05
 * @since JDK1.8
 */
@RestController
@Api(tags = {"综合评分指标"})
public class ParComprehensiveScoreController extends BaseController {

    @Autowired
    private ParComprehensiveScoreService parComprehensiveScoreService;

    @ApiOperation(value = "分页查询综合评分指标",notes = "@author SMS")
    @PostMapping("/parComprehensiveScore/page/query")
    public ResponseDTO<PageResultDTO<ParComprehensiveScoreVO>> queryByPage(@RequestBody ParComprehensiveScoreQueryDTO queryDTO) {
        return parComprehensiveScoreService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加人员成本表",notes = "@author SMS")
    @PostMapping("/parComprehensiveScore/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParComprehensiveScoreAddDTO addTO){
        return parComprehensiveScoreService.add(addTO);
    }

    @ApiOperation(value="修改综合评分指标",notes = "@author SMS")
    @PostMapping("/parComprehensiveScore/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParComprehensiveScoreUpdateDTO updateDTO){
        return parComprehensiveScoreService.update(updateDTO);
    }

    @ApiOperation(value="批量删除综合评分指标",notes = "@author SMS")
    @PostMapping("/parComprehensiveScore/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parComprehensiveScoreService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parComprehensiveScore/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParComprehensiveScoreExcelVO> parComprehensiveScoreList = parComprehensiveScoreService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("综合评分指标", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParComprehensiveScoreExcelVO.class, parComprehensiveScoreList);
        downloadExcel("综合评分指标", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parComprehensiveScore/export/all")
    public void exportAll(@RequestBody @Validated ParComprehensiveScoreQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParComprehensiveScoreExcelVO> parComprehensiveScoreList = parComprehensiveScoreService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("综合评分指标", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParComprehensiveScoreExcelVO.class, parComprehensiveScoreList);
        downloadExcel("综合评分指标", workbook, response);
    }

}
