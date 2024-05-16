package net.lab1024.smartadmin.module.business.param.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 资产负债利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 13:52:53
 * @since JDK1.8
 */
@Data
public class ParPropertyLiabilityRateAddDTO {
    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("类型(资产，负债)")
    private String interestRateType;

    @ApiModelProperty("最低")
    private Double interestRateMin;

    @ApiModelProperty("平均")
    private Double interestRateAverage;

    @ApiModelProperty("最高")
    private Double interestRateMax;


}
