package net.lab1024.smartadmin.module.business.decision.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 投资业务(长期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-24 15:46:37
 * @since JDK1.8
 */
@Data
public class DecInvestmentLongAddDTO extends DecInvestmentShortAddDTO{
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;
    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;
    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;
    private Integer shares1;
    private Integer shares2;
    private Integer shares3;
    private Integer shares4;
    private Integer shares5;
    private Integer bond_a_1;
    private Integer bond_a_2;
    private Integer bond_a_3;
    private Integer bond_a_4;
    private Integer bond_a_5;
    private Integer bond_b_1;
    private Integer bond_b_2;
    private Integer bond_b_3;
    private Integer bond_b_4;
    private Integer bond_b_5;
    private Integer bond_c_2;
    private Integer bond_c_3;
    private Integer bond_c_4;
    private Integer bond_c_5;
    private Integer bond_d_3;
    private Integer bond_d_4;
    private Integer bond_d_5;
    private Integer bond_e_4;
    private Integer bond_e_5;
    private Integer bond_f_5;

    @ApiModelProperty("股票/债券类型(P0~5)")
    private String bondType;

    @ApiModelProperty("买入或卖出(债券第一次只能买)")
    private Integer buySell;

    @ApiModelProperty("保存:0,提交:1")
    private Integer state;
}
