package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerBazaarAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerBazaarUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerBazaarQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerBazaarVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerBazaarExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataMarketCustomerBazaarService;
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
 * [ 初始数据表-市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 16:39:55
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-市场营销数据(客户市场)单位：百万元"})
public class ParInitialDataMarketCustomerBazaarController extends BaseController {

    @Autowired
    private ParInitialDataMarketCustomerBazaarService parInitialDataMarketCustomerBazaarService;

    @ApiOperation(value = "分页查询初始数据表-市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerBazaar/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataMarketCustomerBazaarVO>> queryByPage(@RequestBody ParInitialDataMarketCustomerBazaarQueryDTO queryDTO) {
        return parInitialDataMarketCustomerBazaarService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerBazaar/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataMarketCustomerBazaarAddDTO addTO){
        return parInitialDataMarketCustomerBazaarService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerBazaar/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataMarketCustomerBazaarUpdateDTO updateDTO){
        return parInitialDataMarketCustomerBazaarService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerBazaar/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataMarketCustomerBazaarService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerBazaar/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataMarketCustomerBazaarExcelVO> parInitialDataMarketCustomerBazaarList = parInitialDataMarketCustomerBazaarService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-市场营销数据(客户市场)单位：百万元", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataMarketCustomerBazaarExcelVO.class, parInitialDataMarketCustomerBazaarList);
        downloadExcel("初始数据表-市场营销数据(客户市场)单位：百万元", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerBazaar/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataMarketCustomerBazaarQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataMarketCustomerBazaarExcelVO> parInitialDataMarketCustomerBazaarList = parInitialDataMarketCustomerBazaarService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-市场营销数据(客户市场)单位：百万元", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataMarketCustomerBazaarExcelVO.class, parInitialDataMarketCustomerBazaarList);
        downloadExcel("初始数据表-市场营销数据(客户市场)单位：百万元", workbook, response);
    }

}
