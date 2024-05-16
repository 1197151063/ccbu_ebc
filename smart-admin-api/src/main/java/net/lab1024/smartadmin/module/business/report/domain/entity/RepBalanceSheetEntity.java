package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

/**
 * [ 资产负债表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 16:08:53
 * @since JDK1.8
 */
@Data
@TableName("rep_balance_sheet")
public class RepBalanceSheetEntity extends BaseEntity{


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
     * 短期贷款
     */
    private Integer shortLoan;

    /**
     * 应收利息
     */
    private Integer interestReceivable;

    /**
     * 其他应收款
     */
    private Integer otherReceivables;

    /**
     * 贴现
     */
    private Integer discount;

    /**
     * 短期投资
     */
    private Integer shortInvestment;

    /**
     * 其他流动资产
     */
    private Integer otherProperty;

    /**
     * 流动资产合计
     */
    private Integer propertySum;

    /**
     * 中长期贷款
     */
    private Integer mlongLoan;

    /**
     * 减：贷款损失准备
     */
    private Integer lossReserves;

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
     * 在建工程
     */
    private Integer constructionProcess;

    /**
     * 长期资产合计
     */
    private Integer longTotalAsset;

    /**
     * 无形资产
     */
    private Integer intangibleAssets;

    /**
     * 其他资产
     */
    private Integer otherAssets;

    /**
     * 无形资产及其他资产合计
     */
    private Integer intangibleAotherAssets;

    /**
     * 资产总计
     */
    private Integer totalAssets;

    /**
     * 短期存款
     */
    private Integer shortDeposit;

    /**
     * 短期储蓄存款
     */
    private Integer shortSavingsDeposit;

    /**
     * 向中央银行借款	
     */
    private Integer borrowingsCentralBank;

    /**
     * 同业存放款项
     */
    private Integer interbankDeposit;

    /**
     * 同业拆入
     */
    private Integer interBank;

    /**
     * 存入短期保证金
     */
    private Integer depositShortMargin;

    /**
     * 应付利息
     */
    private Integer paymentInterest;

    /**
     * 流动负债合计
     */
    private Integer totalCurrentLiability;

    /**
     * 长期存款
     */
    private Integer longDeposit;

    /**
     * 长期储蓄存款
     */
    private Integer longSavingsDeposit;

    /**
     * 存入长期保证金
     */
    private Integer depositLongMargin;

    /**
     * 应付长期债券
     */
    private Integer paymentLongBond;

    /**
     * 应付次级债券
     */
    private Integer paymentSubBond;

    /**
     * 长期负债合计
     */
    private Integer totalLongLiabilities;

    /**
     * 负债合计
     */
    private Integer totalLiabilities;

    /**
     * 股本
     */
    private Integer capital;

    /**
     * 资本公积
     */
    private Integer capitalReserve;

    /**
     * 盈余公积
     */
    private Integer surplusReserve;

    /**
     * 未分配利润
     */
    private Integer undistributedProfit;

    /**
     * 股东权益合计
     */
    private Integer totalShareholdersEquity;

    /**
     * 负债和股东权益总计
     */
    private Integer totalLiabilitiesEquityIndebted;

}
