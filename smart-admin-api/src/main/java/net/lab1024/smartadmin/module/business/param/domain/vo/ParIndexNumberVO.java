package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 自动化投资指数 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-09 18:42:39
 * @since JDK1.8
 */
@Data
public class ParIndexNumberVO {
    @ApiModelProperty("自动化投资指数ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("指数")
    private Double indexNumber;

    @ApiModelProperty("所需工作小时变动率")
    private Double rateOfChange;

    @ApiModelProperty("累计投资")
    private Integer cumulativeInvestment;



}
