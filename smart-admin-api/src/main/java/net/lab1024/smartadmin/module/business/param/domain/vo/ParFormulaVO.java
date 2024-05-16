package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-07 14:42:04
 * @since JDK1.8
 */
@Data
public class ParFormulaVO {
    @ApiModelProperty("存贷款计算公式ID")
    private Long id;

    @ApiModelProperty("业务名称")
    private String businessName;

    @ApiModelProperty("参数")
    private Double parameter;



}
