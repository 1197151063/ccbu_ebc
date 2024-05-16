package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 股价计划指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:09:54
 * @since JDK1.8
 */
@Data
@TableName("par_stock_calculation_index")
public class ParStockCalculationIndexEntity extends BaseEntity{


    /**
     * 指标
     */
    private String index;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 最小值
     */
    private Double minScore;

    /**
     * 最大值
     */
    private Double maxScore;

    /**
     * 分值
     */
    private Double score;

}
