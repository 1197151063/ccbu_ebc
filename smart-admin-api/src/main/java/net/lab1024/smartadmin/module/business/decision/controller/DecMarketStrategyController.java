package net.lab1024.smartadmin.module.business.decision.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecMarketStrategyUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyVO;
import net.lab1024.smartadmin.module.business.decision.service.DecMarketStrategyService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 市场营销策略 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:31:22
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentDec.STUDENT_DECISION_MARKET})
//@Api(tags = {"市场营销策略"})
public class DecMarketStrategyController extends BaseController {

    @Autowired
    private DecMarketStrategyService decMarketStrategyService;

    @ApiOperation(value = "分页查询市场营销策略",notes = "@author SMS")
    @PostMapping("/decMarketStrategy/page/query")
    public ResponseDTO<PageResultDTO<DecMarketStrategyVO>> queryByPage(@RequestBody DecMarketStrategyQueryDTO queryDTO) {
        return decMarketStrategyService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加市场营销策略",notes = "返回值说明{"+"" +
            "\n项目ID：currentProjectId," +
            "\n" +
            "\n团队ID：currentTeamId," +
            "\n" +
            "\n阶段ID：currentStageId," +
            "\n" +
            "\n贷款业务-市场拓展<35岁：loansBusineDevelopLess,\n" +
            "\n" +
            "\n贷款业务-客户维护<35岁：loansCustomerMainLess,\n" +
            "\n" +
            " 贷款业务-市场拓展<35岁：loansBusineDevelopLess，\n" +
            "\n" +
            " 贷款业务-客户维护<35岁： loansCustomerMainLess，\n" +
            "\n" +
            " 存款业务-市场拓展<35岁：depositBusineDevelopLess，\n" +
            "\n" +
            " 存款业务-客户维护<35岁：depositCustomerMainLess，\n" +
            "\n" +
            " 中间业务-市场拓展<35岁：middleBusineDevelopLess，\n" +
            "\n" +
            " 中间业务-客户维护<35岁：middleCustomerMainLess，\n" +
            "\n" +
            " 贷款业务-市场拓展35~55岁：loansBusineDevelopBetween，\n" +
            "\n" +
            " 贷款业务-客户维护35~55岁：loansCustomerMainBetween，\n" +
            "\n" +
            " 存款业务-市场拓展35~55岁：depositBusineDevelopBetween，\n" +
            "\n" +
            " 存款业务-客户维护35~55岁：depositCustomerMainBetween，\n" +
            "\n" +
            " 中间业务-市场拓展35~55岁：middleBusineDevelopBetween，\n" +
            "\n" +
            " 中间业务-客户维护35~55岁：middleCustomerMainBetween，\n" +
            "\n" +
            " 贷款业务-市场拓展>55岁：loansBusineDevelopGreater，\n" +
            "\n" +
            " 贷款业务-客户维护>55岁：loansCustomerMainGreater，\n" +
            "\n" +
            " 存款业务-市场拓展>55岁：depositBusineDevelopGreater，\n" +
            "\n" +
            " 存款业务-客户维护>55岁：depositCustomerMainGreater，\n" +
            "\n" +
            " 中间业务-市场拓展>55岁：middleBusineDevelopGreater，\n" +
            "\n" +
            " 中间业务-客户维护>55岁：middleCustomerMainGreater，\n" +
            "\n" +
            " 贷款业务-市场拓展-小型企业：loansBusineDevelopSmall，\n" +
            "\n" +
            " 贷款业务-客户维护-小型企业：loansCustomerMainSmall，\n" +
            "\n" +
            " 存款业务-市场拓展-小型企业：depositBusineDevelopSmall，\n" +
            "\n" +
            " 存款业务-客户维护-小型企业：depositCustomerMainSmall，\n" +
            "\n" +
            " 中间业务-市场拓展-小型企业：middleBusineDevelopSmall，\n" +
            "\n" +
            " 中间业务-客户维护-小型企业：middleCustomerMainSmall，\n" +
            "\n" +
            " 贷款业务-市场拓展-中型企业：loansBusineDevelopMedium，\n" +
            "\n" +
            " 贷款业务-客户维护-中型企业：loansCustomerMainMedium，\n" +
            "\n" +
            " 存款业务-市场拓展-中型企业：depositBusineDevelopMedium，\n" +
            "\n" +
            " 存款业务-客户维护-中型企业：depositCustomerMainMedium，\n" +
            "\n" +
            " 中间业务-市场拓展-中型企业：middleBusineDevelopMedium，\n" +
            "\n" +
            " 中间业务-客户维护-中型企业：middleCustomerMainMedium，\n" +
            "\n" +
            " 贷款业务-市场拓展-大型企业：loansBusineDevelopMajor，\n" +
            "\n" +
            " 贷款业务-客户维护-大型企业：loansCustomerMainMajor，\n" +
            "\n" +
            " 存款业务-市场拓展-大型企业： depositBusineDevelopMajor，\n" +
            "\n" +
            " 存款业务-客户维护-大型企业：depositCustomerMainMajor，\n" +
            "\n" +
            " 中间业务-市场拓展-大型企业：middleBusineDevelopMajor，\n" +
            "\n" +
            " 中间业务-客户维护-大型企业：middleCustomerMainMajor，\n" +
            "\n" +
            " 贷款业务-市场拓展-公共机构：loansBusineDevelopPublic，\n" +
            "\n" +
            " 贷款业务-客户维护-公共机构：loansCustomerMainPublic，\n" +
            "\n" +
            " 存款业务-市场拓展-公共机构：depositBusineDevelopPublic，\n" +
            "\n" +
            " 存款业务-客户维护-公共机构：depositCustomerMainPublic，\n" +
            "\n" +
            " 中间业务-市场拓展-公共机构：middleBusineDevelopPublic，\n" +
            "\n" +
            " 中间业务-客户维护-公共机构：middleCustomerMainPublic，\n" +
            "\n" +
            " 客户营销-可用费用总量：customerMarketingTotalCostAvailable，\n" +
            "\n" +
            " 客户营销-<35岁：customerMarketingLessAge，\n" +
            "\n" +
            " 客户营销-35~55岁：customerMarketingBetweenAge，\n" +
            "\n" +
            " 客户营销->55岁：customerMarketingGreaterAge，\n" +
            "\n" +
            " 客户营销-小型企业：customerMarketingSmallEnterprise，\n" +
            "\n" +
            " 客户营销-中型企业：customerMarketingMediumEnterprise，\n" +
            "\n" +
            " 客户营销-大型企业：customerMarketingMajorEnterprise，\n" +
            "\n" +
            " 客户营销-公共机构：customerMarketingPublicInstitution，\n" +
            "\n" +
            " 业务营销-可用费用总量：businessMarketingTotalCostAvailable，\n" +
            "\n" +
            " 业务营销-贷款业务(单位：百万元)：businessMarketingLoanTransaction，\n" +
            "\n" +
            " 业务营销-存款业务(单位：百万元)：businessMarketingDepositBloanTransaction，\n" +
            "\n" +
            " 业务营销-中间业务(单位：百万元)：businessMarketingMiddleTransaction，\n" +
            "\n" +
            " 保存:0,提交:1：state，\n"
            +"}")
    @PostMapping("/decMarketStrategy/add")
    public ResponseDTO<String> add(@RequestBody @Validated DecMarketStrategyAddDTO addTO, HttpServletRequest request){
        return decMarketStrategyService.add(addTO,request);
    }

//    @ApiOperation(value = "预计算市场营销",notes = "@author SMS")
//    @PostMapping("/decMarketStrategy/preOperation")
//    public ResponseDTO<Map<String,Object>> preOperation(@RequestBody @Validated DecMarketStrategyAddDTO addTO){
//        return decMarketStrategyService.preOperation(addTO);
//    }


    //查询保存的市场营销决策信息  12-11  13:59
    @ApiOperation(value="-查询保存的市场营销决策信息",notes = "@author SMS")
    @GetMapping("/decMarketStrategy/queryMarket")
    public ResponseDTO<DecMarketStrategyVO> queryMarket(@RequestParam("currentProjectId") Long currentProjectId,@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId){
        return decMarketStrategyService.queryMarket(currentProjectId,currentTeamId,currentStageId);
    }



    @ApiOperation(value="修改市场营销策略",notes = "@author SMS")
    @PostMapping("/decMarketStrategy/update")
    public ResponseDTO<String> update(@RequestBody @Validated DecMarketStrategyUpdateDTO updateDTO){
        return decMarketStrategyService.update(updateDTO);
    }

    @ApiOperation(value="批量删除市场营销策略",notes = "@author SMS")
    @PostMapping("/decMarketStrategy/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return decMarketStrategyService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/decMarketStrategy/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<DecMarketStrategyExcelVO> decMarketStrategyList = decMarketStrategyService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("市场营销策略", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecMarketStrategyExcelVO.class, decMarketStrategyList);
        downloadExcel("市场营销策略", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/decMarketStrategy/export/all")
    public void exportAll(@RequestBody @Validated DecMarketStrategyQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<DecMarketStrategyExcelVO> decMarketStrategyList = decMarketStrategyService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("市场营销策略", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecMarketStrategyExcelVO.class, decMarketStrategyList);
        downloadExcel("市场营销策略", workbook, response);
    }

}
