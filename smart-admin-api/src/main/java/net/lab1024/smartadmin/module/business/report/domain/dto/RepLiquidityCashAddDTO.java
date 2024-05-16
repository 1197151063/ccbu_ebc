package net.lab1024.smartadmin.module.business.report.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-24 16:07:10
 * @since JDK1.8
 */
@Data
public class RepLiquidityCashAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("同业存放款项")
    private Integer interbankDeposit;

    @ApiModelProperty("同业拆入")
    private Integer interBank;

    @ApiModelProperty("短期存款")
    private Integer shortDeposit;

    @ApiModelProperty("长期存款")
    private Integer longDeposit;

    @ApiModelProperty("短期储蓄存款")
    private Integer shortSavingsDeposit;

    @ApiModelProperty("长期储蓄存款")
    private Integer longSavingsDeposit;

    @ApiModelProperty("短期债务总计")
    private Integer totalShortDebt;

    @ApiModelProperty("现金流动性要求")
    private Long cashLiquidityRequirements;

    @ApiModelProperty("清偿力")
    private Integer solvency;

    @ApiModelProperty("现金清偿力")
    private Integer cashSolvency;


}
