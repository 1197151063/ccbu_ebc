package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 债券参数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:42:33
 * @since JDK1.8
 */
@Data
@TableName("par_bond_parameter")
public class ParBondParameterEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 债券种类(各阶段解锁一个，初始值只能加不能减)
     */
    private String bondParameter;

    /**
     * 债券价格%
     */
    private Double bondPrice;

}
