package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 综合排名分数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-17 14:42:37
 * @since JDK1.8
 */
@Data
public class RepComprehensiveRankingScoreExcelVO {
    @Excel(name = "综合排名分数表ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "资本充足率")
    private Double capitalAdequacyRatio;

    @Excel(name = "资产规模")
    private Double assetScale;

    @Excel(name = "不良贷款率")
    private Double nonPerformingLoanRatio;

    @Excel(name = "不良贷款拨备覆盖率")
    private Double nplProvisionCoverage;

    @Excel(name = "流动性比率")
    private Double liquidityRatio;

    @Excel(name = "存贷比")
    private Double depositLoanRatio;

    @Excel(name = "净利润（净亏损）")
    private Double netProfitLoss;

    @Excel(name = "资产收益率")
    private Double returnOnAssets;

    @Excel(name = "净资产收益率")
    private Double returnOnNetAssets;

    @Excel(name = "EVA")
    private Double economicValueAdded;

    @Excel(name = "成本收入比")
    private Double costIncomeRatio;

    @Excel(name = "搭桥贷款")
    private Double bridgeLoan;

    @Excel(name = "计划质量")
    private Double planQuality;

    @Excel(name = "分红")
    private Double shareOutBonus;

    @Excel(name = "总分数")
    private Double totalScore;

    @ApiModelProperty("股价")
    private Double stockPrice;


}
