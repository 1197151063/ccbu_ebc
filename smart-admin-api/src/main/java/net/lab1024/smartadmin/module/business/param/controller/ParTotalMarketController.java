package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTotalMarketAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTotalMarketUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParTotalMarketQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTotalMarketVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParTotalMarketExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParTotalMarketService;
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
 * [ 市场总额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 18:27:21
 * @since JDK1.8
 */
@RestController
@Api(tags = {"市场总额"})
public class ParTotalMarketController extends BaseController {

    @Autowired
    private ParTotalMarketService parTotalMarketService;

    @ApiOperation(value = "分页查询市场总额",notes = "@author SMS")
    @PostMapping("/parTotalMarket/page/query")
    public ResponseDTO<PageResultDTO<ParTotalMarketVO>> queryByPage(@RequestBody ParTotalMarketQueryDTO queryDTO) {
        return parTotalMarketService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加市场总额",notes = "@author SMS")
    @PostMapping("/parTotalMarket/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParTotalMarketAddDTO addTO){
        return parTotalMarketService.add(addTO);
    }

    @ApiOperation(value="修改市场总额",notes = "@author SMS")
    @PostMapping("/parTotalMarket/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParTotalMarketUpdateDTO updateDTO){
        return parTotalMarketService.update(updateDTO);
    }

    @ApiOperation(value="批量删除市场总额",notes = "@author SMS")
    @PostMapping("/parTotalMarket/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parTotalMarketService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parTotalMarket/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParTotalMarketExcelVO> parTotalMarketList = parTotalMarketService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("市场总额", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParTotalMarketExcelVO.class, parTotalMarketList);
        downloadExcel("市场总额", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parTotalMarket/export/all")
    public void exportAll(@RequestBody @Validated ParTotalMarketQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParTotalMarketExcelVO> parTotalMarketList = parTotalMarketService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("市场总额", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParTotalMarketExcelVO.class, parTotalMarketList);
        downloadExcel("市场总额", workbook, response);
    }

}
