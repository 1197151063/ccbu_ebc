package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 初始内容表-资产负债 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:46:26
 * @since JDK1.8
 */
@Data
public class ParInitialDataLoanDepositExcelVO {
    @Excel(name = "初始内容表-资产负债ID")
    private Long id;

    @Excel(name = "初始阶段ID")
    private Long currentStageId;

    @Excel(name = "现金")
    private Integer cash;

    @Excel(name = "存放中央银行款项")
    private Integer dueCentralBank;

    @Excel(name = "存放同业款项")
    private Integer depositInterbank;

    @Excel(name = "拆放同业")
    private Integer loanTrade;

    @Excel(name = "短期贷款")
    private Integer shortLoan;

    @Excel(name = "应收利息")
    private Integer interestReceivable;

    @Excel(name = "其他应收款")
    private Integer otherReceivables;

    @Excel(name = "贴现")
    private Integer discount;

    @Excel(name = "短期投资")
    private Integer shortInvestment;

    @Excel(name = "其他流动资产")
    private Integer otherProperty;

    @Excel(name = "流动资产合计")
    private Integer propertySum;

    @Excel(name = "中长期贷款")
    private Integer mlongLoan;

    @Excel(name = "减：贷款损失准备")
    private Integer lossReserves;

    @Excel(name = "长期债权投资")
    private Integer longDebtInvestment;

    @Excel(name = "长期股权投资")
    private Integer longEquityInvestment;

    @Excel(name = "固定资产")
    private Integer fixedAssets;

    @Excel(name = "在建工程")
    private Integer constructionProcess;

    @Excel(name = "长期资产合计")
    private Integer longTotalAsset;

    @Excel(name = "无形资产")
    private Integer intangibleAssets;

    @Excel(name = "其他资产")
    private Integer otherAssets;

    @Excel(name = "无形资产及其他资产合计")
    private Integer intangibleAotherAssets;

    @Excel(name = "资产总计")
    private Integer totalAssets;

    @Excel(name = "短期存款")
    private Integer shortDeposit;

    @Excel(name = "短期储蓄存款")
    private Integer shortSavingsDeposit;

    @Excel(name = "向中央银行借款	")
    private Integer borrowingsCentralBank;

    @Excel(name = "同业存放款项")
    private Integer interbankDeposit;

    @Excel(name = "同业拆入")
    private Integer interBank;

    @Excel(name = "存入短期保证金")
    private Integer depositShortMargin;

    @Excel(name = "应付利息")
    private Integer paymentInterest;

    @Excel(name = "流动负债合计")
    private Integer totalCurrentLiability;

    @Excel(name = "长期存款")
    private Integer longDeposit;

    @Excel(name = "长期储蓄存款")
    private Integer longSavingsDeposit;

    @Excel(name = "存入长期保证金")
    private Integer depositLongMargin;

    @Excel(name = "应付长期债券")
    private Integer paymentLongBond;

    @Excel(name = "应付次级债券")
    private Integer paymentSubBond;

    @Excel(name = "长期负债rep_合计")
    private Integer totalLongLiabilities;

    @Excel(name = "负债合计")
    private Integer totalLiabilities;

    @Excel(name = "股本")
    private Integer capital;

    @Excel(name = "资本公积")
    private Integer capitalReserve;

    @Excel(name = "盈余公积")
    private Integer surplusReserve;

    @Excel(name = "未分配利润")
    private Integer undistributedProfit;

    @Excel(name = "股东权益合计")
    private Integer totalShareholdersEquity;

    @Excel(name = "负债和股东权益总计")
    private Integer totalLiabilitiesEquityIndebted;



}
