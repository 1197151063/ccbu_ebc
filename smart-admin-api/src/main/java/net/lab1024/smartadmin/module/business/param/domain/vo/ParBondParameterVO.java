package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 债券参数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:42:33
 * @since JDK1.8
 */
@Data
public class ParBondParameterVO {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("债券种类(各阶段解锁一个，初始值只能加不能减)")
    private String bondParameter;

    @ApiModelProperty("债券价格%")
    private Double bondPrice;



}
