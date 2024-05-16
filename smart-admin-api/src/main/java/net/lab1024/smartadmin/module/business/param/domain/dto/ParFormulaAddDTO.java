package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-07 14:42:04
 * @since JDK1.8
 */
@Data
public class ParFormulaAddDTO {
    @ApiModelProperty("业务名称")
    private String businessName;

    @ApiModelProperty("参数")
    private Double parameter;


}
