package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 14:42:04
 * @since JDK1.8
 */
@Data
@TableName("par_formula")
public class ParFormulaEntity extends BaseEntity{


    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 参数
     */
    private Double parameter;

}
