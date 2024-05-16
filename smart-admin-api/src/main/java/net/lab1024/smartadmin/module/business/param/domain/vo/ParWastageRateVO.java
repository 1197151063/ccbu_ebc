package net.lab1024.smartadmin.module.business.param.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-08 16:05:49
 * @since JDK1.8
 */
@Data
public class ParWastageRateVO {
    @ApiModelProperty("人员流失率ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("业务类型")
    private String businessType;

    @ApiModelProperty("人员流失率")
    private Double wastageRate;



}
