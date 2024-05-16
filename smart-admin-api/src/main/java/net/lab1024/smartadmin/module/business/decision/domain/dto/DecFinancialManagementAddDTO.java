package net.lab1024.smartadmin.module.business.decision.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 财务管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-24 15:45:59
 * @since JDK1.8
 */
@Data
public class DecFinancialManagementAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("分红（%）")
    private Double shareBonus;

    @ApiModelProperty("增效（百万元）")
    private Integer synergia;

    @ApiModelProperty("保存:0,提交:1")
    private Integer state;


}
