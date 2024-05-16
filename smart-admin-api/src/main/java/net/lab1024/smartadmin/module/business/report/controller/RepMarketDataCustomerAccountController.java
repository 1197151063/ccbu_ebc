package net.lab1024.smartadmin.module.business.report.controller;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerAccountVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerAccountExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepMarketDataCustomerAccountService;
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
 * [ 市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:25:07
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_MARKET_ACCOUNT})
//@Api(tags = {"市场营销数据(客户账户)"})
public class RepMarketDataCustomerAccountController extends BaseController {

    @Autowired
    private RepMarketDataCustomerAccountService repMarketDataCustomerAccountService;

    @ApiOperation(value = "分页查询市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerAccount/page/query")
    public ResponseDTO<PageResultDTO<RepMarketDataCustomerAccountVO>> queryByPage(@RequestBody RepMarketDataCustomerAccountQueryDTO queryDTO) {
        return repMarketDataCustomerAccountService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerAccount/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepMarketDataCustomerAccountAddDTO addTO){
        return repMarketDataCustomerAccountService.add(addTO);
    }

    @ApiOperation(value="修改市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerAccount/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepMarketDataCustomerAccountUpdateDTO updateDTO){
        return repMarketDataCustomerAccountService.update(updateDTO);
    }

    @ApiOperation(value="批量删除市场营销数据(客户账户)",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerAccount/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repMarketDataCustomerAccountService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerAccount/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepMarketDataCustomerAccountExcelVO> repMarketDataCustomerAccountList = repMarketDataCustomerAccountService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("市场营销数据(客户账户)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepMarketDataCustomerAccountExcelVO.class, repMarketDataCustomerAccountList);
        downloadExcel("市场营销数据(客户账户)", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerAccount/export/all")
    public void exportAll(@RequestBody @Validated RepMarketDataCustomerAccountQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepMarketDataCustomerAccountExcelVO> repMarketDataCustomerAccountList = repMarketDataCustomerAccountService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("市场营销数据(客户账户)", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepMarketDataCustomerAccountExcelVO.class, repMarketDataCustomerAccountList);
        downloadExcel("市场营销数据(客户账户)", workbook, response);
    }


    @ApiOperation(value="市场营销数据表（市场）展示到页面",notes = "@author wz")
    @GetMapping("/repMarketDataCustomerAccount/selectMarDatCusAccount")
    public ResponseDTO<List<RepMarketDataCustomerAccountVO>> selectMarDatCusAccount(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return repMarketDataCustomerAccountService.selectMarDatCusAccount(currentTeamId,currentStageId);
    }

}
