package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParMarketShareService;
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
 * [ 市场份额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 20:06:33
 * @since JDK1.8
 */
@RestController
@Api(tags = {"市场份额"})
public class ParMarketShareController extends BaseController {

    @Autowired
    private ParMarketShareService parMarketShareService;

    @ApiOperation(value = "分页查询市场份额",notes = "@author SMS")
    @PostMapping("/parMarketShare/page/query")
    public ResponseDTO<PageResultDTO<ParMarketShareVO>> queryByPage(@RequestBody ParMarketShareQueryDTO queryDTO) {
        return parMarketShareService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加市场份额",notes = "@author SMS")
    @PostMapping("/parMarketShare/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParMarketShareAddDTO addTO){
        return parMarketShareService.add(addTO);
    }

    @ApiOperation(value="修改市场份额",notes = "@author SMS")
    @PostMapping("/parMarketShare/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParMarketShareUpdateDTO updateDTO){
        return parMarketShareService.update(updateDTO);
    }

    @ApiOperation(value="批量删除市场份额",notes = "@author SMS")
    @PostMapping("/parMarketShare/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parMarketShareService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parMarketShare/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParMarketShareExcelVO> parMarketShareList = parMarketShareService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("市场份额", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParMarketShareExcelVO.class, parMarketShareList);
        downloadExcel("市场份额", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parMarketShare/export/all")
    public void exportAll(@RequestBody @Validated ParMarketShareQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParMarketShareExcelVO> parMarketShareList = parMarketShareService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("市场份额", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParMarketShareExcelVO.class, parMarketShareList);
        downloadExcel("市场份额", workbook, response);
    }

}
