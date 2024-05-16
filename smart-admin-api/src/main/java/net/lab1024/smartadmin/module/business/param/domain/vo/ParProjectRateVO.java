package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 项目利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:52:11
 * @since JDK1.8
 */
@Data
public class ParProjectRateVO {
    @ApiModelProperty("项目利率ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("项目名")
    private String projectName;

    @ApiModelProperty("利率 利率/股指")
    private Double interestRate;



}
