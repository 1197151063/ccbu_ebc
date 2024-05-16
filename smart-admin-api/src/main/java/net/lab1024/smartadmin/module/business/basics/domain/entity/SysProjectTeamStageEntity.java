package net.lab1024.smartadmin.module.business.basics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 项目团队阶段业务关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 11:10:47
 * @since JDK1.8
 */
@Data
@TableName("sys_project_team_stage")
public class SysProjectTeamStageEntity extends BaseEntity{


    /**
     * 项目id
     */
    private Long proId;

    /**
     * 团队id
     */
    private Long teamId;

    /**
     * 阶段id
     */
    private Long stageId;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 业务提交状态
     */
    private Integer status;

}
