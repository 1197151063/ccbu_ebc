package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 初始数据表-利润 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:47:09
 * @since JDK1.8
 */
@Data
public class ParInitialDataProfitExcelVO {
    @Excel(name = "初始内容表-利润ID")
    private Long id;

    @Excel(name = "初始阶段ID")
    private Long currentStageId;

    @Excel(name = "利息收入")
    private Integer interestIncome;

    @Excel(name = "金融机构往来收入")
    private Integer financialOrganizationIncome;

    @Excel(name = "手续费收入")
    private Integer serviceChargeIncome;

    @Excel(name = "营业收入合计")
    private Integer totalRevenues;

    @Excel(name = "利息支出")
    private Integer interestExpense;

    @Excel(name = "金融机构往来支出")
    private Integer financialOrganizationExpense;

    @Excel(name = "手续费支出")
    private Integer serviceChargeExpense;

    @Excel(name = "营业支出合计")
    private Integer expenseRevenues;

    @Excel(name = "人员开支")
    private Integer peopleCosts;

    @Excel(name = "附加人员成本")
    private Integer additionPeopleCosts;

    @Excel(name = "其他人员开支")
    private Integer otherPeopleCosts;

    @Excel(name = "固定资产折旧")
    private Integer fixedAssetsDepreciation;

    @Excel(name = "其它营业费用")
    private Integer otherBusinessCosts;

    @Excel(name = "营业费用合计")
    private Integer totalOperatingExpenses;

    @Excel(name = "投资收益")
    private Integer incomeInvestment;

    @Excel(name = "营业利润")
    private Integer operatingProfit;

    @Excel(name = "减：营业税金及附加")
    private Integer subtractBusTaxSurch;

    @Excel(name = "加：营业外收入")
    private Integer addNonbusIncome;

    @Excel(name = "减：营业外支出")
    private Integer subtractNonbusExpend;

    @Excel(name = "扣除资产减值前利润总额")
    private Integer deductAssetImpairmentBeforeTotalProfit;

    @Excel(name = "减：资产准备支出")
    private Integer subtractAssetReserveExpense;

    @Excel(name = "扣除资产减值后利润总额")
    private Integer deductAssetImpairmentAfterTotalProfit;

    @Excel(name = "减：所得税")
    private Integer subtractIncomeTax;

    @Excel(name = "净利润")
    private Integer retainedProfits;



}
