package net.lab1024.smartadmin.module.business.report.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-08 12:46:28
 * @since JDK1.8
 */
@Data
public class RepAgencyBondVO {
    @ApiModelProperty("代理债券发行业务数据表ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("债券类型")
    private String bondType;

    @ApiModelProperty("票面利率(%)")
    private Double couponRate;

    @ApiModelProperty("期限")
    private Integer deadline;

    @ApiModelProperty("信用评级")
    private String creditRating;

    @ApiModelProperty("发行金额")
    private Integer issueAmount;

    @ApiModelProperty("承销发行银行")
    private String underwritingBank;

    @ApiModelProperty("承销金额（百万元）")
    private Integer underwritingMount;

    @ApiModelProperty("认购额度（%）")
    private Double subscriptionLimit;

    @ApiModelProperty("承销价格（%）")
    private Double underwritingPrice;

    @ApiModelProperty("折扣（%）")
    private Integer discount;



}
