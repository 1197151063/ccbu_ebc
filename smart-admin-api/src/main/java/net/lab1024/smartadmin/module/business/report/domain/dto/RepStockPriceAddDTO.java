package net.lab1024.smartadmin.module.business.report.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 股价表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-17 19:06:10
 * @since JDK1.8
 */
@Data
public class RepStockPriceAddDTO {
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

    @ApiModelProperty("流动性比率")
    private Double liquidityRatio;

    @ApiModelProperty("净资产收益率")
    private Double returnOnNetAssets;

    @ApiModelProperty("每股股利（分红）")
    private Double shareOutBonus;

    @ApiModelProperty("每股收益")
    private Double perShareProfit;

    @ApiModelProperty("每股净资产")
    private Double perShareNetAssets;

    @ApiModelProperty("股价")
    private Double stockPrice;


}
