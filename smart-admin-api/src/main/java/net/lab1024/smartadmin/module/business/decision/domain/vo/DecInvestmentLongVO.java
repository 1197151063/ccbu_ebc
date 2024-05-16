package net.lab1024.smartadmin.module.business.decision.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 投资业务(长期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:46:37
 * @since JDK1.8
 */
@Data
public class DecInvestmentLongVO extends DecInvestmentShortVO{
    @ApiModelProperty("投资业务(长期投资)ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("股票/债券类型(P0~5)")
    private String bondType;

    @ApiModelProperty("买入或卖出(债券第一次只能买)")
    private Integer buySell;

    @ApiModelProperty("保存:0,提交:1")
    private Integer status;

}
