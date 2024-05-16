package net.lab1024.smartadmin.module.business.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-09 16:01:51
 * @since JDK1.8
 */
@Data
@TableName("t_decision_summary")
public class TDecisionSummaryEntity extends BaseEntity{


    /**
     * 项目
     */
    private String project;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 业务
     */
    private String business;

    /**
     * 决策团队
     */
    private String team;

    /**
     * 决策人
     */
    private String name;



    /**
     * 总结状态
     */
    private Integer status;

}
