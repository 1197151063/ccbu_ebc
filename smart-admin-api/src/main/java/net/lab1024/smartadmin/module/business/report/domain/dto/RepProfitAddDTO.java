package net.lab1024.smartadmin.module.business.report.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 利润表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-24 16:07:55
 * @since JDK1.8
 */
@Data
public class RepProfitAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("利息收入")
    private Integer interestIncome;

    @ApiModelProperty("金融机构往来收入")
    private Integer financialOrganizationIncome;

    @ApiModelProperty("手续费收入")
    private Integer serviceChargeIncome;

    @ApiModelProperty("营业收入合计")
    private Integer totalRevenues;

    @ApiModelProperty("利息支出")
    private Integer interestExpense;

    @ApiModelProperty("金融机构往来支出")
    private Integer financialOrganizationExpense;

    @ApiModelProperty("手续费支出")
    private Integer serviceChargeExpense;

    @ApiModelProperty("营业支出合计")
    private Integer expenseRevenues;

    @ApiModelProperty("人员开支")
    private Integer peopleCosts;

    @ApiModelProperty("附加人员成本")
    private Integer additionPeopleCosts;

    @ApiModelProperty("其他人员开支")
    private Integer otherPeopleCosts;

    @ApiModelProperty("固定资产折旧")
    private Integer fixedAssetsDepreciation;

    @ApiModelProperty("其它营业费用")
    private Integer otherBusinessCosts;

    @ApiModelProperty("营业费用合计")
    private Integer totalOperatingExpenses;

    @ApiModelProperty("投资收益")
    private Integer incomeInvestment;

    @ApiModelProperty("营业利润")
    private Integer operatingProfit;

    @ApiModelProperty("减：营业税金及附加")
    private Integer subtractBusTaxSurch;

    @ApiModelProperty("加：营业外收入")
    private Integer addNonbusIncome;

    @ApiModelProperty("减：营业外支出")
    private Integer subtractNonbusExpend;

    @ApiModelProperty("扣除资产减值前利润总额")
    private Integer deductAssetImpairmentBeforeTotalProfit;

    @ApiModelProperty("减：资产准备支出")
    private Integer subtractAssetReserveExpense;

    @ApiModelProperty("扣除资产减值后利润总额")
    private Integer deductAssetImpairmentAfterTotalProfit;

    @ApiModelProperty("减：所得税")
    private Integer subtractIncomeTax;

    @ApiModelProperty("净利润")
    private Integer retainedProfits;


}
