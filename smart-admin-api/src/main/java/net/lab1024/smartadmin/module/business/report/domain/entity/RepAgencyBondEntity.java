package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 12:46:28
 * @since JDK1.8
 */
@Data
@TableName("rep_agency_bond")
public class RepAgencyBondEntity extends BaseEntity{


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
     * 债券类型
     */
    private String bondType;

    /**
     * 票面利率(%)
     */
    private Double couponRate;

    /**
     * 期限
     */
    private Integer deadline;

    /**
     * 信用评级
     */
    private String creditRating;

    /**
     * 发行金额
     */
    private Integer issueAmount;

    /**
     * 承销发行银行
     */
    private String underwritingBank;

    /**
     * 承销金额（百万元）
     */
    private Integer underwritingMount;

    /**
     * 认购额度（%）
     */
    private Double subscriptionLimit;

    /**
     * 承销价格（%）
     */
    private Double underwritingPrice;

    /**
     * 折扣（%）
     */
    private Integer discount;

}
