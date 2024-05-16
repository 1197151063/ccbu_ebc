package net.lab1024.smartadmin.module.business.decision.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 投资业务(短期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-24 15:47:18
 * @since JDK1.8
 */
@Data
public class DecInvestmentShortAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("资产总量")
    private Integer totalAssets;

    @ApiModelProperty("股票")
    private Integer investmentStock;

    @ApiModelProperty("债券")
    private Integer nvestmentBond;


}
