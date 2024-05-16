package net.lab1024.smartadmin.module.business.param.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-08 16:05:49
 * @since JDK1.8
 */
@Data
public class ParWastageRateAddDTO {
    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("业务类型")
    private String businessType;

    @ApiModelProperty("人员流失率")
    private Double wastageRate;


}
