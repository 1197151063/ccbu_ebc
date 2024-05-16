package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 初始数据表-投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 14:03:28
 * @since JDK1.8
 */
@Data
public class ParInitialDataInvestbusBondAddDTO {
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
