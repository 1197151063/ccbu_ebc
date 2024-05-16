package net.lab1024.smartadmin.module.business.decision.domain.vo;

import io.swagger.annotations.ApiModelProperty;
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
public class DecMarketStrategyVO {
    @ApiModelProperty("市场营销策略ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("职员总数")
    private Integer headcount;

    @ApiModelProperty("贷款业务-市场拓展<35岁")
    private Integer loansBusineDevelopLess;

    @ApiModelProperty("贷款业务-客户维护<35岁")
    private Integer loansCustomerMainLess;

    @ApiModelProperty("存款业务-市场拓展<35岁")
    private Integer depositBusineDevelopLess;

    @ApiModelProperty("存款业务-客户维护<35岁")
    private Integer depositCustomerMainLess;

    @ApiModelProperty("中间业务-市场拓展<35岁")
    private Integer middleBusineDevelopLess;

    @ApiModelProperty("中间业务-客户维护<35岁")
    private Integer middleCustomerMainLess;

    @ApiModelProperty("贷款业务-市场拓展35~55岁")
    private Integer loansBusineDevelopBetween;

    @ApiModelProperty("贷款业务-客户维护35~55岁")
    private Integer loansCustomerMainBetween;

    @ApiModelProperty("存款业务-市场拓展35~55岁")
    private Integer depositBusineDevelopBetween;

    @ApiModelProperty("存款业务-客户维护35~55岁")
    private Integer depositCustomerMainBetween;

    @ApiModelProperty("中间业务-市场拓展35~55岁")
    private Integer middleBusineDevelopBetween;

    @ApiModelProperty("中间业务-客户维护35~55岁")
    private Integer middleCustomerMainBetween;

    @ApiModelProperty("贷款业务-市场拓展>55岁")
    private Integer loansBusineDevelopGreater;

    @ApiModelProperty("贷款业务-客户维护>55岁")
    private Integer loansCustomerMainGreater;

    @ApiModelProperty("存款业务-市场拓展>55岁")
    private Integer depositBusineDevelopGreater;

    @ApiModelProperty("存款业务-客户维护>55岁")
    private Integer depositCustomerMainGreater;

    @ApiModelProperty("中间业务-市场拓展>55岁")
    private Integer middleBusineDevelopGreater;

    @ApiModelProperty("中间业务-客户维护>55岁")
    private Integer middleCustomerMainGreater;

    @ApiModelProperty("贷款业务-市场拓展-小型企业")
    private Integer loansBusineDevelopSmall;

    @ApiModelProperty("贷款业务-客户维护-小型企业")
    private Integer loansCustomerMainSmall;

    @ApiModelProperty("存款业务-市场拓展-小型企业")
    private Integer depositBusineDevelopSmall;

    @ApiModelProperty("存款业务-客户维护-小型企业")
    private Integer depositCustomerMainSmall;

    @ApiModelProperty("中间业务-市场拓展-小型企业")
    private Integer middleBusineDevelopSmall;

    @ApiModelProperty("中间业务-客户维护-小型企业")
    private Integer middleCustomerMainSmall;

    @ApiModelProperty("贷款业务-市场拓展-中型企业")
    private Integer loansBusineDevelopMedium;

    @ApiModelProperty("贷款业务-客户维护-中型企业")
    private Integer loansCustomerMainMedium;

    @ApiModelProperty("存款业务-市场拓展-中型企业")
    private Integer depositBusineDevelopMedium;

    @ApiModelProperty("存款业务-客户维护-中型企业")
    private Integer depositCustomerMainMedium;

    @ApiModelProperty("中间业务-市场拓展-中型企业")
    private Integer middleBusineDevelopMedium;

    @ApiModelProperty("中间业务-客户维护-中型企业")
    private Integer middleCustomerMainMedium;

    @ApiModelProperty("贷款业务-市场拓展-大型企业")
    private Integer loansBusineDevelopMajor;

    @ApiModelProperty("贷款业务-客户维护-大型企业")
    private Integer loansCustomerMainMajor;

    @ApiModelProperty("存款业务-市场拓展-大型企业")
    private Integer depositBusineDevelopMajor;

    @ApiModelProperty("存款业务-客户维护-大型企业")
    private Integer depositCustomerMainMajor;

    @ApiModelProperty("中间业务-市场拓展-大型企业")
    private Integer middleBusineDevelopMajor;

    @ApiModelProperty("中间业务-客户维护-大型企业")
    private Integer middleCustomerMainMajor;

    @ApiModelProperty("贷款业务-市场拓展-公共机构")
    private Integer loansBusineDevelopPublic;

    @ApiModelProperty("贷款业务-客户维护-公共机构")
    private Integer loansCustomerMainPublic;

    @ApiModelProperty("存款业务-市场拓展-公共机构")
    private Integer depositBusineDevelopPublic;

    @ApiModelProperty("存款业务-客户维护-公共机构")
    private Integer depositCustomerMainPublic;

    @ApiModelProperty("中间业务-市场拓展-公共机构")
    private Integer middleBusineDevelopPublic;

    @ApiModelProperty("中间业务-客户维护-公共机构")
    private Integer middleCustomerMainPublic;

    @ApiModelProperty("客户营销-可用费用总量")
    private Integer customerMarketingTotalCostAvailable;

    @ApiModelProperty("客户营销-<35岁")
    private Integer customerMarketingLessAge;

    @ApiModelProperty("客户营销-35~55岁")
    private Integer customerMarketingBetweenAge;

    @ApiModelProperty("客户营销->55岁")
    private Integer customerMarketingGreaterAge;

    @ApiModelProperty("客户营销-小型企业")
    private Integer customerMarketingSmallEnterprise;

    @ApiModelProperty("客户营销-中型企业")
    private Integer customerMarketingMediumEnterprise;

    @ApiModelProperty("客户营销-大型企业")
    private Integer customerMarketingMajorEnterprise;

    @ApiModelProperty("客户营销-公共机构")
    private Integer customerMarketingPublicInstitution;

    @ApiModelProperty("业务营销-可用费用总量")
    private Integer businessMarketingTotalCostAvailable;

    @ApiModelProperty("业务营销-贷款业务(单位：百万元)")
    private Integer businessMarketingLoanTransaction;

    @ApiModelProperty("业务营销-存款业务(单位：百万元)")
    private Integer businessMarketingDepositBloanTransaction;

    @ApiModelProperty("业务营销-中间业务(单位：百万元)")
    private Integer businessMarketingMiddleTransaction;

    @ApiModelProperty("保存:0,提交:1")
    private Integer status;

}
