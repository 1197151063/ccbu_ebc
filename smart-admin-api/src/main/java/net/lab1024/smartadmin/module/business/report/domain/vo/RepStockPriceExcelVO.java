package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 股价表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-17 19:06:10
 * @since JDK1.8
 */
@Data
public class RepStockPriceExcelVO {
    @Excel(name = "股价表ID")
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

    @Excel(name = "流动性比率")
    private Double liquidityRatio;

    @Excel(name = "净资产收益率")
    private Double returnOnNetAssets;

    @Excel(name = "每股股利（分红）")
    private Double shareOutBonus;

    @Excel(name = "每股收益")
    private Double perShareProfit;

    @Excel(name = "每股净资产")
    private Double perShareNetAssets;

    @Excel(name = "股价")
    private Double stockPrice;



}
