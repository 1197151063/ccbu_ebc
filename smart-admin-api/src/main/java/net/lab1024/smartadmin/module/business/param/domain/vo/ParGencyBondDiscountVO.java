package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 代理债券折扣 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-09 14:44:56
 * @since JDK1.8
 */
@Data
public class ParGencyBondDiscountVO {
    @ApiModelProperty("代理债券折扣ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("债券类型")
    private String bondType;

    @ApiModelProperty("折扣")
    private Integer discount;



}
