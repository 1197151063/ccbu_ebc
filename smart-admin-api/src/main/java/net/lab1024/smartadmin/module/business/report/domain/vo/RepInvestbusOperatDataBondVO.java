package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 18:22:46
 * @since JDK1.8
 */
@Data
public class RepInvestbusOperatDataBondVO {
    @ApiModelProperty("投资债券ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("债券种类(来自各个阶段)")
    private String bondKind;

    @ApiModelProperty("价格(%)")
    private Double bondPrice;

    @ApiModelProperty("债券投资类型(长期或短期)")
    private String bondType;

    @ApiModelProperty("实际价值")
    private Integer realValue;

    @ApiModelProperty("名义价值")
    private Integer nominalValue;

    @ApiModelProperty("利率(%)")
    private Double rateInterest;

    @ApiModelProperty("利息")
    private Integer interest;

    @ApiModelProperty("账面损益")
    private Integer accountProfitLoss;



}
