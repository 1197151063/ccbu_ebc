package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 初始数据表-人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-06 14:22:45
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_automation")
public class ParInitialDataAutomationEntity extends BaseEntity{


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
    private Double presentStage;

    /**
     * 累计投资
     */
    private Double cumulativeInvestment;

    /**
     * 指数
     */
    private Double exponent;

}
