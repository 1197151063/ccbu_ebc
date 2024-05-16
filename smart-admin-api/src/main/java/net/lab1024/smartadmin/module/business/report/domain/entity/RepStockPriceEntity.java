package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 股价表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:06:10
 * @since JDK1.8
 */
@Data
@TableName("rep_stock_price")
public class RepStockPriceEntity extends BaseEntity{


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
     * 流动性比率
     */
    private Double liquidityRatio;

    /**
     * 净资产收益率
     */
    private Double returnOnNetAssets;

    /**
     * 每股股利（分红）
     */
    private Double shareOutBonus;

    /**
     * 每股收益
     */
    private Double perShareProfit;

    /**
     * 每股净资产
     */
    private Double perShareNetAssets;

    /**
     * 股价
     */
    private Double stockPrice;

}
