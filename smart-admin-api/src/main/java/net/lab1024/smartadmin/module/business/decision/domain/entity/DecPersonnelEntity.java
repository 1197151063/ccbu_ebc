package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 15:49:37
 * @since JDK1.8
 */
@Data
@TableName("dec_personnel")
public class DecPersonnelEntity extends BaseEntity{


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
     * 雇佣/解聘(雇员数量)-贷款业务
     */
    private Integer hireFireLoans;

    /**
     * 培训(天/人)-贷款业务
     */
    private Integer trainLoans;

    /**
     * 市场拓展(%)-贷款业务
     */
    private Double businessDevelopmentLoans;

    /**
     * 客户维护(%)-贷款业务
     */
    private Double customerCareLoans;

    /**
     * 雇佣/解聘(雇员数量)-存款业务
     */
    private Integer hireFireDeposit;

    /**
     * 培训(天/人)-存款业务
     */
    private Integer trainDeposit;

    /**
     * 市场拓展(%)-存款业务
     */
    private Double businessDevelopmentDeposit;

    /**
     * 客户维护(%)-存款业务
     */
    private Double customerCareDeposit;

    /**
     * 雇佣/解聘(雇员数量)-投资业务
     */
    private Integer hireFireInvest;

    /**
     * 培训(天/人)-投资业务
     */
    private Integer trainInvest;

    /**
     * 市场拓展(%)-投资业务
     */
    private Double businessDevelopmentInvest;

    /**
     * 客户维护(%)-投资业务
     */
    private Double customerCareInvest;

    /**
     * 雇佣/解聘(雇员数量)-中间业务
     */
    private Long hireFireMiddle;

    /**
     * 培训(天/人)-中间业务
     */
    private Long trainMiddle;

    /**
     * 市场拓展(%)-中间业务
     */
    private Double businessDevelopmentMiddle;

    /**
     * 客户维护(%)-中间业务
     */
    private Double customerCareMiddle;

    /**
     * 附加人员成本(%)
     */
    private Double additionalPersonnelCost;

    /**
     * 自动化投资(百万元)
     */
    private Integer automationInvestment;

}
