package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:24:21
 * @since JDK1.8
 */
@Data
@TableName("rep_personnel_logistics_data_automation")
public class RepPersonnelLogisticsDataAutomationEntity extends BaseEntity{


    /**
     * 当前项目ID
     */
    private Long currentProjectId;

    /**
     * 当前团队ID
     */
    private Long currentTeamId;

    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 项目
     */
    private String project;

    /**
     * 本阶段
     */
    private Integer presentStage;

    /**
     * 累计投资
     */
    private Integer cumulativeInvestment;

    /**
     * 指数
     */
    private Double exponent;

}
