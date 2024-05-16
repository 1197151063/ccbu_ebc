package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

/**
 * [ 代理债券发行业务信息表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:37:59
 * @since JDK1.8
 */
@Data
@TableName("par_agency_bond_issue")
public class ParAgencyBondIssueEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 债券种类(甲/乙/丙)
     */
    private String typesBonds;

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
     * 发行金额(百万元)
     */
    private Integer issuance;

    /**
     * 最低承销价格(%)
     */
    private Double minPrice;

    //认购额度
    private Double subscriptionLimit;

}
