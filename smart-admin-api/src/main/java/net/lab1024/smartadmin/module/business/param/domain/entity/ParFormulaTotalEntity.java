package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 公式 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 17:01:27
 * @since JDK1.8
 */
@Data
@TableName("par_formula_total")
public class ParFormulaTotalEntity extends BaseEntity{


    /**
     * 业务
     */
    private String business;

    /**
     * 项目
     */
    private String project;

    /**
     * 公式
     */
    private String formula;

}
