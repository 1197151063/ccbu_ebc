package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 16:07:10
 * @since JDK1.8
 */
@Data
@TableName("rep_liquidity_cash")
public class RepLiquidityCashEntity extends BaseEntity{


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
     * 短期债务总计
     */
    private Integer totalShortDebt;

    /**
     * 现金流动性要求
     */
    private Integer cashLiquidityRequirements;

    /**
     * 清偿力
     */
    private Integer solvency;

    /**
     * 现金清偿力
     */
    private Integer cashSolvency;

}
