package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 初始数据表-流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 13:44:40
 * @since JDK1.8
 */
@Data
public class ParInitialDataLiquidityCashAddDTO {
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
