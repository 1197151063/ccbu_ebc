package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 初始数据表-流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:44:40
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_liquidity_cash")
public class ParInitialDataLiquidityCashEntity extends BaseEntity{


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
    private Long cashLiquidityRequirements;

    /**
     * 清偿力
     */
    private Integer solvency;

    /**
     * 现金清偿力
     */
    private Integer cashSolvency;

}
