package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketsharesTestVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketsharesTestExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParMarketsharesTestService;
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
 * [ 各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:54
 * @since JDK1.8
 */
@RestController
@Api(tags = {"各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)"})
public class ParMarketsharesTestController extends BaseController {

    @Autowired
    private ParMarketsharesTestService parMarketsharesTestService;

    @ApiOperation(value = "分页查询各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)",notes = "@author SMS")
    @PostMapping("/parMarketsharesTest/page/query")
    public ResponseDTO<PageResultDTO<ParMarketsharesTestVO>> queryByPage(@RequestBody ParMarketsharesTestQueryDTO queryDTO) {
        return parMarketsharesTestService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)",notes = "@author SMS")
    @PostMapping("/parMarketsharesTest/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParMarketsharesTestAddDTO addTO){
        return parMarketsharesTestService.add(addTO);
    }

    @ApiOperation(value="修改各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)",notes = "@author SMS")
    @PostMapping("/parMarketsharesTest/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParMarketsharesTestUpdateDTO updateDTO){
        return parMarketsharesTestService.update(updateDTO);
    }

    @ApiOperation(value="批量删除各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)",notes = "@author SMS")
    @PostMapping("/parMarketsharesTest/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parMarketsharesTestService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parMarketsharesTest/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParMarketsharesTestExcelVO> parMarketsharesTestList = parMarketsharesTestService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParMarketsharesTestExcelVO.class, parMarketsharesTestList);
        downloadExcel("各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parMarketsharesTest/export/all")
    public void exportAll(@RequestBody @Validated ParMarketsharesTestQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParMarketsharesTestExcelVO> parMarketsharesTestList = parMarketsharesTestService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParMarketsharesTestExcelVO.class, parMarketsharesTestList);
        downloadExcel("各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)", workbook, response);
    }

}
