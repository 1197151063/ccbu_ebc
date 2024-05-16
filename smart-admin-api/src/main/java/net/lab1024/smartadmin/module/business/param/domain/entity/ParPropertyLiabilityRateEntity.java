package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 资产负债利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:53
 * @since JDK1.8
 */
@Data
@TableName("par_property_liability_rate")
public class ParPropertyLiabilityRateEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 类型(资产，负债)
     */
    private String interestRateType;

    /**
     * 最低
     */
    private Double interestRateMin;

    /**
     * 平均
     */
    private Double interestRateAverage;

    /**
     * 最高
     */
    private Double interestRateMax;

}
