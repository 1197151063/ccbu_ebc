package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 18:23:18
 * @since JDK1.8
 */
@Data
public class RepInvestbusOperatDataStockVO {
    @ApiModelProperty("投资股票ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("项目")
    private String stockProject;

    @ApiModelProperty("股票指数")
    private Double stockIndex;

    @ApiModelProperty("股票类型(长期或短期)")
    private String stockType;

    @ApiModelProperty("股票数量(手)")
    private Integer stockNumber;

    @ApiModelProperty("股票价值")
    private Integer stockCost;

    @ApiModelProperty("股息")
    private Integer stockDividend;

    @ApiModelProperty("账面损益")
    private Integer stockAccount;



}
