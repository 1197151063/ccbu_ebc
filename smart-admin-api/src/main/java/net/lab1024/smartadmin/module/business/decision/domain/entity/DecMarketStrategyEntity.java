package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 市场营销策略 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 15:47:54
 * @since JDK1.8
 */
@Data
@TableName("dec_market_strategy")
public class DecMarketStrategyEntity extends BaseEntity{


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
     * 职员总数
     */
    private Integer headcount;

    /**
     * 贷款业务-市场拓展<35岁
     */
    private Integer loansBusineDevelopLess;

    /**
     * 贷款业务-客户维护<35岁
     */
    private Integer loansCustomerMainLess;

    /**
     * 存款业务-市场拓展<35岁
     */
    private Integer depositBusineDevelopLess;

    /**
     * 存款业务-客户维护<35岁
     */
    private Integer depositCustomerMainLess;

    /**
     * 中间业务-市场拓展<35岁
     */
    private Long middleBusineDevelopLess;

    /**
     * 中间业务-客户维护<35岁
     */
    private Long middleCustomerMainLess;

    /**
     * 贷款业务-市场拓展35~55岁
     */
    private Integer loansBusineDevelopBetween;

    /**
     * 贷款业务-客户维护35~55岁
     */
    private Integer loansCustomerMainBetween;

    /**
     * 存款业务-市场拓展35~55岁
     */
    private Integer depositBusineDevelopBetween;

    /**
     * 存款业务-客户维护35~55岁
     */
    private Integer depositCustomerMainBetween;

    /**
     * 中间业务-市场拓展35~55岁
     */
    private Long middleBusineDevelopBetween;

    /**
     * 中间业务-客户维护35~55岁
     */
    private Long middleCustomerMainBetween;

    /**
     * 贷款业务-市场拓展>55岁
     */
    private Integer loansBusineDevelopGreater;

    /**
     * 贷款业务-客户维护>55岁
     */
    private Integer loansCustomerMainGreater;

    /**
     * 存款业务-市场拓展>55岁
     */
    private Integer depositBusineDevelopGreater;

    /**
     * 存款业务-客户维护>55岁
     */
    private Integer depositCustomerMainGreater;

    /**
     * 中间业务-市场拓展>55岁
     */
    private Long middleBusineDevelopGreater;

    /**
     * 中间业务-客户维护>55岁
     */
    private Long middleCustomerMainGreater;

    /**
     * 贷款业务-市场拓展-小型企业
     */
    private Integer loansBusineDevelopSmall;

    /**
     * 贷款业务-客户维护-小型企业
     */
    private Integer loansCustomerMainSmall;

    /**
     * 存款业务-市场拓展-小型企业
     */
    private Integer depositBusineDevelopSmall;

    /**
     * 存款业务-客户维护-小型企业
     */
    private Integer depositCustomerMainSmall;

    /**
     * 中间业务-市场拓展-小型企业
     */
    private Long middleBusineDevelopSmall;

    /**
     * 中间业务-客户维护-小型企业
     */
    private Long middleCustomerMainSmall;

    /**
     * 贷款业务-市场拓展-中型企业
     */
    private Integer loansBusineDevelopMedium;

    /**
     * 贷款业务-客户维护-中型企业
     */
    private Integer loansCustomerMainMedium;

    /**
     * 存款业务-市场拓展-中型企业
     */
    private Integer depositBusineDevelopMedium;

    /**
     * 存款业务-客户维护-中型企业
     */
    private Integer depositCustomerMainMedium;

    /**
     * 中间业务-市场拓展-中型企业
     */
    private Long middleBusineDevelopMedium;

    /**
     * 中间业务-客户维护-中型企业
     */
    private Long middleCustomerMainMedium;

    /**
     * 贷款业务-市场拓展-大型企业
     */
    private Integer loansBusineDevelopMajor;

    /**
     * 贷款业务-客户维护-大型企业
     */
    private Integer loansCustomerMainMajor;

    /**
     * 存款业务-市场拓展-大型企业
     */
    private Integer depositBusineDevelopMajor;

    /**
     * 存款业务-客户维护-大型企业
     */
    private Integer depositCustomerMainMajor;

    /**
     * 中间业务-市场拓展-大型企业
     */
    private Long middleBusineDevelopMajor;

    /**
     * 中间业务-客户维护-大型企业
     */
    private Long middleCustomerMainMajor;

    /**
     * 贷款业务-市场拓展-公共机构
     */
    private Integer loansBusineDevelopPublic;

    /**
     * 贷款业务-客户维护-公共机构
     */
    private Integer loansCustomerMainPublic;

    /**
     * 存款业务-市场拓展-公共机构
     */
    private Integer depositBusineDevelopPublic;

    /**
     * 存款业务-客户维护-公共机构
     */
    private Integer depositCustomerMainPublic;

    /**
     * 中间业务-市场拓展-公共机构
     */
    private Long middleBusineDevelopPublic;

    /**
     * 中间业务-客户维护-公共机构
     */
    private Long middleCustomerMainPublic;

    /**
     * 客户营销-可用费用总量
     */
    private Integer customerMarketingTotalCostAvailable;

    /**
     * 客户营销-<35岁
     */
    private Integer customerMarketingLessAge;

    /**
     * 客户营销-35~55岁
     */
    private Integer customerMarketingBetweenAge;

    /**
     * 客户营销->55岁
     */
    private Integer customerMarketingGreaterAge;

    /**
     * 客户营销-小型企业
     */
    private Integer customerMarketingSmallEnterprise;

    /**
     * 客户营销-中型企业
     */
    private Integer customerMarketingMediumEnterprise;

    /**
     * 客户营销-大型企业
     */
    private Integer customerMarketingMajorEnterprise;

    /**
     * 客户营销-公共机构
     */
    private Integer customerMarketingPublicInstitution;

    /**
     * 业务营销-可用费用总量
     */
    private Integer businessMarketingTotalCostAvailable;

    /**
     * 业务营销-贷款业务(单位：百万元)
     */
    private Integer businessMarketingLoanTransaction;

    /**
     * 业务营销-存款业务(单位：百万元)
     */
    private Integer businessMarketingDepositBloanTransaction;

    /**
     * 业务营销-中间业务(单位：百万元)
     */
    private Integer businessMarketingMiddleTransaction;

}
