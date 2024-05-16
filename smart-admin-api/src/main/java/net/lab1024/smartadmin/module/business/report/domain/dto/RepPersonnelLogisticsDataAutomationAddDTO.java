package net.lab1024.smartadmin.module.business.report.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 11:24:21
 * @since JDK1.8
 */
@Data
public class RepPersonnelLogisticsDataAutomationAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("本阶段")
    private Integer presentStage;

    @ApiModelProperty("累计投资")
    private Integer cumulativeInvestment;

    @ApiModelProperty("指数")
    private Double exponent;


}
