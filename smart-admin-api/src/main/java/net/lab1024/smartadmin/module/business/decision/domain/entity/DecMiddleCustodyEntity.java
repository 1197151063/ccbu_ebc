package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 中间业务提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 15:48:49
 * @since JDK1.8
 */
@Data
@TableName("dec_middle_custody")
public class DecMiddleCustodyEntity extends BaseEntity{


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
     * 托管业务-增长率%
     */
    private Double custodyBusinessAdd;

    /**
     * 担保业务-增长率%
     */
    private Double guaranteeBusinessAdd;

    /**
     * 托管业务-手续费率%
     */
    private Double custodyBusinessProcedure;

    /**
     * 担保业务-手续费率%
     */
    private Double guaranteeBusinessProcedure;

    /**
     * 甲债券承销价(%)
     */
    private Double firstUnderwritPriceBond;

    /**
     * 乙债券承销价(%)
     */
    private Double secondUnderwritPriceBond;

    /**
     * 丙债券承销价(%)
     */
    private Double thirdUnderwritPriceBond;

}
