package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 资本充足率报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:17:26
 * @since JDK1.8
 */
@Data
@TableName("rep_capital_adequacy")
public class RepCapitalAdequacyEntity extends BaseEntity{


    /**
     * 当前项目ID
     */
    private Long currentProjectId;

    /**
     * 当前团队ID
     */
    private Long currentTeamId;

    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 资产风险(账面价值 风险加权系数% 风险加权资产)
     */
    private String propertyRisk;

    /**
     * 现金
     */
    private Integer cash;

    /**
     * 存放中央银行款项
     */
    private Integer dueCentralBank;

    /**
     * 存放同业款项
     */
    private Integer depositInterbank;

    /**
     * 拆放同业
     */
    private Integer loanTrade;

    /**
     * 短期_抵押贷款
     */
    private Integer shortMortgageLoan;

    /**
     * 短期_质押贷款
     */
    private Integer shortHypothecatedLoan;

    /**
     * 短期_保证贷款
     */
    private Integer shortGuaranteeLoan;

    /**
     * 短期_信用贷款
     */
    private Integer shortCreditLoan;

    /**
     * 贴现
     */
    private Integer discount;

    /**
     * 中长期_抵押贷款
     */
    private Integer mlongMortgageLoan;

    /**
     * 中长期_质押贷款
     */
    private Integer mlongHypothecatedLoan;

    /**
     * 中长期_保证贷款
     */
    private Integer mlongGuaranteeLoan;

    /**
     * 中长期_信用贷款
     */
    private Integer mlongCreditLoan;

    /**
     * 短期投资-债券
     */
    private Integer currentInvestmentBond;

    /**
     * 短期投资-股票
     */
    private Integer currentInvestmentStock;

    /**
     * 长期债权投资
     */
    private Integer longDebtInvestment;

    /**
     * 长期股权投资
     */
    private Integer longEquityInvestment;

    /**
     * 固定资产
     */
    private Integer fixedAssets;

    /**
     * 无形资产
     */
    private Integer intangibleAssets;

    /**
     * 其他资产
     */
    private Integer otherAssets;

    /**
     * 总计
     */
    private Integer total;

    /**
     * 资本充足率要求
     */
    private Integer capitalAdequacyRequirements;

    /**
     * 资本来源-核心资本-股本
     */
    private Integer capitalSourceCoreStock;

    /**
     * 资本来源-核心资本-资本公积
     */
    private Integer capitalSourceCoreCapitalReserve;

    /**
     * 资本来源-核心资本-盈余公积
     */
    private Integer capitalSourceCoreSurplusReserve;

    /**
     * 资本来源-核心资本-未分配利润
     */
    private Integer capitalSourceCoreUnProfit;

    /**
     * 资本来源-附属资本-贷款损失准备
     */
    private Integer capitalSourceLossReserves;

    /**
     * 资本来源-附属资本-次级债券
     */
    private Integer capitalSourceSubordBond;

    /**
     * 资本来源总额
     */
    private Integer capitalSourceTotal;

    /**
     * 资本盈余/不足
     */
    private Integer capitalSurplusShortage;

}
