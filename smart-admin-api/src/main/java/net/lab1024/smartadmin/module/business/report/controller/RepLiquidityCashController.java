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
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashAddDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashUpdateDTO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashVO;
import net.lab1024.smartadmin.module.business.report.service.RepLiquidityCashService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 18:30:53
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentRep.STUDENT_REPORT_LIQUIDITY_CASH})
//@Api(tags = {"流动性报表-现金"})
public class RepLiquidityCashController extends BaseController {

    @Autowired
    private RepLiquidityCashService repLiquidityCashService;

    @ApiOperation(value = "分页查询流动性报表-现金",notes = "@author SMS")
    @PostMapping("/repLiquidityCash/page/query")
    public ResponseDTO<PageResultDTO<RepLiquidityCashVO>> queryByPage(@RequestBody RepLiquidityCashQueryDTO queryDTO) {
        return repLiquidityCashService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加流动性报表-现金",notes = "@author SMS")
    @PostMapping("/repLiquidityCash/add")
    public ResponseDTO<String> add(@RequestBody @Validated RepLiquidityCashAddDTO addTO){
        return repLiquidityCashService.add(addTO);
    }

    @ApiOperation(value="修改流动性报表-现金",notes = "@author SMS")
    @PostMapping("/repLiquidityCash/update")
    public ResponseDTO<String> update(@RequestBody @Validated RepLiquidityCashUpdateDTO updateDTO){
        return repLiquidityCashService.update(updateDTO);
    }

    @ApiOperation(value="批量删除流动性报表-现金",notes = "@author SMS")
    @PostMapping("/repLiquidityCash/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return repLiquidityCashService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/repLiquidityCash/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<RepLiquidityCashExcelVO> repLiquidityCashList = repLiquidityCashService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("流动性报表-现金", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepLiquidityCashExcelVO.class, repLiquidityCashList);
        downloadExcel("流动性报表-现金", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/repLiquidityCash/export/all")
    public void exportAll(@RequestBody @Validated RepLiquidityCashQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<RepLiquidityCashExcelVO> repLiquidityCashList = repLiquidityCashService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("流动性报表-现金", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepLiquidityCashExcelVO.class, repLiquidityCashList);
        downloadExcel("流动性报表-现金", workbook, response);
    }

    /**
     * notes = "返回值说明{" +" " +
     *             ""
     *             +"}")
     * @param currentTeamId
     * @param currentStageId
     * @return
     */
    @ApiOperation(value = "查询流动性报表-现金结果表", notes ="返回值说明{" +

            "  当前项目ID  ：currentProjectId,\n" +
            "\n" +
            "  当前团队ID  ：currentTeamId,\n" +
            "\n" +
            "  当前阶段ID ：currentStageId,\n" +
            "\n" +
            "  同业存放款项 ：interbankDeposit,\n" +
            "\n" +
            "  同业拆入 ：interBank,\n" +
            "\n" +
            "  短期存款  ：shortDeposit,\n" +
            "\n" +
            "  长期存款 ：longDeposit,\n" +
            "\n" +
            "  短期储蓄存款：shortSavingsDeposit,\n" +
            "\n" +
            "  长期储蓄存款  ：longSavingsDeposit,\n" +
            "\n" +
            "  短期债务总计  : totalShortDebt,\n" +
            "\n" +
            "  现金流动性要求 ：cashLiquidityRequirements,\n" +
            "\n" +
            "  清偿力 ：solvency,\n" +
            "\n" +
            "  现金清偿力 ：cashSolvency," +
            ""+"}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currentTeamId",dataType="Long",required = true),
            @ApiImplicitParam(name="currentStageId",dataType="Long",required = true)
    })
    @GetMapping("/repLiqudityCash/selectLiquidityCash")
    public ResponseDTO<RepLiquidityCashVO> selectLiquidityCash(@RequestParam("currentTeamId") Long currentTeamId,@RequestParam("currentStageId") Long currentStageId){
        return repLiquidityCashService.selectLiquidityCash(currentTeamId,currentStageId);
    }
}
