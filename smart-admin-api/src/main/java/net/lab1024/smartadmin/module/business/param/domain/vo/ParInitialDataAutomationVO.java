package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 初始数据表-人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-06 14:22:45
 * @since JDK1.8
 */
@Data
public class ParInitialDataAutomationVO {
    @ApiModelProperty("自动化投资")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("本阶段")
    private Double presentStage;

    @ApiModelProperty("累计投资")
    private Double cumulativeInvestment;

    @ApiModelProperty("指数")
    private Double exponent;



}
