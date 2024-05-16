package net.lab1024.smartadmin.module.business.basics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 阶段业务名称 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 09:36:32
 * @since JDK1.8
 */
@Data
@TableName("sys_stage_business")
public class SysStageBusinessEntity extends BaseEntity{


    /**
     * 阶段id
     */
    private Long stageId;

    /**
     * 阶段名称
     */
    private String stageName;

    /**
     * 业务名称
     */
    private String businessName;

}
