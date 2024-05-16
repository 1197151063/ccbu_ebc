package net.lab1024.smartadmin.module.business.report.controller;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerBazaarVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerBazaarExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepMarketDataCustomerBazaarService;
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
 * [ 市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:26:24
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_MARKET_BAZAAR})
//@Api(tags = {"市场营销数据(客户市场)单位：百万元"})
public class RepMarketDataCustomerBazaarController extends BaseController {

    @Autowired
    private RepMarketDataCustomerBazaarService repMarketDataCustomerBazaarService;

    @ApiOperation(value = "分页查询市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerBazaar/page/query")
    public ResponseDTO<PageResultDTO<RepMarketDataCustomerBazaarVO>> queryByPage(@RequestBody RepMarketDataCustomerBazaarQueryDTO queryDTO) {
        return repMarketDataCustomerBazaarService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerBazaar/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepMarketDataCustomerBazaarAddDTO addTO){
        return repMarketDataCustomerBazaarService.add(addTO);
    }

    @ApiOperation(value="修改市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerBazaar/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepMarketDataCustomerBazaarUpdateDTO updateDTO){
        return repMarketDataCustomerBazaarService.update(updateDTO);
    }

    @ApiOperation(value="批量删除市场营销数据(客户市场)单位：百万元",notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerBazaar/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repMarketDataCustomerBazaarService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerBazaar/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepMarketDataCustomerBazaarExcelVO> repMarketDataCustomerBazaarList = repMarketDataCustomerBazaarService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("市场营销数据(客户市场)单位：百万元", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepMarketDataCustomerBazaarExcelVO.class, repMarketDataCustomerBazaarList);
        downloadExcel("市场营销数据(客户市场)单位：百万元", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repMarketDataCustomerBazaar/export/all")
    public void exportAll(@RequestBody @Validated RepMarketDataCustomerBazaarQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepMarketDataCustomerBazaarExcelVO> repMarketDataCustomerBazaarList = repMarketDataCustomerBazaarService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("市场营销数据(客户市场)单位：百万元", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepMarketDataCustomerBazaarExcelVO.class, repMarketDataCustomerBazaarList);
        downloadExcel("市场营销数据(客户市场)单位：百万元", workbook, response);
    }


    @ApiOperation(value="市场营销数据表（账户）展示到页面",notes = "@author wz")
    @GetMapping("/repMarketDataCustomerAccount/selectMarDataCusBaz")
    public ResponseDTO<List<RepMarketDataCustomerBazaarVO>> selectMarDataCusBaz(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return repMarketDataCustomerBazaarService.selectMarDataCusBaz(currentTeamId,currentStageId);
    }

}
