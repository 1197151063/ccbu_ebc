package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 初始数据表-利润 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:47:09
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_profit")
public class ParInitialDataProfitEntity extends BaseEntity{


    /**
     * 初始阶段ID
     */
    private Long currentStageId;

    /**
     * 利息收入
     */
    private Integer interestIncome;

    /**
     * 金融机构往来收入
     */
    private Integer financialOrganizationIncome;

    /**
     * 手续费收入
     */
    private Integer serviceChargeIncome;

    /**
     * 营业收入合计
     */
    private Integer totalRevenues;

    /**
     * 利息支出
     */
    private Integer interestExpense;

    /**
     * 金融机构往来支出
     */
    private Integer financialOrganizationExpense;

    /**
     * 手续费支出
     */
    private Integer serviceChargeExpense;

    /**
     * 营业支出合计
     */
    private Integer expenseRevenues;

    /**
     * 人员开支
     */
    private Integer peopleCosts;

    /**
     * 附加人员成本
     */
    private Integer additionPeopleCosts;

    /**
     * 其他人员开支
     */
    private Integer otherPeopleCosts;

    /**
     * 固定资产折旧
     */
    private Integer fixedAssetsDepreciation;

    /**
     * 其它营业费用
     */
    private Integer otherBusinessCosts;

    /**
     * 营业费用合计
     */
    private Integer totalOperatingExpenses;

    /**
     * 投资收益
     */
    private Integer incomeInvestment;

    /**
     * 营业利润
     */
    private Integer operatingProfit;

    /**
     * 减：营业税金及附加
     */
    private Integer subtractBusTaxSurch;

    /**
     * 加：营业外收入
     */
    private Integer addNonbusIncome;

    /**
     * 减：营业外支出
     */
    private Integer subtractNonbusExpend;

    /**
     * 扣除资产减值前利润总额
     */
    private Integer deductAssetImpairmentBeforeTotalProfit;

    /**
     * 减：资产准备支出
     */
    private Integer subtractAssetReserveExpense;

    /**
     * 扣除资产减值后利润总额
     */
    private Integer deductAssetImpairmentAfterTotalProfit;

    /**
     * 减：所得税
     */
    private Integer subtractIncomeTax;

    /**
     * 净利润
     */
    private Integer retainedProfits;

}
