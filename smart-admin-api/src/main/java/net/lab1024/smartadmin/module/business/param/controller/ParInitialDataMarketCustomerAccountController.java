package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerAccountVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerAccountExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataMarketCustomerAccountService;
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
 * [ 初始数据表-市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 16:38:35
 * @since JDK1.8
 */
@RestController
@Api(tags = {"初始数据表-市场营销数据(客户账户)"})
public class ParInitialDataMarketCustomerAccountController extends BaseController {

    @Autowired
    private ParInitialDataMarketCustomerAccountService parInitialDataMarketCustomerAccountService;

    @ApiOperation(value = "分页查询初始数据表-市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerAccount/page/query")
    public ResponseDTO<PageResultDTO<ParInitialDataMarketCustomerAccountVO>> queryByPage(@RequestBody ParInitialDataMarketCustomerAccountQueryDTO queryDTO) {
        return parInitialDataMarketCustomerAccountService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加初始数据表-市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerAccount/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParInitialDataMarketCustomerAccountAddDTO addTO){
        return parInitialDataMarketCustomerAccountService.add(addTO);
    }

    @ApiOperation(value="修改初始数据表-市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerAccount/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParInitialDataMarketCustomerAccountUpdateDTO updateDTO){
        return parInitialDataMarketCustomerAccountService.update(updateDTO);
    }

    @ApiOperation(value="批量删除初始数据表-市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerAccount/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parInitialDataMarketCustomerAccountService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerAccount/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataMarketCustomerAccountExcelVO> parInitialDataMarketCustomerAccountList = parInitialDataMarketCustomerAccountService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-市场营销数据(客户账户)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataMarketCustomerAccountExcelVO.class, parInitialDataMarketCustomerAccountList);
        downloadExcel("初始数据表-市场营销数据(客户账户)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parInitialDataMarketCustomerAccount/export/all")
    public void exportAll(@RequestBody @Validated ParInitialDataMarketCustomerAccountQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParInitialDataMarketCustomerAccountExcelVO> parInitialDataMarketCustomerAccountList = parInitialDataMarketCustomerAccountService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("初始数据表-市场营销数据(客户账户)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParInitialDataMarketCustomerAccountExcelVO.class, parInitialDataMarketCustomerAccountList);
        downloadExcel("初始数据表-市场营销数据(客户账户)", workbook, response);
    }

}
