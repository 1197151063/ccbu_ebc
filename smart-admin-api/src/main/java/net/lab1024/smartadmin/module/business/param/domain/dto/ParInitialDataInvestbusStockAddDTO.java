package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 初始数据表-投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-25 14:04:09
 * @since JDK1.8
 */
@Data
public class ParInitialDataInvestbusStockAddDTO {
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
    private Long stockDividend;

    @ApiModelProperty("账面损益")
    private Integer stockAccount;


}
