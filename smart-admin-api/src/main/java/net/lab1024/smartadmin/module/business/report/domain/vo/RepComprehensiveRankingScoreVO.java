package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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
public class RepComprehensiveRankingScoreVO {
    @ApiModelProperty("综合排名分数表ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("资本充足率")
    private Double capitalAdequacyRatio;

    @ApiModelProperty("资产规模")
    private Double assetScale;

    @ApiModelProperty("不良贷款率")
    private Double nonPerformingLoanRatio;

    @ApiModelProperty("不良贷款拨备覆盖率")
    private Double nplProvisionCoverage;

    @ApiModelProperty("流动性比率")
    private Double liquidityRatio;

    @ApiModelProperty("存贷比")
    private Double depositLoanRatio;

    @ApiModelProperty("净利润（净亏损）")
    private Double netProfitLoss;

    @ApiModelProperty("资产收益率")
    private Double returnOnAssets;

    @ApiModelProperty("净资产收益率")
    private Double returnOnNetAssets;

    @ApiModelProperty("EVA")
    private Double economicValueAdded;

    @ApiModelProperty("成本收入比")
    private Double costIncomeRatio;

    @ApiModelProperty("搭桥贷款")
    private Double bridgeLoan;

    @ApiModelProperty("计划质量")
    private Double planQuality;

    @ApiModelProperty("分红")
    private Double shareOutBonus;

    @ApiModelProperty("总分数")
    private Double totalScore;

    @ApiModelProperty("股价")
    private Double stockPrice;
    @ApiModelProperty("团队名称")
    private String name;
    @ApiModelProperty("团队logo")
    private String logo;
    @ApiModelProperty("团队口号")
    private String shortName;
}
