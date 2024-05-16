package net.lab1024.smartadmin.module.business.decision.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 中间业务提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-24 15:48:49
 * @since JDK1.8
 */
@Data
public class DecMiddleCustodyAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("托管业务-增长率%")
    private Double custodyBusinessAdd;

    @ApiModelProperty("担保业务-增长率%")
    private Double guaranteeBusinessAdd;

    @ApiModelProperty("托管业务-手续费率%")
    private Double custodyBusinessProcedure;

    @ApiModelProperty("担保业务-手续费率%")
    private Double guaranteeBusinessProcedure;

    @ApiModelProperty("甲债券承销价(%)")
    private Double firstUnderwritPriceBond;

    @ApiModelProperty("乙债券承销价(%)")
    private Double secondUnderwritPriceBond;

    @ApiModelProperty("丙债券承销价(%)")
    private Double thirdUnderwritPriceBond;

    @ApiModelProperty("保存:0,提交:1")
    private Integer state;

}
