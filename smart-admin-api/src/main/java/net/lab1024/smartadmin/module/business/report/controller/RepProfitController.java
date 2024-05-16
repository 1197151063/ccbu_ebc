package net.lab1024.smartadmin.module.business.report.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepProfitService;
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
import java.util.Map;

/**
 * [ 利润表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 14:42:17
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_PROFIT})
//@Api(tags = {"利润表"})
public class RepProfitController extends BaseController {

    @Autowired
    private RepProfitService repProfitService;

    @ApiOperation(value = "分页查询利润表",notes = "@author SMS")
    @PostMapping("/repProfit/page/query")
    public ResponseDTO<PageResultDTO<RepProfitVO>> queryByPage(@RequestBody RepProfitQueryDTO queryDTO) {
        return repProfitService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加利润表",notes = "@author SMS")
    @PostMapping("/repProfit/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepProfitAddDTO addTO){
        return repProfitService.add(addTO);
    }

    @ApiOperation(value="修改利润表",notes = "@author SMS")
    @PostMapping("/repProfit/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepProfitUpdateDTO updateDTO){
        return repProfitService.update(updateDTO);
    }

    @ApiOperation(value="批量删除利润表",notes = "@author SMS")
    @PostMapping("/repProfit/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repProfitService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repProfit/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepProfitExcelVO> repProfitList = repProfitService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("利润表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepProfitExcelVO.class, repProfitList);
        downloadExcel("利润表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repProfit/export/all")
    public void exportAll(@RequestBody @Validated RepProfitQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepProfitExcelVO> repProfitList = repProfitService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("利润表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepProfitExcelVO.class, repProfitList);
        downloadExcel("利润表", workbook, response);
    }
    @ApiOperation(value="查询利润结果表",notes = "返回值说明{" +
            "\n" +
            " 当前项目ID ： currentProjectId,\n" +
            "\n" +
            " 当前团队ID ：currentTeamId,\n" +
            "\n" +
            " 当前阶段ID ：currentStageId,\n" +
            "\n" +
            " 利息收入 ：interestIncome,\n" +
            "\n" +
            " 金融机构往来收入 ：financialOrganizationIncome,\n" +
            "\n" +
            " 手续费收入 ：serviceChargeIncome,\n" +
            "\n" +
            " 营业收入合计 ：totalRevenues,\n" +
            "\n" +
            " 利息支出 ：interestExpense,\n" +
            "\n" +
            " 金融机构往来支出 ：financialOrganizationExpense,\n" +
            "\n" +
            " 手续费支出 ：serviceChargeExpense,\n" +
            "\n" +
            " 营业支出合计 ：expenseRevenues,\n" +
            "\n" +
            " 人员开支 ：peopleCosts,\n" +
            "\n" +
            " 附加人员成本 ：additionPeopleCosts,\n" +
            "\n" +
            " 其他人员开支 ：otherPeopleCosts,\n" +
            "\n" +
            " 固定资产折旧 ：fixedAssetsDepreciation,\n" +
            "\n" +
            " 其它营业费用 ：otherBusinessCosts,\n" +
            "\n" +
            " 营业费用合计 ：totalOperatingExpenses,\n" +
            "\n" +
            " 投资收益 ：incomeInvestment,\n" +
            "\n" +
            " 营业利润 ：operatingProfit,\n" +
            "\n" +
            " 减：营业税金及附加 ：subtractBusTaxSurch,\n" +
            "\n" +
            " 加：营业外收入 ：addNonbusIncome,\n" +
            "\n" +
            " 减：营业外支出 ：subtractNonbusExpend,\n" +
            "\n" +
            " 扣除资产减值前利润总额 ：deductAssetImpairmentBeforeTotalProfit,\n" +
            "\n" +
            " 减：资产准备支出 ：subtractAssetReserveExpense,\n" +
            "\n" +
            " 扣除资产减值后利润总额 ：deductAssetImpairmentAfterTotalProfit,\n" +
            "\n" +
            " 减：所得税 ：subtractIncomeTax,\n" +
            "\n" +
            " 净利润 :retainedProfits," +
            "}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currentTeamId",dataType="Long",required = true),
            @ApiImplicitParam(name="currentStageIds",dataType="Long[]",required = true)
    })
    @GetMapping("/repProfit/selectProfit")
    public ResponseDTO<List<Map<Long,RepProfitVO>>> selectProfit(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageIds") Long[] currentStageIds){
        return repProfitService.selectProfit(currentTeamId,currentStageIds);
    }

}
