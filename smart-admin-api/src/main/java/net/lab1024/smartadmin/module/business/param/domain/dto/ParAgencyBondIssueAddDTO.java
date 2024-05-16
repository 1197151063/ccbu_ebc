package net.lab1024.smartadmin.module.business.param.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 代理债券发行业务信息表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 13:37:59
 * @since JDK1.8
 */
@Data
public class ParAgencyBondIssueAddDTO {
    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("债券种类(甲/乙/丙)")
    private String typesBonds;

    @ApiModelProperty("票面利率(%)")
    private Double couponRate;

    @ApiModelProperty("期限")
    private Integer deadline;

    @ApiModelProperty("信用评级")
    private String creditRating;

    @ApiModelProperty("发行金额(百万元)")
    private Integer issuance;

    @ApiModelProperty("最低承销价格(%)")
    private Double minPrice;

    @ApiModelProperty("认购额度")
    private Double subscriptionLimit;
}
