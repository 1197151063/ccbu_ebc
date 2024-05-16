package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 资产负债表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 16:08:53
 * @since JDK1.8
 */
@Data
public class RepBalanceSheetVO {
    @ApiModelProperty("资产负债表ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("现金")
    private Integer cash;

    @ApiModelProperty("存放中央银行款项")
    private Integer dueCentralBank;

    @ApiModelProperty("存放同业款项")
    private Integer depositInterbank;

    @ApiModelProperty("拆放同业")
    private Integer loanTrade;

    @ApiModelProperty("短期贷款")
    private Integer shortLoan;

    @ApiModelProperty("应收利息")
    private Integer interestReceivable;

    @ApiModelProperty("其他应收款")
    private Integer otherReceivables;

    @ApiModelProperty("贴现")
    private Integer discount;

    @ApiModelProperty("短期投资")
    private Integer shortInvestment;

    @ApiModelProperty("其他流动资产")
    private Integer otherProperty;

    @ApiModelProperty("流动资产合计")
    private Integer propertySum;

    @ApiModelProperty("中长期贷款")
    private Integer mlongLoan;

    @ApiModelProperty("减：贷款损失准备")
    private Integer lossReserves;

    @ApiModelProperty("长期债权投资")
    private Integer longDebtInvestment;

    @ApiModelProperty("长期股权投资")
    private Integer longEquityInvestment;

    @ApiModelProperty("固定资产")
    private Integer fixedAssets;

    @ApiModelProperty("在建工程")
    private Integer constructionProcess;

    @ApiModelProperty("长期资产合计")
    private Integer longTotalAsset;

    @ApiModelProperty("无形资产")
    private Integer intangibleAssets;

    @ApiModelProperty("其他资产")
    private Integer otherAssets;

    @ApiModelProperty("无形资产及其他资产合计")
    private Integer intangibleAotherAssets;

    @ApiModelProperty("资产总计")
    private Integer totalAssets;

    @ApiModelProperty("短期存款")
    private Integer shortDeposit;

    @ApiModelProperty("短期储蓄存款")
    private Integer shortSavingsDeposit;

    @ApiModelProperty("向中央银行借款	")
    private Integer borrowingsCentralBank;

    @ApiModelProperty("同业存放款项")
    private Integer interbankDeposit;

    @ApiModelProperty("同业拆入")
    private Integer interBank;

    @ApiModelProperty("存入短期保证金")
    private Integer depositShortMargin;

    @ApiModelProperty("应付利息")
    private Integer paymentInterest;

    @ApiModelProperty("流动负债合计")
    private Integer totalCurrentLiability;

    @ApiModelProperty("长期存款")
    private Integer longDeposit;

    @ApiModelProperty("长期储蓄存款")
    private Integer longSavingsDeposit;

    @ApiModelProperty("存入长期保证金")
    private Integer depositLongMargin;

    @ApiModelProperty("应付长期债券")
    private Integer paymentLongBond;

    @ApiModelProperty("应付次级债券")
    private Integer paymentSubBond;

    @ApiModelProperty("长期负债合计")
    private Integer totalLongLiabilities;

    @ApiModelProperty("负债合计")
    private Integer totalLiabilities;

    @ApiModelProperty("股本")
    private Integer capital;

    @ApiModelProperty("资本公积")
    private Integer capitalReserve;

    @ApiModelProperty("盈余公积")
    private Integer surplusReserve;

    @ApiModelProperty("未分配利润")
    private Integer undistributedProfit;

    @ApiModelProperty("股东权益合计")
    private Integer totalShareholdersEquity;

    @ApiModelProperty("负债和股东权益总计")
    private Integer totalLiabilitiesEquityIndebted;

    @ApiModelProperty("团队名称")
    private String name;
}
