package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 代理债券折扣 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:44:56
 * @since JDK1.8
 */
@Data
@TableName("par_gency_bond_discount")
public class ParGencyBondDiscountEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 债券类型
     */
    private String bondType;

    /**
     * 折扣
     */
    private Integer discount;

}
