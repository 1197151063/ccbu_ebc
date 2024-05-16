package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 市场供求预测表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:49:31
 * @since JDK1.8
 */
@Data
@TableName("par_market_supply_demand_forecast")
public class ParMarketSupplyDemandForecastEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 预期市场供求(%)
     */
    private String marketProspect;

}
