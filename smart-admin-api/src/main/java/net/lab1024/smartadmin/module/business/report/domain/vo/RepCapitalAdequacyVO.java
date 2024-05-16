package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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
public class RepCapitalAdequacyVO {
    @ApiModelProperty("资本充足率报告ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("资产风险(账面价值 风险加权系数% 风险加权资产)")
    private String propertyRisk;

    @ApiModelProperty("现金")
    private Integer cash;

    @ApiModelProperty("存放中央银行款项")
    private Integer dueCentralBank;

    @ApiModelProperty("存放同业款项")
    private Integer depositInterbank;

    @ApiModelProperty("拆放同业")
    private Integer loanTrade;

    @ApiModelProperty("短期_抵押贷款")
    private Integer shortMortgageLoan;

    @ApiModelProperty("短期_质押贷款")
    private Integer shortHypothecatedLoan;

    @ApiModelProperty("短期_保证贷款")
    private Integer shortGuaranteeLoan;

    @ApiModelProperty("短期_信用贷款")
    private Integer shortCreditLoan;

    @ApiModelProperty("贴现")
    private Integer discount;

    @ApiModelProperty("中长期_抵押贷款")
    private Integer mlongMortgageLoan;

    @ApiModelProperty("中长期_质押贷款")
    private Integer mlongHypothecatedLoan;

    @ApiModelProperty("中长期_保证贷款")
    private Integer mlongGuaranteeLoan;

    @ApiModelProperty("中长期_信用贷款")
    private Integer mlongCreditLoan;

    @ApiModelProperty("短期投资-债券")
    private Integer currentInvestmentBond;

    @ApiModelProperty("短期投资-股票")
    private Integer currentInvestmentStock;

    @ApiModelProperty("长期债权投资")
    private Integer longDebtInvestment;

    @ApiModelProperty("长期股权投资")
    private Integer longEquityInvestment;

    @ApiModelProperty("固定资产")
    private Integer fixedAssets;

    @ApiModelProperty("无形资产")
    private Integer intangibleAssets;

    @ApiModelProperty("其他资产")
    private Integer otherAssets;

    @ApiModelProperty("总计")
    private Integer total;

    @ApiModelProperty("资本充足率要求")
    private Integer capitalAdequacyRequirements;

    @ApiModelProperty("资本来源-核心资本-股本")
    private Integer capitalSourceCoreStock;

    @ApiModelProperty("资本来源-核心资本-资本公积")
    private Integer capitalSourceCoreCapitalReserve;

    @ApiModelProperty("资本来源-核心资本-盈余公积")
    private Integer capitalSourceCoreSurplusReserve;

    @ApiModelProperty("资本来源-核心资本-未分配利润")
    private Integer capitalSourceCoreUnProfit;

    @ApiModelProperty("资本来源-附属资本-贷款损失准备")
    private Integer capitalSourceLossReserves;

    @ApiModelProperty("资本来源-附属资本-次级债券")
    private Integer capitalSourceSubordBond;

    @ApiModelProperty("资本来源总额")
    private Integer capitalSourceTotal;

    @ApiModelProperty("资本盈余/不足")
    private Integer capitalSurplusShortage;



}
