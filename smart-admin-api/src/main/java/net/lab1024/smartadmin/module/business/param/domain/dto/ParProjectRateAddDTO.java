package net.lab1024.smartadmin.module.business.param.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 项目利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 13:52:11
 * @since JDK1.8
 */
@Data
public class ParProjectRateAddDTO {
    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("项目名")
    private String projectName;

    @ApiModelProperty("利率 利率/股指")
    private Double interestRate;


}
