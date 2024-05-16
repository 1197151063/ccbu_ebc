package net.lab1024.smartadmin.module.business.report.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityExcelVO;
import net.lab1024.smartadmin.module.business.report.service.RepLiquidityTotalityService;
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
 * [ 流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 18:34:36
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_LIQUIDITY_TOTALITY})
//@Api(tags = {"流动性报表-总体"})
public class RepLiquidityTotalityController extends BaseController {

    @Autowired
    private RepLiquidityTotalityService repLiquidityTotalityService;

    @ApiOperation(value = "分页查询流动性报表-总体",notes = "@author SMS")
    @PostMapping("/repLiquidityTotality/page/query")
    public ResponseDTO<PageResultDTO<RepLiquidityTotalityVO>> queryByPage(@RequestBody RepLiquidityTotalityQueryDTO queryDTO) {
        return repLiquidityTotalityService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加流动性报表-总体",notes = "@author SMS")
    @PostMapping("/repLiquidityTotality/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepLiquidityTotalityAddDTO addTO){
        return repLiquidityTotalityService.add(addTO);
    }

    @ApiOperation(value="修改流动性报表-总体",notes = "@author SMS")
    @PostMapping("/repLiquidityTotality/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepLiquidityTotalityUpdateDTO updateDTO){
        return repLiquidityTotalityService.update(updateDTO);
    }

    @ApiOperation(value="批量删除流动性报表-总体",notes = "@author SMS")
    @PostMapping("/repLiquidityTotality/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repLiquidityTotalityService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repLiquidityTotality/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepLiquidityTotalityExcelVO> repLiquidityTotalityList = repLiquidityTotalityService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("流动性报表-总体", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepLiquidityTotalityExcelVO.class, repLiquidityTotalityList);
        downloadExcel("流动性报表-总体", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repLiquidityTotality/export/all")
    public void exportAll(@RequestBody @Validated RepLiquidityTotalityQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepLiquidityTotalityExcelVO> repLiquidityTotalityList = repLiquidityTotalityService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("流动性报表-总体", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepLiquidityTotalityExcelVO.class, repLiquidityTotalityList);
        downloadExcel("流动性报表-总体", workbook, response);
    }
    @ApiOperation(value = "查询流动性报表-总体结果表", notes = "返回值说明{" +" " +
            " 当前项目ID : currentProjectId, \n" +
            "\n" +
            " 当前团队ID ：currentTeamId, \n" +
            "\n" +
            " 当前阶段ID ：currentStageId, \n" +
            "\n" +
            " 存放中央银行款项 ：dueCentralBank, \n" +
            "\n" +
            " 存放同业款项 ：depositInterbank, \n" +
            "\n" +
            " 拆放同业 ：loanTrade, \n" +
            "\n" +
            " 贴现 ：discount, \n" +
            "\n" +
            " 短期投资 ：shortInvestment, \n" +
            "\n" +
            " 长期债权投资 ：ongDebtInvestment, \n" +
            "\n" +
            " 长期股权投资 ：longEquityInvestment, \n" +
            "\n" +
            " 易变现资产总计 ：totalRealizedAssets, \n" +
            "\n" +
            " 同业存放款项 ：interbankDeposit, \n" +
            "\n" +
            " 同业拆入 ：interBank, \n" +
            "\n" +
            " 短期存款：shortDeposit, \n" +
            "\n" +
            " 长期存款 ：longDeposit, \n" +
            "\n" +
            " 短期储蓄存款 ：shortSavingsDeposit, \n" +
            "\n" +
            " 长期储蓄存款 ：longSavingsDeposit, \n" +
            "\n" +
            " 存入短期及长期保证金 ：depositShortLongMargin, \n" +
            "\n" +
            " 向中央银行借款 ：borrowingsCentralBank, \n" +
            "\n" +
            " 流动性负债总计 ：totalCurrentLiabilities, \n" +
            "：overallLiquidityRequirements, \n" +
            "\n" +
            " 清偿力 ：solvency, \n" +
            "\n" +
            " 总体清偿力 ： totalitySolvency, \n" +
            "\n" +
            " 总体流动性盈余/不足 ：overallLiquiditySurplusShortage, "
            +"}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currentTeamId",dataType="Long",required = true),
            @ApiImplicitParam(name="currentStageId",dataType="Long",required = true)
    })
    @GetMapping("/repLiqudityTotality/selectLiquidityTotality")
    public ResponseDTO<RepLiquidityTotalityVO> selectLiquidityTotality(@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return repLiquidityTotalityService.selectLiquidityTotality(currentTeamId,currentStageId);
    }

}
