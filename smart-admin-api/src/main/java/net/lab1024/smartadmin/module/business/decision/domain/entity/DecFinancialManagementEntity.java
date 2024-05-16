package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 财务管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 15:45:59
 * @since JDK1.8
 */
@Data
@TableName("dec_financial_management")
public class DecFinancialManagementEntity extends BaseEntity{


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
     * 分红（%）
     */
    private Double shareBonus;

    /**
     * 增效（百万元）
     */
    private Integer synergia;

}
