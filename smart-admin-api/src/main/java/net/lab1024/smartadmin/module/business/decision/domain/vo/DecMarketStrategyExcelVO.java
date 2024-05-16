package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 市场营销策略 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:47:54
 * @since JDK1.8
 */
@Data
public class DecMarketStrategyExcelVO {
    @Excel(name = "市场营销策略ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "职员总数")
    private Integer headcount;

    @Excel(name = "贷款业务-市场拓展<35岁")
    private Integer loansBusineDevelopLess;

    @Excel(name = "贷款业务-客户维护<35岁")
    private Integer loansCustomerMainLess;

    @Excel(name = "存款业务-市场拓展<35岁")
    private Integer depositBusineDevelopLess;

    @Excel(name = "存款业务-客户维护<35岁")
    private Integer depositCustomerMainLess;

    @Excel(name = "中间业务-市场拓展<35岁")
    private Long middleBusineDevelopLess;

    @Excel(name = "中间业务-客户维护<35岁")
    private Long middleCustomerMainLess;

    @Excel(name = "贷款业务-市场拓展35~55岁")
    private Integer loansBusineDevelopBetween;

    @Excel(name = "贷款业务-客户维护35~55岁")
    private Integer loansCustomerMainBetween;

    @Excel(name = "存款业务-市场拓展35~55岁")
    private Integer depositBusineDevelopBetween;

    @Excel(name = "存款业务-客户维护35~55岁")
    private Integer depositCustomerMainBetween;

    @Excel(name = "中间业务-市场拓展35~55岁")
    private Long middleBusineDevelopBetween;

    @Excel(name = "中间业务-客户维护35~55岁")
    private Long middleCustomerMainBetween;

    @Excel(name = "贷款业务-市场拓展>55岁")
    private Integer loansBusineDevelopGreater;

    @Excel(name = "贷款业务-客户维护>55岁")
    private Integer loansCustomerMainGreater;

    @Excel(name = "存款业务-市场拓展>55岁")
    private Integer depositBusineDevelopGreater;

    @Excel(name = "存款业务-客户维护>55岁")
    private Integer depositCustomerMainGreater;

    @Excel(name = "中间业务-市场拓展>55岁")
    private Long middleBusineDevelopGreater;

    @Excel(name = "中间业务-客户维护>55岁")
    private Long middleCustomerMainGreater;

    @Excel(name = "贷款业务-市场拓展-小型企业")
    private Integer loansBusineDevelopSmall;

    @Excel(name = "贷款业务-客户维护-小型企业")
    private Integer loansCustomerMainSmall;

    @Excel(name = "存款业务-市场拓展-小型企业")
    private Integer depositBusineDevelopSmall;

    @Excel(name = "存款业务-客户维护-小型企业")
    private Integer depositCustomerMainSmall;

    @Excel(name = "中间业务-市场拓展-小型企业")
    private Long middleBusineDevelopSmall;

    @Excel(name = "中间业务-客户维护-小型企业")
    private Long middleCustomerMainSmall;

    @Excel(name = "贷款业务-市场拓展-中型企业")
    private Integer loansBusineDevelopMedium;

    @Excel(name = "贷款业务-客户维护-中型企业")
    private Integer loansCustomerMainMedium;

    @Excel(name = "存款业务-市场拓展-中型企业")
    private Integer depositBusineDevelopMedium;

    @Excel(name = "存款业务-客户维护-中型企业")
    private Integer depositCustomerMainMedium;

    @Excel(name = "中间业务-市场拓展-中型企业")
    private Long middleBusineDevelopMedium;

    @Excel(name = "中间业务-客户维护-中型企业")
    private Long middleCustomerMainMedium;

    @Excel(name = "贷款业务-市场拓展-大型企业")
    private Integer loansBusineDevelopMajor;

    @Excel(name = "贷款业务-客户维护-大型企业")
    private Integer loansCustomerMainMajor;

    @Excel(name = "存款业务-市场拓展-大型企业")
    private Integer depositBusineDevelopMajor;

    @Excel(name = "存款业务-客户维护-大型企业")
    private Integer depositCustomerMainMajor;

    @Excel(name = "中间业务-市场拓展-大型企业")
    private Long middleBusineDevelopMajor;

    @Excel(name = "中间业务-客户维护-大型企业")
    private Long middleCustomerMainMajor;

    @Excel(name = "贷款业务-市场拓展-公共机构")
    private Integer loansBusineDevelopPublic;

    @Excel(name = "贷款业务-客户维护-公共机构")
    private Integer loansCustomerMainPublic;

    @Excel(name = "存款业务-市场拓展-公共机构")
    private Integer depositBusineDevelopPublic;

    @Excel(name = "存款业务-客户维护-公共机构")
    private Integer depositCustomerMainPublic;

    @Excel(name = "中间业务-市场拓展-公共机构")
    private Long middleBusineDevelopPublic;

    @Excel(name = "中间业务-客户维护-公共机构")
    private Long middleCustomerMainPublic;

    @Excel(name = "客户营销-可用费用总量")
    private Integer customerMarketingTotalCostAvailable;

    @Excel(name = "客户营销-<35岁")
    private Integer customerMarketingLessAge;

    @Excel(name = "客户营销-35~55岁")
    private Integer customerMarketingBetweenAge;

    @Excel(name = "客户营销->55岁")
    private Integer customerMarketingGreaterAge;

    @Excel(name = "客户营销-小型企业")
    private Integer customerMarketingSmallEnterprise;

    @Excel(name = "客户营销-中型企业")
    private Integer customerMarketingMediumEnterprise;

    @Excel(name = "客户营销-大型企业")
    private Integer customerMarketingMajorEnterprise;

    @Excel(name = "客户营销-公共机构")
    private Integer customerMarketingPublicInstitution;

    @Excel(name = "业务营销-可用费用总量")
    private Integer businessMarketingTotalCostAvailable;

    @Excel(name = "业务营销-贷款业务(单位：百万元)")
    private Integer businessMarketingLoanTransaction;

    @Excel(name = "业务营销-存款业务(单位：百万元)")
    private Integer businessMarketingDepositBloanTransaction;

    @Excel(name = "业务营销-中间业务(单位：百万元)")
    private Integer businessMarketingMiddleTransaction;



}
