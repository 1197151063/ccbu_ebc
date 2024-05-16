package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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
public class ParInitialDataLiquidityTotalityVO {
    @ApiModelProperty("初始内容表-流动性报表-总体ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("存放中央银行款项")
    private Integer dueCentralBank;

    @ApiModelProperty("存放同业款项")
    private Integer depositInterbank;

    @ApiModelProperty("拆放同业")
    private Integer loanTrade;

    @ApiModelProperty("贴现")
    private Integer discount;

    @ApiModelProperty("短期投资")
    private Integer shortInvestment;

    @ApiModelProperty("长期债权投资")
    private Integer longDebtInvestment;

    @ApiModelProperty("长期股权投资")
    private Integer longEquityInvestment;

    @ApiModelProperty("易变现资产总计")
    private Integer totalRealizedAssets;

    @ApiModelProperty("同业存放款项")
    private Integer interbankDeposit;

    @ApiModelProperty("同业拆入")
    private Integer interBank;

    @ApiModelProperty("短期存款")
    private Integer shortDeposit;

    @ApiModelProperty("长期存款")
    private Integer longDeposit;

    @ApiModelProperty("短期储蓄存款")
    private Integer shortSavingsDeposit;

    @ApiModelProperty("长期储蓄存款")
    private Integer longSavingsDeposit;

    @ApiModelProperty("存入短期及长期保证金")
    private Integer depositShortLongMargin;

    @ApiModelProperty("向中央银行借款")
    private Integer borrowingsCentralBank;

    @ApiModelProperty("流动性负债总计")
    private Integer totalCurrentLiabilities;

    @ApiModelProperty("总体流动性要求")
    private Long overallLiquidityRequirements;

    @ApiModelProperty("清偿力")
    private Integer solvency;

    @ApiModelProperty("总体清偿力")
    private Integer totalitySolvency;

    @ApiModelProperty("总体流动性盈余/不足")
    private Long overallLiquiditySurplusShortage;



}
