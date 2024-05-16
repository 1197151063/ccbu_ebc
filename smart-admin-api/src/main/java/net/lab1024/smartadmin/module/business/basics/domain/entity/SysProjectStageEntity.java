package net.lab1024.smartadmin.module.business.basics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 项目阶段关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 16:13:42
 * @since JDK1.8
 */
@Data
@TableName("sys_project_stage")
public class SysProjectStageEntity extends BaseEntity{


    /**
     * 项目id
     */
    private Long proId;

    /**
     * 阶段id
     */
    private Long stageId;

    /**
     * 阶段是否开放（0：未开放1：已开放）
     */
    private Integer status;

}
