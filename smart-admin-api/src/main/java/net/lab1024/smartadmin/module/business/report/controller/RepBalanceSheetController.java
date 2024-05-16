package net.lab1024.smartadmin.module.business.report.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.service.RepBalanceSheetService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 资产负债表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 14:33:59
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_BALANCE})
//@Api(tags = {"资产负债表"})
public class RepBalanceSheetController extends BaseController {

    @Autowired
    private RepBalanceSheetService repBalanceSheetService;

    @ApiOperation(value = "分页查询资产负债表",notes = "@author SMS")
    @PostMapping("/repBalanceSheet/page/query")
    public ResponseDTO<PageResultDTO<RepBalanceSheetVO>> queryByPage(@RequestBody RepBalanceSheetQueryDTO queryDTO) {
        return repBalanceSheetService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加资产负债表",notes = "@author SMS")
    @PostMapping("/repBalanceSheet/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepBalanceSheetAddDTO addTO){
        return repBalanceSheetService.add(addTO);
    }

    @ApiOperation(value="修改资产负债表",notes = "@author SMS")
    @PostMapping("/repBalanceSheet/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepBalanceSheetUpdateDTO updateDTO){
        return repBalanceSheetService.update(updateDTO);
    }

    @ApiOperation(value="批量删除资产负债表",notes = "@author SMS")
    @PostMapping("/repBalanceSheet/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repBalanceSheetService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repBalanceSheet/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepBalanceSheetExcelVO> repBalanceSheetList = repBalanceSheetService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("资产负债表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepBalanceSheetExcelVO.class, repBalanceSheetList);
        downloadExcel("资产负债表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repBalanceSheet/export/all")
    public void exportAll(@RequestBody @Validated RepBalanceSheetQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepBalanceSheetExcelVO> repBalanceSheetList = repBalanceSheetService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("资产负债表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepBalanceSheetExcelVO.class, repBalanceSheetList);
        downloadExcel("资产负债表", workbook, response);
    }


    @ApiOperation(value="查询资产负债结果表",notes = "返回值说明{" +
            "\n" +
            " 当前团队ID ： currentTeamId ,\n" +
            "\n" +
            " 当前阶段ID ： currentStageId ,\n" +
            "\n" +
            " 现金 ：cash ,\n" +
            "\n" +
            " 存放中央银行款项 ：dueCentralBank ,\n" +
            "\n" +
            " 存放同业款项 ：depositInterbank ,\n" +
            "\n" +
            " 拆放同业 ：loanTrade ,\n" +
            "\n" +
            " 短期贷款 ：shortLoan ,\n" +
            "\n" +
            " 应收利息 ：interestReceivable ,\n" +
            "\n" +
            " 其他应收款 ：otherReceivables ,\n" +
            "\n" +
            " 贴现 ：discount ,\n" +
            "\n" +
            " 短期投资 ：shortInvestment ,\n" +
            "\n" +
            " 其他流动资产 ：otherProperty ,\n" +
            "\n" +
            " 流动资产合计 ： propertySum ,\n" +
            "\n" +
            " 中长期贷款 ： mlongLoan ,\n" +
            "\n" +
            " 减：贷款损失准备 ： lossReserves ,\n" +
            "\n" +
            " 长期债权投资 ：longDebtInvestment ,\n" +
            "\n" +
            " 长期股权投资 ：longEquityInvestment ,\n" +
            "\n" +
            " 固定资产 ：fixedAssets ,\n" +
            "\n" +
            " 在建工程 ：constructionProcess ,\n" +
            "\n" +
            " 长期资产合计 ：longTotalAsset ,\n" +
            "\n" +
            " 无形资产 ： intangibleAssets ,\n" +
            "\n" +
            " 其他资产 ：otherAssets ,\n" +
            "\n" +
            " 无形资产及其他资产合计 ：intangibleAotherAssets ,\n" +
            "\n" +
            " 资产总计 ：totalAssets ,\n" +
            "\n" +
            " 短期存款 ：shortDeposit ,\n" +
            "\n" +
            " 短期储蓄存款 ：shortSavingsDeposit ,\n" +
            "\n" +
            " 向中央银行借款  ：borrowingsCentralBank ,\n" +
            "\n" +
            " 同业存放款项 ：interbankDeposit ,\n" +
            "\n" +
            " 同业拆入 ：interBank ,\n" +
            "\n" +
            " 存入短期保证金 ：depositShortMargin ,\n" +
            "\n" +
            " 应付利息 ：paymentInterest ,\n" +
            "\n" +
            " 流动负债合计 ：totalCurrentLiability ,\n" +
            "\n" +
            " 长期存款 ：longDeposit ,\n" +
            "\n" +
            " 长期储蓄存款 ： longSavingsDeposit ,\n" +
            "\n" +
            " 存入长期保证金 ：depositLongMargin ,\n" +
            "\n" +
            " 应付长期债券 ：paymentLongBond ,\n" +
            "\n" +
            " 应付次级债券 ：paymentSubBond ,\n" +
            "\n" +
            " 长期负债合计 ：totalLongLiabilities ,\n" +
            "\n" +
            " 负债合计 ： totalLiabilities ,\n" +
            "\n" +
            " 股本：capital ,\n" +
            "\n" +
            " 资本公积  ：capitalReserve ,\n" +
            "\n" +
            " 盈余公积  ：surplusReserve ,\n" +
            "\n" +
            " 未分配利润  ：undistributedProfit,\n" +
            "\n" +
            " 股东权益合计  ： totalShareholdersEquity\n" +
            "\n" +
            " 负债和股东权益总计  ：totalLiabilitiesEquityIndebted,\n" +
            "}")
    @ApiImplicitParams({
        @ApiImplicitParam(name="currentTeamId",dataType="Long",required = true),
        @ApiImplicitParam(name="currentStageIds",dataType="Long[]",required = true)
    })
    @GetMapping("/repBalanceSheet/selectBalanceResult")
    public ResponseDTO<List<Map<Long,RepBalanceSheetVO>>> selectBalanceResult(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageIds")Long[] currentStageIds){
        return repBalanceSheetService.selectBalanceResult(currentTeamId,currentStageIds);
    }

    //查询所有银行资产负债,未完成
    @GetMapping("/repBalanceSheet/selectBalanceAllResult")
    public ResponseDTO<List<Map<Long, RepBalanceSheetVO>>> selectBalanceAllResult(@RequestParam("currentProjectId")Long currentProjectId,@RequestParam("currentStageIds")Long currentStageId){
        return repBalanceSheetService.selectBalanceAllResult(currentProjectId,currentStageId);
    }

}
