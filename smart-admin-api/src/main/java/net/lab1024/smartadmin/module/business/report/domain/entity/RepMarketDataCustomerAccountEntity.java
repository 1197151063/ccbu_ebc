package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:25:07
 * @since JDK1.8
 */
@Data
@TableName("rep_market_data_customer_account")
public class RepMarketDataCustomerAccountEntity extends BaseEntity{


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
     * 客户账户营业数据
     */
    private String customerMarketBusinessData;

    /**
     * 账户数据类型[A=账户数量(单位：个)，V=单位账户平均价值(单位：千元)]
     */
    private String accountType;

    /**
     * <35岁
     */
    private Integer lessAge;

    /**
     * 35~55岁
     */
    private Integer betweenAge;

    /**
     * >55岁
     */
    private Integer greaterAge;

    /**
     * 小型企业
     */
    private Integer smallEnterprise;

    /**
     * 中型企业
     */
    private Integer mediumEnterprise;

    /**
     * 大型企业
     */
    private Integer majorEnterprise;

    /**
     * 公共机构
     */
    private Integer publicInstitution;

    /**
     * 账户合计
     */
    private Integer accountSum;

}
