package net.lab1024.smartadmin.module.business.decision.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:49:37
 * @since JDK1.8
 */
@Data
public class DecPersonnelVO {
    @ApiModelProperty("人事后勤信息录入ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("雇佣/解聘(雇员数量)-贷款业务")
    private Integer hireFireLoans;

    @ApiModelProperty("培训(天/人)-贷款业务")
    private Integer trainLoans;

    @ApiModelProperty("市场拓展(%)-贷款业务")
    private Double businessDevelopmentLoans;

    @ApiModelProperty("客户维护(%)-贷款业务")
    private Double customerCareLoans;

    @ApiModelProperty("雇佣/解聘(雇员数量)-存款业务")
    private Integer hireFireDeposit;

    @ApiModelProperty("培训(天/人)-存款业务")
    private Integer trainDeposit;

    @ApiModelProperty("市场拓展(%)-存款业务")
    private Double businessDevelopmentDeposit;

    @ApiModelProperty("客户维护(%)-存款业务")
    private Double customerCareDeposit;

    @ApiModelProperty("雇佣/解聘(雇员数量)-投资业务")
    private Integer hireFireInvest;

    @ApiModelProperty("培训(天/人)-投资业务")
    private Integer trainInvest;

    @ApiModelProperty("市场拓展(%)-投资业务")
    private Double businessDevelopmentInvest;

    @ApiModelProperty("客户维护(%)-投资业务")
    private Double customerCareInvest;

    @ApiModelProperty("雇佣/解聘(雇员数量)-中间业务")
    private Integer hireFireMiddle;

    @ApiModelProperty("培训(天/人)-中间业务")
    private Integer trainMiddle;

    @ApiModelProperty("市场拓展(%)-中间业务")
    private Double businessDevelopmentMiddle;

    @ApiModelProperty("客户维护(%)-中间业务")
    private Double customerCareMiddle;

    @ApiModelProperty("附加人员成本(%)")
    private Double additionalPersonnelCost;

    @ApiModelProperty("自动化投资(百万元)")
    private Integer automationInvestment;

    @ApiModelProperty("保存:0,提交:1")
    private Integer status;
}
