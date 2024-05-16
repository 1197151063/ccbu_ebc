package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 16:06:06
 * @since JDK1.8
 */
@Data
@TableName("rep_liquidity_totality")
public class RepLiquidityTotalityEntity extends BaseEntity{


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
     * 贴现
     */
    private Integer discount;

    /**
     * 短期投资
     */
    private Integer shortInvestment;

    /**
     * 长期债权投资
     */
    private Integer longDebtInvestment;

    /**
     * 长期股权投资
     */
    private Integer longEquityInvestment;

    /**
     * 易变现资产总计
     */
    private Integer totalRealizedAssets;

    /**
     * 同业存放款项
     */
    private Integer interbankDeposit;

    /**
     * 同业拆入
     */
    private Integer interBank;

    /**
     * 短期存款
     */
    private Integer shortDeposit;

    /**
     * 长期存款
     */
    private Integer longDeposit;

    /**
     * 短期储蓄存款
     */
    private Integer shortSavingsDeposit;

    /**
     * 长期储蓄存款
     */
    private Integer longSavingsDeposit;

    /**
     * 存入短期及长期保证金
     */
    private Integer depositShortLongMargin;

    /**
     * 向中央银行借款
     */
    private Integer borrowingsCentralBank;

    /**
     * 流动性负债总计
     */
    private Integer totalCurrentLiabilities;

    /**
     * 总体流动性要求
     */
    private Integer overallLiquidityRequirements;

    /**
     * 清偿力
     */
    private Integer solvency;

    /**
     * 总体清偿力
     */
    private Integer totalitySolvency;

    /**
     * 总体流动性盈余/不足
     */
    private Integer overallLiquiditySurplusShortage;

}
