package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 资本充足率报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 11:17:26
 * @since JDK1.8
 */
@Data
public class RepCapitalAdequacyExcelVO {
//    @Excel(name = "资本充足率报告ID")
//    private Long id;
//
//    @Excel(name = "当前项目ID")
//    private Long currentProjectId;
//
//    @Excel(name = "当前团队ID")
//    private Long currentTeamId;
//
//    @Excel(name = "当前阶段ID")
//    private Long currentStageId;

    @Excel(name = "团队名称")
    private String name;

    @Excel(name = "资产风险(账面价值 风险加权系数% 风险加权资产)")
    private String propertyRisk;

    @Excel(name = "现金")
    private Integer cash;

    @Excel(name = "存放中央银行款项")
    private Integer dueCentralBank;

    @Excel(name = "存放同业款项")
    private Integer depositInterbank;

    @Excel(name = "拆放同业")
    private Integer loanTrade;

    @Excel(name = "短期_抵押贷款")
    private Integer shortMortgageLoan;

    @Excel(name = "短期_质押贷款")
    private Integer shortHypothecatedLoan;

    @Excel(name = "短期_保证贷款")
    private Integer shortGuaranteeLoan;

    @Excel(name = "短期_信用贷款")
    private Integer shortCreditLoan;

    @Excel(name = "贴现")
    private Integer discount;

    @Excel(name = "中长期_抵押贷款")
    private Integer mlongMortgageLoan;

    @Excel(name = "中长期_质押贷款")
    private Integer mlongHypothecatedLoan;

    @Excel(name = "中长期_保证贷款")
    private Integer mlongGuaranteeLoan;

    @Excel(name = "中长期_信用贷款")
    private Integer mlongCreditLoan;

    @Excel(name = "短期投资-债券")
    private Integer currentInvestmentBond;

    @Excel(name = "短期投资-股票")
    private Integer currentInvestmentStock;

    @Excel(name = "长期债权投资")
    private Integer longDebtInvestment;

    @Excel(name = "长期股权投资")
    private Integer longEquityInvestment;

    @Excel(name = "固定资产")
    private Integer fixedAssets;

    @Excel(name = "无形资产")
    private Integer intangibleAssets;

    @Excel(name = "其他资产")
    private Integer otherAssets;

    @Excel(name = "总计")
    private Integer total;

    @Excel(name = "资本充足率要求")
    private Integer capitalAdequacyRequirements;

    @Excel(name = "资本来源-核心资本-股本")
    private Integer capitalSourceCoreStock;

    @Excel(name = "资本来源-核心资本-资本公积")
    private Integer capitalSourceCoreCapitalReserve;

    @Excel(name = "资本来源-核心资本-盈余公积")
    private Integer capitalSourceCoreSurplusReserve;

    @Excel(name = "资本来源-核心资本-未分配利润")
    private Integer capitalSourceCoreUnProfit;

    @Excel(name = "资本来源-附属资本-贷款损失准备")
    private Integer capitalSourceLossReserves;

    @Excel(name = "资本来源-附属资本-次级债券")
    private Integer capitalSourceSubordBond;

    @Excel(name = "资本来源总额")
    private Integer capitalSourceTotal;

    @Excel(name = "资本盈余/不足")
    private Integer capitalSurplusShortage;



}
