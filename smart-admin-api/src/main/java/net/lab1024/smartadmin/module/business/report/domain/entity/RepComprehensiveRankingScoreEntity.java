package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 综合排名分数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 14:42:37
 * @since JDK1.8
 */
@Data
@TableName("rep_comprehensive_ranking_score")
public class RepComprehensiveRankingScoreEntity extends BaseEntity{


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
     * 资本充足率
     */
    private Double capitalAdequacyRatio;

    /**
     * 资产规模
     */
    private Double assetScale;

    /**
     * 不良贷款率
     */
    private Double nonPerformingLoanRatio;

    /**
     * 不良贷款拨备覆盖率
     */
    private Double nplProvisionCoverage;

    /**
     * 流动性比率
     */
    private Double liquidityRatio;

    /**
     * 存贷比
     */
    private Double depositLoanRatio;

    /**
     * 净利润（净亏损）
     */
    private Double netProfitLoss;

    /**
     * 资产收益率
     */
    private Double returnOnAssets;

    /**
     * 净资产收益率
     */
    private Double returnOnNetAssets;

    /**
     * EVA
     */
    private Double economicValueAdded;

    /**
     * 成本收入比
     */
    private Double costIncomeRatio;

    /**
     * 搭桥贷款
     */
    private Double bridgeLoan;

    /**
     * 计划质量
     */
    private Double planQuality;

    /**
     * 分红
     */
    private Double shareOutBonus;

    /**
     * 总分数
     */
    private Double totalScore;

}
