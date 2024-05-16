package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 初始数据表-流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:45:23
 * @since JDK1.8
 */
@Data
public class ParInitialDataLiquidityTotalityExcelVO {
    @Excel(name = "初始内容表-流动性报表-总体ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "存放中央银行款项")
    private Integer dueCentralBank;

    @Excel(name = "存放同业款项")
    private Integer depositInterbank;

    @Excel(name = "拆放同业")
    private Integer loanTrade;

    @Excel(name = "贴现")
    private Integer discount;

    @Excel(name = "短期投资")
    private Integer shortInvestment;

    @Excel(name = "长期债权投资")
    private Integer longDebtInvestment;

    @Excel(name = "长期股权投资")
    private Integer longEquityInvestment;

    @Excel(name = "易变现资产总计")
    private Integer totalRealizedAssets;

    @Excel(name = "同业存放款项")
    private Integer interbankDeposit;

    @Excel(name = "同业拆入")
    private Integer interBank;

    @Excel(name = "短期存款")
    private Integer shortDeposit;

    @Excel(name = "长期存款")
    private Integer longDeposit;

    @Excel(name = "短期储蓄存款")
    private Integer shortSavingsDeposit;

    @Excel(name = "长期储蓄存款")
    private Integer longSavingsDeposit;

    @Excel(name = "存入短期及长期保证金")
    private Integer depositShortLongMargin;

    @Excel(name = "向中央银行借款")
    private Integer borrowingsCentralBank;

    @Excel(name = "流动性负债总计")
    private Integer totalCurrentLiabilities;

    @Excel(name = "总体流动性要求")
    private Long overallLiquidityRequirements;

    @Excel(name = "清偿力")
    private Integer solvency;

    @Excel(name = "总体清偿力")
    private Integer totalitySolvency;

    @Excel(name = "总体流动性盈余/不足")
    private Long overallLiquiditySurplusShortage;



}
