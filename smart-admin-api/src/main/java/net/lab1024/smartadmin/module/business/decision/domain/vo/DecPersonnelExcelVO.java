package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class DecPersonnelExcelVO {
    @Excel(name = "人事后勤信息录入ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "雇佣/解聘(雇员数量)-贷款业务")
    private Integer hireFireLoans;

    @Excel(name = "培训(天/人)-贷款业务")
    private Integer trainLoans;

    @Excel(name = "市场拓展(%)-贷款业务")
    private Double businessDevelopmentLoans;

    @Excel(name = "客户维护(%)-贷款业务")
    private Double customerCareLoans;

    @Excel(name = "雇佣/解聘(雇员数量)-存款业务")
    private Integer hireFireDeposit;

    @Excel(name = "培训(天/人)-存款业务")
    private Integer trainDeposit;

    @Excel(name = "市场拓展(%)-存款业务")
    private Double businessDevelopmentDeposit;

    @Excel(name = "客户维护(%)-存款业务")
    private Double customerCareDeposit;

    @Excel(name = "雇佣/解聘(雇员数量)-投资业务")
    private Integer hireFireInvest;

    @Excel(name = "培训(天/人)-投资业务")
    private Integer trainInvest;

    @Excel(name = "市场拓展(%)-投资业务")
    private Double businessDevelopmentInvest;

    @Excel(name = "客户维护(%)-投资业务")
    private Double customerCareInvest;

    @Excel(name = "雇佣/解聘(雇员数量)-中间业务")
    private Long hireFireMiddle;

    @Excel(name = "培训(天/人)-中间业务")
    private Long trainMiddle;

    @Excel(name = "市场拓展(%)-中间业务")
    private Double businessDevelopmentMiddle;

    @Excel(name = "客户维护(%)-中间业务")
    private Double customerCareMiddle;

    @Excel(name = "附加人员成本(%)")
    private Double additionalPersonnelCost;

    @Excel(name = "自动化投资(百万元)")
    private Integer automationInvestment;



}
